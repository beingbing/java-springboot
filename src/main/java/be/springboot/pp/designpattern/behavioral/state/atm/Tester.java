package be.springboot.pp.designpattern.behavioral.state.atm;

import be.springboot.pp.designpattern.behavioral.state.atm.card.CardDetails;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.CardType;

public class Tester {

    public static void main(String[] args) {
        Atm atm = new Atm(5000);

        int txnId = atm.init();  // Start the transaction
        CardDetails cardDetails = new CardDetails(CardType.DEBIT, 123456789, 1234, "samar");
        atm.readCard(cardDetails); // Insert valid card
        boolean legitAmount = atm.readAmount(cardDetails, 1000, txnId);   // Enter amount
        System.out.println("legit-amount: " + legitAmount);

        if (legitAmount) atm.dispenseCash(txnId);

        System.out.println("------------------------");
    }
}
