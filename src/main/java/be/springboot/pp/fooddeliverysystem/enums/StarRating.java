package be.springboot.pp.fooddeliverysystem.enums;

// 5 enums, 5 objects will be created during bootrstraping.
// each object can have attributes as well, here that attribute is value.
public enum StarRating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(4); // invoking their constructors.

    private final int value;

    private StarRating(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    } // getter to get value of enum-type on which it is called.
}
