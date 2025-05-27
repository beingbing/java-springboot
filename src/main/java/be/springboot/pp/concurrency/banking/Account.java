package be.springboot.pp.concurrency.banking;

public class Account {
    private final int id;
    private int amount;

    public Account(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public void add(int credit) {
        amount += credit;
    }

    public void deduct(int debit) {
        if (debit > amount) throw new RuntimeException("debit amount: " + debit + " is more than balance amount: " + amount);
        amount -= debit;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}
