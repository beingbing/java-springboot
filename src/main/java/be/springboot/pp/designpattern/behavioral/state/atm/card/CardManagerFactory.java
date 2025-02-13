package be.springboot.pp.designpattern.behavioral.state.atm.card;

import be.springboot.pp.designpattern.behavioral.state.atm.enums.CardType;

public class CardManagerFactory {

    private CardManagerFactory() {}

    public static CardManager getCardManager(CardType cardType) {
        return switch (cardType) {
            case DEBIT -> new DebitCardManager();
            case CREDIT -> new CreditCardManager();
        };
    }
}
