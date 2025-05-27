package be.springboot.pp.concurrency.banking;

import java.util.List;

public class Auditor implements Runnable {
    private final List<Account> accounts;
    private final Bank bank;

    public Auditor(List<Account> accounts, Bank bank) {
        this.accounts = accounts;
        this.bank = bank;
    }

    @Override
    public void run() {
        while (true) {
            int sum = 0;
            for (Account account : accounts) {
                System.out.println(account.getId() + " : " + account.getAmount());
                sum += account.getAmount();
            }
            System.out.println("total amount: " + sum);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
