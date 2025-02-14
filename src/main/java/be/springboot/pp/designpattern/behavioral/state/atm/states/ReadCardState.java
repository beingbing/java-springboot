package be.springboot.pp.designpattern.behavioral.state.atm.states;

import be.springboot.pp.designpattern.behavioral.state.atm.Atm;
import be.springboot.pp.designpattern.behavioral.state.atm.card.CardDetails;
import be.springboot.pp.designpattern.behavioral.state.atm.card.CardManagerFactory;
import be.springboot.pp.designpattern.behavioral.state.atm.db.DbAccessor;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.AtmState;

public class ReadCardState implements ChangeState {
    private final Atm atm;

    public ReadCardState(Atm atm) {
        this.atm = atm;
    }

    @Override
    public int init() {
        System.out.println("ReadCardState: init");
        throw new IllegalStateException();
    }

    @Override
    public boolean cancel(int txnId) {
        System.out.println("ReadCardState: cancel");
        DbAccessor.saveTxnCancellation(txnId);
        this.atm.changeState(StateFactory.getState(AtmState.EJECT_CARD, this.atm));
        return true;
    }

    @Override
    public boolean readCard(CardDetails cardDetails) {
        System.out.println("ReadCardState: readCard");
        DbAccessor.persistCardDetails(cardDetails, this.atm.getAtmId());

        boolean isCardValid = CardManagerFactory
                .getCardManager(cardDetails.getCardType())
                .validateCard(cardDetails);
        if (!isCardValid) {
            DbAccessor.saveCardValidationFailure(cardDetails, this.atm.getAtmId());
            this.atm.changeState(StateFactory.getState(AtmState.EJECT_CARD, this.atm));
            return false;
        }
        this.atm.changeState(StateFactory.getState(AtmState.READ_AMOUNT, this.atm));
        return true;
    }

    @Override
    public boolean readAmount(CardDetails cardDetails, float amount, int txnId) {
        System.out.println("ReadCardState: readAmount");
        throw new IllegalStateException();
    }

    @Override
    public float dispenseCash(int txnId) {
        System.out.println("ReadCardState: dispenseCash");
        throw new IllegalStateException();
    }

    @Override
    public void ejectCard() {
        System.out.println("ReadCardState: ejectCard");
        throw new IllegalStateException();
    }

    @Override
    public AtmState getState() {
        return AtmState.READ_CARD;
    }
}
