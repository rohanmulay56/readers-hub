package com.ideas.readershub.library.service;

import com.ideas.readershub.commons.NotFoundException;
import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.library.entity.LibraryInventory;
import com.ideas.readershub.library.validation.LibraryValidationService;
import com.ideas.readershub.user.BooksTaken;
import com.ideas.readershub.user.UserAccount;

import java.time.LocalDate;
import java.util.Optional;

public class BookWithdrawnService {
    private final UserAccount user;
    private final LibraryInventory inventory;
    private final LibraryValidationService validationService;

    public BookWithdrawnService(UserAccount user) {
        this.user = user;
        this.inventory = LibraryInventory.getInstance();
        this.validationService = new LibraryValidationService();
    }

    public Optional<Book> searchBook(String bookName) {
        return inventory.getBook(bookName);
    }

    public void withdrawBook(String bookName) {
        Book book = getBook(bookName);
        validationService.validateRequest(user,book);
        assignBookToUser(book);
        removeBookFromInventory(book);
    }



    private void removeBookFromInventory(Book book) {
        inventory.getBookList().remove(book);
    }

    private void assignBookToUser(Book book) {
        user.getBooksTaken().add(toBookTaken(book));
    }

    private Book getBook(String bookName) {
        Optional<Book> optionalBook = searchBook(bookName);
        if (optionalBook.isEmpty()) {
            throw new NotFoundException("No Book found with book name: " + bookName);
        }
        return optionalBook.get();
    }


    private BooksTaken toBookTaken(Book book) {
        return BooksTaken.builder()
                .book(book)
                .bookWithdrawDate(LocalDate.now())
                .build();
    }

    //for testing
    LibraryInventory getInventory() {
        return inventory;
    }

}
