package be.springboot.pp.designpattern.behavioral.state.atm.db;

import be.springboot.pp.designpattern.behavioral.state.atm.card.CardDetails;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.AtmState;
import be.springboot.pp.designpattern.behavioral.state.atm.enums.TxnStatus;

public class DbAccessor {

    private DbAccessor() {}

    public static AtmState getAtmState(long machineId) {
        // access DB to get current state
        return AtmState.READY;
    }

    public static int createNewTxnId(long atmId) {
        // logic to retrieve new txn-id for atm from DB
        return 1;
    }

    public static void updateAtmState(long machineId, AtmState state) {
        // save in db
    }

    public static void persistCardDetails(CardDetails cardDetails, long machineId) {
        //
    }

    public static void saveCardValidationFailure(CardDetails cardDetails, long machineId) {
        //
    }

    public static void saveTxnCancellation(int txnId) {
        //
    }

    public static void saveWithdrawal(int txnId, float amount, TxnStatus txnStatus) {
        //
    }

    public static float markTxnExecuted(int txnId) {
        //
        return 0f;
    }
}
