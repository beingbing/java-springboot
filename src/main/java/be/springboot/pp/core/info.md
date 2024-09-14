# Object Oriented Programming with Java
4 pillar of OOPS are -
- Encapsulation
- Abstraction
- Polymorphism
- Inheritance

## Class
Books say it is a blueprint/skeleton for defining Objects, but practically , it is something where we bundle related data and algorithms together.
When earlier engineers were working with procedural languages like C, they were writing everything in one file, and then realized particular section of code
interacts only with a particular section of data, so they extracted those out and put in a separate file and called that file a Class.
When Java was being created on OOP structure, this feature of putting related data and algorithms in a separate file and naming that entity as Class was central to it.
There are 6 categories of class which can be defined in Java-
- concrete Class
- Interface
- Enum
- abstract Class
- Annotation
- Final Class

## Constructor, Object and Pointer
Constructor is used to create instance of a class.
Instance: snapshot of a class, in technical terms, Object.
When we invoke constructor and give values of required Class fields at that time inside RAM some memory is alloted and certain values are kept against those fields.
Now we can say we have an object floating around in my system. But saying that it is floating doesn't make sense, where is it actually ?
Whenever a new object is created a pointer referencing it is also created separately in different block of memory. Our systems have a particular type of memory
called Heap memory present within RAM, all the objects we create are created inside Heap memory.
Object referencing pointer is not created inside heap memory it is created in stack memory, and make to point at newly created object.
If we want we can make it reference pointer point to any other object of same type as well.
Using that pointer, objects fields and methods can be accessed using dot-operator.
If constructor do not take any initialization arguments then all fields will be initailized with default values already decided by JRE when code will be executed.
If we do not declare a constructor, in that situation too JRE uses default constructor created by JDK during class loading, that default constructor functions
as a no-args constrcutor to help JRE to initialize fields with default values.
It is possible to have multiple constructors in a single class called constructor overloading, but all of them need to have different signature.

## access modifiers
- public: can be accessed from anywhere
- protected: can be accessed from same package/module
- private: can be accessed in class only.

## Encapsulation
If we put an invalid value to a field of an object which logically/practically do not make any sense then it is called violation of data-integrity/data-constraint.
Whosoever is writing a class, has to responsibly ensure that data integrity violation can not be done by others.
We can prevent that by using access modifier, as name states, it modifies the access of a field/method.
By default all fields are kept as public, meaning once object is created those fields can be accessed using '.' operator from anywhere.
If this is to be prevented, we need to put private in front of field declaration. Once that's done, nobody will be able to access said fields outside the calss
using dot-operator on object.
but then how will others working on objects of our class in their class environment will be able to access our class fields.
For that we can declare getters and setters.
So, what we did here is , we made all fields private and made them accessible via public getters and setters. This is known as data hiding.
We hid data from unintended or malicious changes. This entire setup which we created is known as 'Encapsulation'.
It represents making data private and aloowing data access by public getters and setters.
After doing that we can write rules for data integrity in setters to prevent out data fields from getting corrupted from a malicious change.
The same data integrity rules can be setup during object initialization via constructor as well.

## final keyword
final: can not be changed. While using ita value needs to be assigned at the time of declaration, and that value can not be changed, also it is not allowed to not
give a value while using final keyword.
There are two categories of data-types, primitive and references(user-defined). Making a field final means, value can not be changed for primitive,
This is straightforward true, but for references, it means the object they are referencing will be the same object they will keep referencing during their
entire lifecycle. The reference is fixed but the object fields can still be modified according to data-integrity rules using methods.
As fields declared inside class referenced by referencing pointer are not assigned final keywrod hence modification of their values is permitted, only the reference
of object created is locked in.
But if we declare field variables to be final as well, then our class methods re prevented to make any changes to those fields during compile-time only and we will be
forced to give values to those 'final' fields at the time of object creation via constructor or define their values inline during declaration.
Hence, we can not have setters for our final fields, we are forced to initate final fields during object creation because we won't be able to do so after object is created.
Extending functionality of final keyword to its ultimate form gave us a concept of Immutable objects.
Immutable means something that can't be mutated, and mutation means cahnge, so in essence, Immutable objects mean an object which can't be modified or changed
after it has been created. To create such an object we need to declare all fields of the class while declaring the blueprint to be recursively final, it means,
if class contains a reference to an object of other class then fields of that class also needs to be declared with final.
As an example of its use case, We use Immutable objects during concurrency, the shared objects are declared to be final s o that it do not hamper results of
Threads running parallely by corrup data which got modified by som other Thread. Hence we do not allow modification to shared objects once they are created.
that's how Immutable objects prevent race condition in essence.

## static keyword
Normally when we create a class all fields can have different values for different objects because they create a unique characteristic of that object.
Hence, a field is normally created at object level, to deal with object.
But we also may require sometimes for all objects of a particualr class to share same entity. As every object will get a separate field to keep its value, but if a field
is declaredstatiic, none ofthe objects created will get a separte field instead they all will share the same field, and that field will be kept
at the level of class. So, to access that field instead of using dot-operator on object reference, we need to use dot-operator on class-name.
Because if a field is at a class level hence we can access it without having to ever created any object of that class, vi-a-vis if objects of that
class exist and instaead of using class-name we use object reference pointer to access static class, it will work as it would have
worked with class-name access. If a static is made private, we can access it via a getter, but they will be static as well.
Static fields are initatated when classes are loaded in-memory oafter code is compiled. Those loaded files are then read by JVM and load those blueprint of classes in JRE.
At that time static fields are initaited.
static+final is prominently used when we want global immutable values in our JRE. JVM keeps static fields and loaded classes in a memory called as 'meta-space'.
If we declare a method to be static then only static fields or methods can be used in that method.
This is because a static method can be invoked before any object is created, hence object specific fields or methods do not exist
in JRE at that time. In short, in static context everything should be static.

