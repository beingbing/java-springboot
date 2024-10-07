# Object-Oriented Programming with Java
Object-Oriented Programming (OOP) conceptualizes software design around data, or objects, rather
than functions and logic as was done in Procedural Programming. The four foundational pillars of
OOP are:
- Encapsulation
- Polymorphism
- Abstraction
- Inheritance

These principles help in creating modular, reusable, and scalable code.

## Class: The Blueprint for Objects
By bookish definition, it is a blueprint that defines the structure and behavior (data and algorithms)
of objects. In practical terms, it groups related data and methods together. In earlier procedural
programming languages like C complete logic used to be placed in a single file. As software complexity
grew, developers realized that code dealing with specific data can be together separated into a single
file as a single entity was more efficient, That entity was termed as `Class`. While creating Java on
OOP structure, creating Class and deriving Object out of it was central to language development.
In Java, there are six categories of classes:
- **Concrete Class:** A class with full implementations.
- **Interface:** Contains method signatures without implementations.
- **Enum:** Defines a set of constants.
- **Abstract Class:** A class that may contain both fully implemented methods and abstract methods (without implementation).
- **Annotation:** Metadata for code elements like classes and methods.
- **Final Class:** A class that cannot be subclassed and generated `Immutable Objects`.

## Constructors, Objects, and Pointers
A constructor is used to create instance (snapshot of a class / Object) of a class. Object creation
is done by allocating memory in the Heap and initializing the fields. Simultaneously a reference
(pointer) to that object is created in the stack memory. The pointer refers to the object's memory
location, allowing access to its fields and methods using the dot (.) operator.

If a user-defined constructor does not take any arguments to initialize fields or leaves out a field
initialization, then Java do the initialization by assigning default pre-defined values to fields.

If a constructor is not defined in a class, Java provides a default no-args constructor that assigns
default values to fields.

Java supports multiple constructors, provided they have different signatures (i.e., different
parameter lists).

Always prefer a constructor over instance initializer, as it makes reading code complicated.

## Access Modifiers
Access modifiers control the visibility of classes, fields, and methods.
- **public:** Accessible from anywhere.
- **protected:** Accessible within the same package and subclasses.
- **private:** Accessible only within the same class.

## Encapsulation
Encapsulation ensures data integrity by restricting direct access to an object’s fields. Fields are
marked private and accessed through public getters and setters. This concept is also known as data hiding.
```java
class Account {
    private double balance;

    // Getter
    public double getBalance() {
        return balance;
    }

    // Setter with data integrity check
    // Same check can be setup in constructor definition
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
    }
}
```
Here, encapsulation allows control over how fields are accessed and modified, protecting the data
from invalid values.

## Final Keyword
The `final` keyword prevents modification after compulsory assignment during initialization or
declaration.

For primitive types, final prevents reassignment.
For reference types, final means the reference cannot point to another object, but the object
itself can still be modified.

