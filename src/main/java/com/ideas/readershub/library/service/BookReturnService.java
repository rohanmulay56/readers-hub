package com.ideas.readershub.library.service;

import com.ideas.readershub.library.entity.LibraryInventory;
import com.ideas.readershub.user.BooksTaken;
import com.ideas.readershub.user.UserAccount;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class BookReturnService {
    private final UserAccount user;
    private final LibraryInventory inventory;

    public BookReturnService(UserAccount user) {
        this.user = user;
        this.inventory = LibraryInventory.getInstance();
    }

    public void returnBook(BooksTaken booksTaken) {
        double extraCost = calculateExtraCost(booksTaken);
        user.addToBill(extraCost);
        takeBookBackFromUser(booksTaken);
        addToInventory(booksTaken);
    }

    private double calculateExtraCost(BooksTaken booksTaken) {
        booksTaken.setBookReturnDate(LocalDate.now());
        int maxPeriod = user.getSubscription().getMaxPeriod();
        double extraCharges = user.getSubscription().getExtraCharges();
        long daysBetween = DAYS.between(booksTaken.getBookWithdrawDate(), booksTaken.getBookReturnDate());
        if (daysBetween > maxPeriod) {
            return  (daysBetween - maxPeriod) * extraCharges;
        }
        return 0;
    }

    private void takeBookBackFromUser(BooksTaken booksTaken) {
        user.getBooksTaken().remove(booksTaken);
    }

    private void addToInventory(BooksTaken booksTaken) {
        inventory.getBookList().add(booksTaken.getBook());
    }

    LibraryInventory getInventory(){
        return inventory;
    }
}
