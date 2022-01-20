package com.ideas.readershub.library.validation;

import com.ideas.readershub.commons.ValidationException;
import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.user.UserAccount;

public class MaxBooksCountValidator implements Validator {
    @Override
    public boolean validate(UserAccount user, Book newBook) {
        int maxBooks = user.getSubscription().getMaxBooks();
        int booksTaken = user.getBooksTaken().size();
        if(!(booksTaken < maxBooks)){
            throw new ValidationException("Max book count can not exceed max books allowed in your subscription package!");
        }
        return true;
    }
}
