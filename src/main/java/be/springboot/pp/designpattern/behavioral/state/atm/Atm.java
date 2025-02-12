package be.springboot.pp.designpattern.behavioral.state.atm;

public class Atm {

    public int init() {return 0;}

    public boolean cancel(int txnId) {return true;}

    public boolean readCard(String cardType, long cardNum, int pin, String name) {return true;}

    public boolean readWithdrawalDetails(String cardType, long cardNum, int pin, String name, float amount) {return true;}

    public boolean dispenseCash(int txnId) {return true;}

    public void ejectCard() {}
}
