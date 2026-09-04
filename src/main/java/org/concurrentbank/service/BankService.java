package org.concurrentbank.service;

import org.concurrentbank.model.Account;

public class BankService {

    public void transfer(Account from, Account to, double amount) {

        Account firstLock;
        Account secondLock;

        if (from.getAccountNumber()
                .compareTo(to.getAccountNumber()) < 0) {

            firstLock = from;
            secondLock = to;

        } else {

            firstLock = to;
            secondLock = from;
        }

        synchronized (firstLock) {

            System.out.println(
                    Thread.currentThread().getName()
                            + " locked "
                            + firstLock.getOwnerName()
            );

            synchronized (secondLock) {

                System.out.println(
                        Thread.currentThread().getName()
                                + " locked "
                                + secondLock.getOwnerName()
                );

                if (from.getBalance() >= amount) {

                    from.withdraw(amount);
                    to.deposit(amount);

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " transferred "
                                    + amount
                                    + " from "
                                    + from.getOwnerName()
                                    + " to "
                                    + to.getOwnerName()
                    );

                } else {

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " - Insufficient funds"
                    );
                }
            }
        }
    }
}
