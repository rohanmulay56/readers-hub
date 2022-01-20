package com.ideas.readershub.library.entity;

import com.ideas.readershub.library.enums.Category;
import com.ideas.readershub.library.enums.Language;

import java.util.ArrayList;
import java.util.List;

public class InventoryLoader {
    public static List<Book> getBooks() {
        return new ArrayList<>(List.of(
                getBook("To Kill a Mockingbird", Category.Arts, Language.English),
                getBook("Harry Potter and the Philosopher’s Stone", Category.Business, Language.French),
                getBook("The Lord of the Rings", Category.Business, Language.Hindi),
                getBook("The Great Gatsby", Category.Cooking, Language.Spanish),
                getBook("Pride and Prejudice", Category.History, Language.Hindi),
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
