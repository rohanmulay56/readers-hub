package com.ideas.readershub.library.service;

import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.user.BooksTaken;
import com.ideas.readershub.user.UserAccount;

import java.util.Optional;

public class LibraryService {

    private BookWithdrawnService bookWithdrawnService;
    private BookReturnService bookReturnService;

    public LibraryService(UserAccount user) {
        this.bookWithdrawnService = new BookWithdrawnService(user);
        this.bookReturnService = new BookReturnService(user);
    }

    public Optional<Book> searchBook(String bookName) {
        return bookWithdrawnService.searchBook(bookName);
    }

    public void withdrawBook(String bookName) {
        bookWithdrawnService.withdrawBook(bookName);
    }

    public void returnBook(BooksTaken bookName) {
        bookReturnService.returnBook(bookName);
    }

    public void setBookWithdrawnService(BookWithdrawnService bookWithdrawnService) {
        this.bookWithdrawnService = bookWithdrawnService;
    }

    public void setBookReturnService(BookReturnService bookReturnService) {
        this.bookReturnService = bookReturnService;
    }
}
