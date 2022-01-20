package com.ideas.readershub.library.validation;

import com.ideas.readershub.commons.ValidationException;
import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.user.UserAccount;

import java.time.LocalDate;

public class SubscriptionExpiredValidator implements Validator{
    @Override
    public boolean validate(UserAccount user, Book newBook) {
        if (user.getExpiryDate().isBefore(LocalDate.now())){
            throw new ValidationException("Subscription expired!");
        }
        return true;
    }
}
