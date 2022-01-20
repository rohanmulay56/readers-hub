package com.ideas.readershub.user;

import com.ideas.readershub.library.enums.Subscription;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountTest {

    @Test
    void userCanTakeSubscription() {
        UserAccount userAccount = new UserAccount("USER_NAME", Subscription.PLATINUM);
        assertEquals(6, userAccount.getSubscription().getMaxBooks());
        assertEquals(20, userAccount.getSubscription().getMaxPeriod());
        assertEquals(50, userAccount.getSubscription().getExtraCharges());
        assertEquals(5000, userAccount.getSubscription().getPackageCost());
        assertEquals("USER_NAME", userAccount.getUserName());
        assertEquals(LocalDate.now().plusYears(1), userAccount.getExpiryDate());
        assertEquals(5000, userAccount.getBillAmount());
    }
}