## Polymorphism
What we saw till now are concrete class and final class (Immutablr objects). Now we will talk about interfaces.
Interface: contains signature of a method related to qualities and behaviour of that entity. Interfaces are effective in defining details around which
extending classes will establish functionality. And classes will be forced to atleast have implementation of those specifications.
Polymorphism literally means multiple forms, it means an object reference, which can point to objects of more than one type,
subjected to the condition that all of those classes extends same interface, mean belonging to same entity. example,
interface Animal can have an object reference pointing to object of concrete sub-class of Animal like Cat, Dog, etc.
In this case Animal inteface object reference is polymorphic pointer introducing the essence of polymorphism.
Polymorphism alllows you to write generic code which can be worked on all sub-classes  extending the said interface.
Polymorphism supports open-close principle as generic code do not need to change if more sub-classes got added extending the same interface,
hence keeping code open for extension but closed for modification. Internally Java finds out what is the exact implementation class of Polymorphic reference,
using that all implementation are invoked.
This identifcation of iplementation is done by Java in FRE during execution, hence, it is said that behavior is defined for such reference
at runtie but behavior are declared, defined and implemented at compile time only. Here we need to keep in mind that subclass object reference are not
polymorphic, they can only point to objects of their class-type, not even to objects pointed by their parent interface object refernce.
If you want a sub-class object reference to point to an object refrerence by super-class object reference then you have to anually
type-case that reference to that sub-class type.
This type-casting process is evalutated at runtime hence, if you type-case wrong, you will know about it only at the runtime under class-cast-exception.
We can add extra features to sub-classes as well apart from whatever we inherited from super-class, which will be specific to that sub-class only.
but a polymorphic reference can only access fields/methods whic are declared in its definition, it can not access sub-class
specific fearures, even though it is pointing to a sub-class object, and if you still want to access sub-class specific features then you need to type-case
and access those fearures using dot-operator on sub-class specific object reference pointer.
For that casting at runtime you first need to check if type-casing is possible that you can do using instanceOf operator which if conrms
polymorhpic object reference is pointing to an object of desired sub-class type then you can type-cast to use sub-class specific features.
But such checks introduces if-else ladder stemming out of sub-class usage and needs which do not leave code as generic anymore and foregos open-close
principle, example -
for (Animal a : animals)
if (a instanceOf Dog)
// do dog specific things
if (a instanceOf Cat)
// do cat specific things

with these type of coding practice every new implementation of Animal interface will have required to redo this code snippet. So, if you see any code
snippet needing to use instanceOf checks then understand it to be poorly low level designed.

## Interfaces
In higher versions of Java we can declare fields in interfaces as well but there was a time when it was not possible. Se, we declare a class when we find
both data and methods needs to exist in it and all of it can be inherited in other classes otherwise we implemented different interfaces in a required
class. We should use inheritance of classes only if everything present in supre-class will be useful in sub-class as well, otherwise either go with
Composition or use interfaces. In inheritance as well, polymorphism is supported, we can create a sub-class object and reference to it can be stored in
supre-class object. Due to this we can say Inheritance of class is a specific type of polymorphism. Abstract class similarly is a specific type of
interface where some methods are defined but others are left declared for sub-classes to override and implement.
In inheritance, childs have access to parents fields/methods but viec verso is not true. Also child classes can override behavior of any parent
class method. Hence, same method called from object reference of different child/parent object will have different behavior. This becomes super useful
when parent class has default implementation. So now it is on child to either use that as it is or modify it.
But there comes a third scenario whre child also needs to run code written in parent's default implementation but also add on to it some extra fuctionality,
in that case we use super keword, we can do super.methodName() and var v = super.v in methdoName() method.
There is one more use case of super keyword, which is constructor chaining or constructor initialization of parent class.
We can do that by writing super(args...) in child constructor as first line of its implementation. There is a special case when child do not have any constructor
in that case a default constructor is called and which in turn calls default constructor of parent and vis-a-vis if we create our own constructor then it is
our responsibility to call parent constructor as well. As we talked tabstract class is a specific type of interface where we know implementation of some
methods and we leave out other methods only declared for child inheriters to implement them by over-riding.
An abstract class can inherit another abstract class and over-riding will not be inforced but as soon as a child class is found to be concrete then it will
be forced to implement all method signatures where were declared but never defined.
Abstract classes like interfaces can not be instanticated. To reiterate when all you need isto define behavior then go with interface
as it can nlt contain a method with body, it can only declare contracts.
But if you want to have some implementations along with declared but not defined behaviour go with abstract lass.
Both of them are ways of doning polymorphism, but abstract class has features of both of a class and an interface, and both of abstract class and
interfaces can't be initialzied.

## Abstraction
Abstraction: hiding of details, when you don't want user to worry about implementation and just to be aware about the features and how to use it.
All super-types ar abstraction, they hide sub-types and their implementation details.
It is suggested that super-types must be at the front and easy to use and understand.

It is suggested to not go with inheritance in its absolute form which means, if you need fearures of a class, do not inherit it, instead declare a field of that class-type
and use those features via that field object reference. This is called class Composition.
And in general concencus, Composition is way more adopted and used then inheritance to the extent that inheritance rearely found in real-wrold production code.
The fall of  inheritance is credit to some ot the militations like, difficulty in debugging. We need to drill down the inheritance heirarchy to look for
the function causing problems.
Compostion leads to the practice of dependency injection, where objects of dependent classes are created by parent class and injected into child.