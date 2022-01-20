package com.ideas.readershub.user;

import com.ideas.readershub.library.enums.Subscription;
import com.ideas.readershub.user.BooksTaken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class UserAccount {
    private String userName;
    private Subscription subscription;
    private LocalDate expiryDate;
    private List<BooksTaken> booksTaken = new ArrayList<>();
    private double billAmount;

    public UserAccount(String userName, Subscription subscription) {
        this.userName = userName;
        this.subscription = subscription;
        this.expiryDate = LocalDate.now().plusYears(1);
        this.billAmount = subscription.getPackageCost();
    }

    public void addToBill(double cost) {
        billAmount = billAmount + cost;
    }
}
