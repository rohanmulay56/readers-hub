package com.ideas.readershub.library.service;

import com.ideas.readershub.library.enums.Subscription;
import com.ideas.readershub.user.BooksTaken;
import com.ideas.readershub.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.ideas.readershub.TestMother.getBookTaken;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {
    private LibraryService libraryService;
    @Mock
    private BookReturnService bookReturnService;
    @Mock
    private BookWithdrawnService bookWithdrawnService;

    @BeforeEach
    public void setup() {
        UserAccount userAccount = new UserAccount("Rohan", Subscription.PLATINUM);
        libraryService = new LibraryService(userAccount);
        libraryService.setBookReturnService(bookReturnService);
        libraryService.setBookWithdrawnService(bookWithdrawnService);
    }

    @Test
    void userSearchBookAvailability() {
        libraryService.searchBook("The Hobbit");
        verify(bookWithdrawnService).searchBook("The Hobbit");
    }

    @Test
    void shouldWithdrawn() {
        libraryService.withdrawBook("The Hobbit");
        verify(bookWithdrawnService).withdrawBook("The Hobbit");
    }

    @Test
    void shouldReturnBook() {
        BooksTaken the_hobbit = getBookTaken("The Hobbit", LocalDate.now());
        libraryService.returnBook(the_hobbit);
        verify(bookReturnService).returnBook(the_hobbit);
    }

}
