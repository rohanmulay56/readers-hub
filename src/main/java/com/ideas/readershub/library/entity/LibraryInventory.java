package com.ideas.readershub.library.entity;

import lombok.Data;

import java.util.List;
import java.util.Optional;

import static com.ideas.readershub.library.entity.InventoryLoader.getBooks;

@Data
public class LibraryInventory {
    private static LibraryInventory instance;
    private List<Book> bookList;

    private LibraryInventory() {
    }

    static {
        // static block to initialize instance
        instance = new LibraryInventory();
        instance.setBookList(getBooks());
    }

    public static LibraryInventory getInstance() {
        return instance;
    }

    public Optional<Book> getBook(String bookName) {
        return bookList.stream()
                .filter(book -> bookName.equalsIgnoreCase(book.getBookName()))
                .findFirst();
    }
}
