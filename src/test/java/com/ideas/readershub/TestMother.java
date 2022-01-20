package com.ideas.readershub;

import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.library.enums.Category;
import com.ideas.readershub.library.enums.Language;
import com.ideas.readershub.library.enums.Subscription;
import com.ideas.readershub.user.BooksTaken;
import com.ideas.readershub.user.UserAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestMother {

    public static UserAccount getUserAccount(Subscription subscription, int booksTaken) {
        return UserAccount.builder()
                .userName("Manasi")
                .subscription(subscription)
                .booksTaken(getBooksTaken(booksTaken))
                .expiryDate(LocalDate.now().plusYears(1))
                .build();
    }

    private static List<BooksTaken> getBooksTaken(int booksTaken) {
        List<BooksTaken> booksTakenList = new ArrayList<>();
        for (int i = 0; i < booksTaken; i++) {
            booksTakenList.add(getBookTaken("The Hobbit", LocalDate.now()));
        }
        return booksTakenList;
    }

    public static BooksTaken getBookTaken(String bookName, LocalDate now) {
        return BooksTaken.builder()
                .book(getBook(bookName, Category.Arts, Language.English))
                .bookWithdrawDate(now)
                .build();
    }

    public static List<Book> getBooks() {
        return new ArrayList<>(List.of(
                getBook("To Kill a Mockingbird", Category.Arts, Language.English),
                getBook("Harry Potter and the Philosopher’s Stone", Category.Business, Language.French),
                getBook("The Lord of the Rings", Category.Business, Language.Hindi),
                getBook("Pride and Prejudice", Category.History, Language.Hindi),
                getBook("The Great Gatsby", Category.Cooking, Language.Spanish),
                getBook("The Diary Of A Young Girl", Category.Sports, Language.English),
                getBook("The Book Thief", Category.Sports, Language.French),
                getBook("The Hobbit", Category.Arts, Language.English),
                getBook("Little Women", Category.History, Language.Hindi),
                getBook("Fahrenheit 451", Category.Cooking, Language.Spanish),
                getBook("Animal Farm", Category.Business, Language.French),
                getBook("Gone with the Wind", Category.Arts, Language.English)
        ));
    }

    public static Book getBook(String bookName, Category category, Language language) {
        return Book.builder()
                .bookName(bookName)
                .category(category)
                .language(language)
                .build();
    }
}
