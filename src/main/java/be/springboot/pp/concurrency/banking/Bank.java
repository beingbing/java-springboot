package be.springboot.pp.concurrency.banking;

public class Bank {

    public void transfer(Account source, Account destination, int amount) {
        source.deduct(amount);
        destination.add(amount);
    }
}
