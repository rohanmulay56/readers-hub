package com.ideas.readershub.library.enums;

import java.util.Optional;

public enum Subscription {
    IVORY(3, 7, 20, 1000),
    SILVER(4, 10, 30, 2000),
    GOLD(5, 15, 40, 3000),
    PLATINUM(6, 20, 50, 5000);

    private final int maxBooks;
    private final int maxPeriod;
    private final double extraCharges;
    private final double packageCost;

    Subscription(int maxBooks, int maxPeriod, double extraCharges, double packageCost) {
        this.maxBooks = maxBooks;
        this.maxPeriod = maxPeriod;
        this.extraCharges = extraCharges;
        this.packageCost = packageCost;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public int getMaxPeriod() {
        return maxPeriod;
    }

    public double getExtraCharges() {
        return extraCharges;
    }

    public double getPackageCost() {
        return packageCost;
    }

    public Optional<Subscription> getSubscription(int maxBooks) {
        for (Subscription subscription : Subscription.values()) {
            if (subscription.maxBooks == maxBooks) {
                return Optional.of(subscription);
            }
        }
        return Optional.empty();
    }
}
