package org.concurrentbank.concurrency;

import org.concurrentbank.model.Account;
import org.concurrentbank.service.BankService;

public class TransferTask implements Runnable {

    private final BankService bankService;
    private final Account from;
    private final Account to;
    private final double amount;

    public TransferTask(BankService bankService, Account from, Account to, double amount) {
        this.bankService = bankService;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        bankService.transfer(from, to, amount);
    }
}
