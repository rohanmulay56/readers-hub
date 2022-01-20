package com.ideas.readershub.library.validation;

import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.user.UserAccount;

public interface Validator {
    boolean validate(UserAccount user, Book newBook);
}
