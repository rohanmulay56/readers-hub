package com.ideas.readershub.library.service;

import com.ideas.readershub.commons.NotFoundException;
import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.library.enums.Subscription;
import com.ideas.readershub.user.BooksTaken;
import com.ideas.readershub.user.UserAccount;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookWithdrawnServiceTest {

    private BookWithdrawnService bookWithdrawnService;
    private UserAccount userAccount;

    @Test
    void userCannotTakeMissingBook() {
        assertThrows(NotFoundException.class, () -> helloReaderHub("Rohan", Subscription.PLATINUM)
                .giveMeABook("RandomName"));
    }

    @Test
    void userCanTakeAvailableBook() {
        String bookName = "The Lord of the Rings";
        helloReaderHub("Rohan", Subscription.PLATINUM)
                .giveMeABook(bookName)
                .assertBookAddedToMyAccount(bookName)
                .assertBookPresentInInventory(bookName, false);
    }

    private BookWithdrawnServiceTest assertBookAddedToMyAccount(String bookTaken) {
        Optional<Book> bookAddedToMyList = userAccount.getBooksTaken()
                .stream()
                .map(BooksTaken::getBook)
                .filter(book -> bookTaken.equalsIgnoreCase(book.getBookName()))
                .findAny();
        assertTrue(bookAddedToMyList.isPresent());
        return this;
    }

    private void assertBookPresentInInventory(String bookName, boolean expected) {
        Optional<Book> bookFound = bookWithdrawnService.getInventory()
                .getBookList()
                .stream()
                .filter(book -> bookName.equalsIgnoreCase(book.getBookName()))
                .findAny();
        assertEquals(expected, bookFound.isPresent());
    }

    private BookWithdrawnServiceTest giveMeABook(String bookName) {
        bookWithdrawnService.withdrawBook(bookName);
        return this;
    }

    private BookWithdrawnServiceTest helloReaderHub(String userName, Subscription subscription) {
        userAccount = new UserAccount(userName, subscription);
        bookWithdrawnService = new BookWithdrawnService(userAccount);
        return this;
    }

}
