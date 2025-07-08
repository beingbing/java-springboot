package be.springboot.pp.concurrency.banking;

import java.util.Comparator;
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
            // Sort accounts by ID to avoid deadlock (global locking order)
            List<Account> sortedAccounts = accounts.stream()
                    .sorted(Comparator.comparingInt(Account::getId))
                    .toList();

            // assume we have only 5 accounts for now
            synchronized(accounts.get(0)) {
                synchronized(accounts.get(1)) {
                    synchronized(accounts.get(2)) {
                        synchronized(accounts.get(3)) {
                            synchronized(accounts.get(4)) {
                                int sum = 0;
                                for (Account account : sortedAccounts) {
                                    System.out.println(account.getId() + " : " + account.getAmount());
                                    sum += account.getAmount();
                                }
                                System.out.println("total amount: " + sum);
                            }
                        }
                    }
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/*
 * By doing this we bottleneck all threads on bank object. This will fix the problem but heavily degrade the performance.
 * */

/*
* Audit only happens after lock is acquired on all available accounts, so that when audit is happening then no
* txn happen.
* */