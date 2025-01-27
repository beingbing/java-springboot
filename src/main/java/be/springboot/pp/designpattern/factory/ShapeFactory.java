package be.springboot.pp.designpattern.factory;

public class ShapeFactory {
    // Factory Method
    public Shape getShape(ShapeFactoryCreations shapeType) {
        if (shapeType == null) return null;
        return switch (shapeType) {
            case CIRCLE -> new Circle();
            case RECTANGLE -> new Rectangle();
            case SQUARE -> new Square();
            default -> throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        };
    }
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle.");
    }
}

// Concrete Product: Rectangle
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle.");
    }
}

// Concrete Product: Square
class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Square.");
    }
}