If you make a class's fields recursively final and prevent field modification, you create an
immutable object. Usage, They are crucial in multithreading because they prevent race conditions
(since they can't be changed once created).

## Static Keyword
The `static` keyword marks a field or method as belonging to the class rather than the instance. A static field or method is shared among all instances of the class. A static method can only access other static fields and methods. A static method can be called on an instance variable even if it is not pointing to any object (it's set to `null`)
```java
class Book {
    static int totalBooks = 0; // Class-level field

    public static void incrementBooks() {
        totalBooks++;
    }
}
```
The static field `totalBooks` is shared among all Book instances. It is initialized when the class is
loaded, not when an object is created.

Combining `static` and `final` with `public` access modifier creates global immutable constants.

## Interface
An interface defines behavior by specifying method signatures. Classes that implement an interface
at-least have features declared in the interface as they provide the actual method implementations.

In higher versions of Java fields can be declared in interfaces as well. It cannot be instantiated.
```java
interface Animal {
    void sound();
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Bark");
    }
    
    public void wagTail() {
        System.out.println("wagging tail ...");
    }
}

class Cat implements Animal {
    public void sound() {
        System.out.println("Meow");
    }
    
    public void talk() {
        System.out.println("purr.....");
    }
}
```
Here, `ani` is a polymorphic type object reference, and can point to any object that implements 
`Animal` interface:
```java
Animal ani = new Dog();
ani.sound();  // Output: Bark
```
This behavior is determined at runtime, allowing runtime polymorphism.

## Type Casting
A subclass object reference can be assigned to a parent class reference (upcasting). If needed, it
can be downcasted to the subclass type:
```java
Animal animal = new Dog();   // Upcasting
Dog dog = (Dog) animal;      // Downcasting
```
Before downcasting, it's good practice to check the object's type using the `instanceof` operator,
otherwise, wrong downcasting is reported under ClassCastException at runtime.

## Polymorphism
Polymorphism allows objects of different types to be treated as instances of a common supertype
(e.g., an interface or abstract class). It enables you to write generic code implementable on all 
current and future subclasses. It follows open-close principle as generic code does not need to 
change if more subclasses got added extending the same interface.

Downcasting and using `instanceof` operator obliterate polymorphism advantages and introduces if-else
ladder, it also annihilates adherence to open-close principle as each new subclass addition will require
a new if-else chain addition.
```
for (Animal a : animals)
    if (a instanceOf Dog)
        // do dog specific things
    if (a instanceOf Cat)
        // do cat specific things
```
if you see such code then understand it to be poorly low level designed.

## Abstract Class
An abstract class is a mix of fully implemented methods and methods that subclasses must implement.
It can be thought as a variation of interface with more capabilities.
It cannot be instantiated directly.
```java
abstract class Shape {
    abstract void draw();  // Must be implemented by subclasses
    void move() {          // Already implemented
        System.out.println("Shape moves");
    }
}
```

## Inheritance
Inheritance allows a class to acquire properties and methods of another (abstract/concrete) class.
It inherently also supports Polymorphism. Abstract class to abstract class inheritance do not enforce 
method definition which were left only declared.

It promotes code reuse, but it should only be used when there is a genuine "is-a" relationship between
classes, meaning if it makes sense to have all the features of superclass in subclass. Otherwise, 
opt for either Composition or interface implementation.

Subclass has three choices when dealing with methods inherited from a superclass:
- **Use the default behavior:** The subclass can use the inherited method as is.
- **Override the method:** The subclass can redefine the method to provide its own behavior.
- **Extend the behavior:** The subclass can use the super keyword to access the superclass's and
add its own behavior.

### Super keyword
- If the subclass needs to retain the behavior of the superclass while also adding its own logic,
it can call the superclass's method using the super keyword.
```java
class Animal {
    public void makeSound() {
        System.out.println("The animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        super.makeSound();  // Call the superclass's method
        System.out.println("The dog barks as well");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();  
        // Output: 
        // The animal makes a sound
        // The dog barks as well
    }
}
```
- `super` can be used in constructors to initialize fields or run the constructor of the superclass.
```java
class Animal {
    String name;
    
    public Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor called");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);  // Calls the superclass's constructor
        System.out.println("Dog constructor called");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");
        // Output:
        // Animal constructor called
        // Dog constructor called
    }
}
```
- a subclass can access field/method in the superclass that is hidden due to the subclass defining its own version.
```java
class Animal {
    String name = "Generic Animal";
}

class Dog extends Animal {
    String name = "Dog";
    
    public void printNames() {
        System.out.println("Animal name: " + super.name);  // Access superclass field
        System.out.println("Dog name: " + this.name);  // Access subclass field
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.printNames();
        // Output:
        // Animal name: Generic Animal
        // Dog name: Dog
    }
}
```

## Abstraction
Abstraction hides internal implementation details and exposes only necessary functionality. Abstract
classes and interfaces are the tools used to achieve abstraction in Java.

## Composition Over Inheritance
In many real-world applications, composition (where objects contain other objects) is preferred over
inheritance. Composition leads to better flexibility and maintainability.
```java
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    private Engine engine = new Engine();  // Composition
    void start() {
        engine.start();
    }
}
```
Composition introduced Dependency Injection.

# Annotations
Annotations in Java is a feature that provides a way to add metadata (extra information) to code that can be processed by the compiler or used at runtime without affecting the actual code.

## What Are Annotations?
Annotations are special markers for metadata that provide additional information about a program but don't directly affect the code's execution. Annotations can be applied to classes/interfaces/enums, methods, fields, variables, parameters, and packages to inform the compiler or runtime environment (JVM) how to handle these elements. They do not change the behavior of the annotated code directly but allow tools to process the code differently.
```java
@Override
public String toString() {
    return "MyClass";
}
```
In this example, `@Override` tells the compiler that `toString()` overrides a method in the superclass.

## Built-in Annotations in Java
Java provides several built-in annotations that are commonly used:
- `@Override`: Indicates that a method overrides a method in a superclass.
- `@Deprecated`: Marks a method as deprecated, meaning it shouldn't be used because it might be removed in future versions.
- `@SuppressWarnings`: Tells the compiler to ignore specific warnings (e.g., unchecked warnings).
- `@SafeVarargs`: Indicates that the method does not perform unsafe operations on its varargs parameters.

## Meta-Annotations
Meta-annotations are annotations that apply to other annotations. Java provides several important meta-annotations:
- `@Target`: Specifies where the annotation can be applied (allowed values - `TYPE`, `FIELD`, `METHOD`, `PARAMETER`, `CONSTRUCTOR`, `LOCAL_VARIABLE`, `ANNOTATION_TYPE`, `PACKAGE`, `TYPE_PARAMETER`, `TYPE_USE`).
- `@Retention`: Defines how long the annotation information is kept (allowed values - `SOURCE`, `CLASS`, `RUNTIME`).
- `@Documented`: Marks the annotation for inclusion in Javadoc.
- `@Inherited`: Allows subclasses to inherit the annotation from the parent class.
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCustomAnnotation {
    String value();
}
```

## Creating Custom Annotations
You can define your own annotations in Java by using the @interface keyword. Custom annotations can have elements (like properties) that act as parameters.
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name();
    int value() default 1;
}
```
- `@Target(ElementType.METHOD)` means this annotation can only be applied to methods.
- `@Retention(RetentionPolicy.RUNTIME)` means the annotation will be available at runtime for reflection.
You can apply this annotation to a method like -
```java
@MyAnnotation(name = "test", value = 5)
public void myMethod() {
    // Method implementation
}
```

## Annotation Elements
Annotation elements are similar to method declarations but act as parameters for the annotation. They can have:
- **Default values:** If not provided, the default value is used.
- **Restrictions:** Only primitive types, String, Class, enums, and annotations can be used as element types.
```java
public @interface CustomAnnotation {
    String name();
    int age() default 18; // default value
}

// usage
@CustomAnnotation(name = "John")
public class MyClass {
    // Class code
}
// In this case, age will default to 18 unless specified.
```

## Retention Policy
The retention policy determines when the annotation is available:
- **SOURCE:** Annotations are discarded by the compiler and not included in the compiled `.class` file. They are only useful during development (e.g., `@Override`, `@SuppressWarnings`).
- **CLASS:** Annotations are stored in the `.class` file but not available at runtime (e.g. `@Deprecated`).
- **RUNTIME:** Annotations are available at runtime, allowing them to be accessed using reflection. This is essential for frameworks like Spring.
```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRuntimeAnnotation {
}
```

## Processing Annotations with Reflection
Annotations with `@Retention(RetentionPolicy.RUNTIME)` can be accessed at runtime via Java Reflection. This is useful for frameworks like Spring, which use annotations to configure and manage beans dynamically.
```java
@MyAnnotation(name = "test", value = 5)
public class MyClass {
    public static void main(String[] args) throws Exception {
        MyClass obj = new MyClass();
        Method method = obj.getClass().getMethod("myMethod");

        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            System.out.println("Name: " + annotation.name());
            System.out.println("Value: " + annotation.value());
        }
    }

    @MyAnnotation(name = "test", value = 5)
    public void myMethod() {
        // Some code
    }
}
```
