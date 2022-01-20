package com.ideas.readershub.library.validation;

import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.user.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class LibraryValidationService {
    private final List<Validator> validators;

    public LibraryValidationService() {
        validators = new ArrayList<>();
        validators.add(new MaxBooksCountValidator());
        validators.add(new MaxBooksPerLanguageValidator());
    }

    public boolean validateRequest(UserAccount user, Book newBook) {
        for (Validator validator : validators) {
            validator.validate(user, newBook);
        }
        return true;
    }
}
