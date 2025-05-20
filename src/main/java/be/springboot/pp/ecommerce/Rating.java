package be.springboot.pp.ecommerce;

public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(4);

    private final int value;

    private Rating(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }
}
