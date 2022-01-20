package com.ideas.readershub.library.validation;

import com.ideas.readershub.commons.ValidationException;
import com.ideas.readershub.library.entity.Book;
import com.ideas.readershub.library.enums.Category;
import com.ideas.readershub.library.enums.Language;
import com.ideas.readershub.library.enums.Subscription;
import com.ideas.readershub.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ideas.readershub.TestMother.getUserAccount;
import static com.ideas.readershub.library.entity.InventoryLoader.getBook;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxBooksCountValidatorTest {
    private MaxBooksCountValidator maxBooksCountValidator;
    private UserAccount userAccount;
    private Book to_kill_a_mockingbird;

    @BeforeEach
    public void setUp() {
        maxBooksCountValidator = new MaxBooksCountValidator();
        to_kill_a_mockingbird = getBook("To Kill a Mockingbird", Category.Arts, Language.English);
    }

    @Test
    void shouldBeValidIfNumberOfBooksTakenLessThanAllowed() {
        userAccount = getUserAccount(Subscription.IVORY, 1);
        boolean valid = maxBooksCountValidator.validate(userAccount, to_kill_a_mockingbird);
        assertTrue(valid);
    }

    @Test
    void shouldBeInValidIfNumberOfBooksTakenEqualToAllowed() {
        userAccount = getUserAccount(Subscription.IVORY, 3);
        assertThrows(ValidationException.class, () -> maxBooksCountValidator.validate(userAccount, to_kill_a_mockingbird));
    }
}
