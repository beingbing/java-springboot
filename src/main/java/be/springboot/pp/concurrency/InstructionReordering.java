package be.springboot.pp.concurrency;
// 9
public class InstructionReordering {

    public static void main(String[] args) {
        System.out.println("InstructionReordering: main: args: " + args + " " + Thread.currentThread().getName());

        try {
            NumberFactory numberFactory = new NumberFactory();

            for (int i = 0; i < 1_00_000; i++) {
                Thread t = new Thread(new FactoryWorker(numberFactory));
                int cur = numberFactory.getW(); // memory visibility problem
                System.out.println("InstructionReordering: main: cur: " + cur + " " + Thread.currentThread().getName());
                t.start();
                while (cur == numberFactory.getW()) {}
                System.out.println("w value: " + numberFactory.getW() + " " + Thread.currentThread().getName());
                if (!(numberFactory.getX() == numberFactory.getY()
                        && numberFactory.getY() == numberFactory.getZ()
                        && numberFactory.getZ() == numberFactory.getW()))
                    System.out.println("InstructionReordering: main: values: "
                            + numberFactory.getX() + " "
                            + numberFactory.getY() + " "
                            + numberFactory.getZ() + " "
                            + numberFactory.getW());
                t.join();
                System.out.println("loop ends for i: " + i + " " + Thread.currentThread().getName());
            }
        } catch (InterruptedException ex) {
            System.out.println("InstructionReordering: main: ex: " + ex.getMessage());
        }
    }
}

class NumberFactory {

    private int x, y, z, w;

    public NumberFactory() {
        x = 0;
        y = 0;
        z = 0;
        w = 0;
    }

    /*
    * JVM / instruction optimizers reorder instructions to get some performance benefits,
    * without changing the semantics of the program.
    * Which might lead to w moving upward or in between x, y and z.
    *
    * Synchronized keyword prevents this as well, it prevents JVM from rearranging
    * instructions in a critical section for performance benefits.
    *
    * But apart from preventing reordering, we still need to synchronize getters and
    * setters, hence all below getters will also need to be synchronized.
    * */
    public void increment() {
        x++;
        y++;
        z++;
        w++;
    }

    public int getW() {
        return w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

class FactoryWorker implements Runnable {

    private final NumberFactory numberFactory;

    public FactoryWorker(NumberFactory numberFactory) {
        this.numberFactory = numberFactory;
    }

    @Override
    public void run() {
        System.out.println("FactoryWorker: run: " + Thread.currentThread().getName());
        numberFactory.increment();
    }
}
