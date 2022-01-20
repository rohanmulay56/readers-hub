package com.ideas.readershub.library.service;

import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.library.enums.Subscription;
import com.ideas.readershub.user.BooksTaken;
import com.ideas.readershub.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static com.ideas.readershub.TestMother.getBookTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookReturnServiceTest {
    private BookReturnService bookReturnService;
    private UserAccount userAccount;
    private BooksTaken bookTaken;

    @Test
    void returnBook_whenNoExtraChargesApplicable() {
        helloReaderHub("chetan", Subscription.GOLD)
                .bookWithdrawnDate(LocalDate.now())
                .returnBook(bookTaken)
                .validateExtraCharges(0)
                .assertBookPresentInInventory(bookTaken.getBook().getBookName(), true);
    }

    @Test
    void returnBook_whenExtraChargesApplicable() {
        helloReaderHub("chetan", Subscription.GOLD)
                .bookWithdrawnDate(LocalDate.now().minusDays(40))
                .returnBook(bookTaken)
                .validateExtraCharges(1000)
                .assertBookPresentInInventory(bookTaken.getBook().getBookName(), true);
    }

    private void assertBookPresentInInventory(String bookName, boolean expected) {
        Optional<Book> bookFound = bookReturnService.getInventory()
                .getBookList()
                .stream()
                .filter(book -> bookName.equalsIgnoreCase(book.getBookName()))
                .findAny();
        assertEquals(expected, bookFound.isPresent());
    }

    private BookReturnServiceTest validateExtraCharges(int amount) {
        double extraCharge = userAccount.getBillAmount() - userAccount.getSubscription().getPackageCost();
        assertEquals(extraCharge, amount);
        return this;
    }

    private BookReturnServiceTest bookWithdrawnDate(LocalDate date) {
        bookTaken = getBookTaken("The Hobbit", date);
        userAccount.getBooksTaken().add(bookTaken);
        return this;
    }

    private BookReturnServiceTest returnBook(BooksTaken bookTaken) {
        bookReturnService.returnBook(bookTaken);
        return this;
    }

    private BookReturnServiceTest helloReaderHub(String userName, Subscription subscription) {
        userAccount = new UserAccount(userName, subscription);
        bookReturnService = new BookReturnService(userAccount);
        return this;
    }
}
