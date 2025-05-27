package be.springboot.pp.concurrency.banking;

public class Bank {

    /*
    * By synchronizing this function we have synchronized all transfer operations.
    * Now if t1 is transferring from a1 to a2 and
    * t2 is transferring from a7 to a9
    * none of them do so if t3 acquired the lock, entered this block and is transferring
    * from a3 to a4.
    *
    * Although all accounts are unrelated. But got stuck.
    * */
//    public synchronized void transfer(Account source, Account destination, int amount) {
//        source.deduct(amount);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        destination.add(amount);
//    }

    public synchronized void transfer(Account source, Account destination, int amount) {
        Account a1, a2;
        if (source.getId() < destination.getId()) {
            a1 = source;
            a2 = destination;
        } else {
            a1 = destination;
            a2 = source;
        }
        synchronized(a1) {
            synchronized(a2) {
                source.deduct(amount);
                destination.add(amount);
            }
        }
    }
}
