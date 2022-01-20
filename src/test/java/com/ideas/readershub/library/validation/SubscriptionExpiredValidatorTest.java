package com.ideas.readershub.library.validation;

import com.ideas.readershub.commons.ValidationException;
import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.library.enums.Category;
import com.ideas.readershub.library.enums.Language;
import com.ideas.readershub.library.enums.Subscription;
import com.ideas.readershub.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.ideas.readershub.TestMother.getUserAccount;
import static com.ideas.readershub.library.entity.InventoryLoader.getBook;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubscriptionExpiredValidatorTest {
    private SubscriptionExpiredValidator validator;
    private UserAccount userAccount;
    private Book newEnglishBook;

    @BeforeEach
    public void setUp() {
        validator = new SubscriptionExpiredValidator();
        userAccount = getUserAccount(Subscription.PLATINUM, 3);
        newEnglishBook = getBook("To Kill a Mockingbird", Category.Arts, Language.English);
    }

    @Test
    void validateActiveSubscription() {
        boolean validate = validator.validate(userAccount, newEnglishBook);
        assertTrue(validate);
    }

    @Test
    void validateInactiveSubscription() {
        userAccount.setExpiryDate(LocalDate.now().minusYears(2));
        assertThrows(ValidationException.class, () -> validator.validate(userAccount,newEnglishBook));
    }
}
