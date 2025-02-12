package be.springboot.pp.designpattern.behavioral.state.atm;

/*
* We are still missing handling to ensure that only single
* txn can happen at a time. For that we can maintain a list
* of states. Using that, i can stop a new txn if last txn is
* in any intermediary state.
* */
public class Atm {
    private final long atmId;

    public Atm(long atmId) {
        this.atmId = atmId;
    }

    public int init() {return 0;}

    public boolean cancel(int txnId) {return true;}

    public boolean readCard(String cardType, long cardNum, int pin, String name) {return true;}

    public boolean readWithdrawalDetails(String cardType, long cardNum, int pin, String name, float amount, int txnId) {return true;}

    public boolean dispenseCash(int txnId) {return true;}

    public void ejectCard() {}
}
