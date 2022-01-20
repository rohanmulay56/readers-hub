package com.ideas.readershub.library.validation;

import com.ideas.readershub.commons.ValidationException;
import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.library.enums.Language;
import com.ideas.readershub.user.BooksTaken;
import com.ideas.readershub.user.UserAccount;

import java.util.List;
import java.util.Map;

import static com.ideas.readershub.commons.LibraryConstants.MAX_BOOKS_PER_LANGUAGE;
import static java.util.stream.Collectors.groupingBy;

public class MaxBooksPerLanguageValidator implements Validator {

    @Override
    public boolean validate(UserAccount user, Book newBook) {
        Map<Language, List<Book>> booksByLanguage = user.getBooksTaken().stream()
                .map(BooksTaken::getBook)
                .collect(groupingBy(Book::getLanguage));

        Language language = newBook.getLanguage();
        if (null == booksByLanguage.get(language) || booksByLanguage.get(language).size() < MAX_BOOKS_PER_LANGUAGE) {
            return true;
        } else {
            throw new ValidationException("Max books per language can not exceed than " + MAX_BOOKS_PER_LANGUAGE);
        }
    }
}
