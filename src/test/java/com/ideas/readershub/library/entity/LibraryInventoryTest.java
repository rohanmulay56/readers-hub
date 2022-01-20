package com.ideas.readershub.library.entity;

import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.library.entity.LibraryInventory;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.ideas.readershub.TestMother.getBooks;
import static org.junit.jupiter.api.Assertions.*;

class LibraryInventoryTest {

    @Test
    void testInstance() {
        LibraryInventory obj1 = LibraryInventory.getInstance();
        LibraryInventory obj2 = LibraryInventory.getInstance();
        assertEquals(obj1, obj2);
    }

    @Test
    void shouldGetBook() {
        LibraryInventory libraryInventory = LibraryInventory.getInstance();
        libraryInventory.setBookList(getBooks());
        Optional<Book> book = libraryInventory.getBook("To Kill a Mockingbird");
        assertTrue(book.isPresent());
    }

    @Test
    void shouldNotGetBook() {
        LibraryInventory libraryInventory = LibraryInventory.getInstance();
        libraryInventory.setBookList(getBooks());
        Optional<Book> book = libraryInventory.getBook("Charlotte’s Web");
        assertFalse(book.isPresent());
    }
}
