package org.concurrentbank;

import org.concurrentbank.concurrency.TransferTask;
import org.concurrentbank.model.Account;
import org.concurrentbank.service.BankService;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Account alice =
                new Account("ACC001", "Alice", 10_000);

        Account bob =
                new Account("ACC002", "Bob", 10_000);

        BankService bankService = new BankService();

        Thread thread1 = new Thread(
                () -> bankService.transfer(alice, bob, 100),
                "Alice-to-Bob"
        );

        Thread thread2 = new Thread(
                () -> bankService.transfer(bob, alice, 100),
                "Bob-to-Alice"
        );

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Alice's balance: " + alice.getBalance());
        System.out.println("Bob's balance: " + bob.getBalance());

        System.out.println("Finished");
    }
}