package be.springboot.pp.designpattern.creational.factory;

public class Tester {
    public static void main(String[] args) {
        // Create a factory instance
        ShapeFactory shapeFactory = new ShapeFactory();

        // Get a Circle object and call its draw method
        Shape circle = shapeFactory.getShape(ShapeFactoryCreations.CIRCLE);
        circle.draw();

        // Get a Rectangle object and call its draw method
        Shape rectangle = shapeFactory.getShape(ShapeFactoryCreations.RECTANGLE);
        rectangle.draw();

        // Get a Square object and call its draw method
        Shape square = shapeFactory.getShape(ShapeFactoryCreations.SQUARE);
        square.draw();
    }
}
