# Strings
Both `String` and `StringBuilder` implements `CharSequence` interface. `String` is immutable. Invoking a `String` method will return a new String object rather than changing the value of current instance.  

## Creation
different ways of creating a string -
```java
String name = "samar";
String name = new String("samar");
String name = """
                samar""";
```

## String operation
rules for String concatenation -
- If both operands are numeric, + means numeric addition.
- If either operand is a `String`, + means concatenation.
- the expression is evaluated left to right
```
System.out.println(1 + 2);          // 3
System.out.println("a" + "b");      // ab
System.out.println("a" + "b" + 3);  // ab3
System.out.println(1 + 2 + "c");    // 3c
System.out.println("c" + 1 + 2);    // c12
System.out.println("c" + null);     // cnull
```

## `String` methods
### Determining Length
```
public int length();

var name = "animals";
System.out.println(name.length()); // 7
```

### Getting a Single Character
String index starts from 0 and goes till length-1. Throws exception if queried for illegal index
```
public char charAt(int index);

var name = "animals";
System.out.println(name.charAt(0)); // a
System.out.println(name.charAt(7)); // throws 'StringIndexOutOfBoundsException'
```

### finding an index
finds the first index that matches the desired value. Returns -1 if queried string is not found
```
public int indexOf(int ch);
public int indexOf(int ch, int fromIndex);
public int indexOf(String str);
public int indexOf(String str, int fromIndex);

var name = "animals";
System.out.println(name.indexOf('a'));      // 0
System.out.println(name.indexOf("al"));     // 4
System.out.println(name.indexOf('a', 3));   // 4
System.out.println(name.indexOf("al", 5));  // -1
```

### Getting a Substring
starting index is inclusive, but ending index is excluded.
```
public String substring(int beginIndex);
public String substring(int beginIndex, int endIndex);

var name = "animals";
System.out.println(name.substring(3));      // mals
System.out.println(name.substring(2, 4));   // im
System.out.println(name.substring(3, 3));   // empty-string
System.out.println(name.substring(3, 2));   // throws exception
System.out.println(name.substring(3, 8));   // throws exception
```

### Adjusting Case
```
public String toLowerCase();
public String toUpperCase();

var name = "animals";
System.out.println(name.toUpperCase()); // ANIMALS
```

### Checking Equality
Two strings contains same characters in same order. Never use `==`, it checks pointed reference instead of character sequence.
```
public boolean equals(Object obj);
public boolean equalsIgnoreCase(String str);

System.out.println("abc".equals("ABC"));                // false
System.out.println("abc".equalsIgnoreCase("ABC"));      // true
```

### Searching for Substrings
```
public boolean startsWith(String prefix);
public boolean endsWith(String suffix);
public boolean contains(CharSequence charSeq); // replacement of (str.indexOf(otherStr) != -1)

System.out.println("abc".contains("B")); // false
```

### Replacing Values
```
public String replace(char oldChar, char newChar);
public String replace(CharSequence target, CharSequence replacement);
```

### Removing Whitespace
`strip()` does everything `trim()` does + supports Unicode.
```
public String strip();
public String stripLeading();
public String stringTrailing();
public String trim();

String text = " abc\t ";
System.out.println(text.trim().length());           // 3
System.out.println(text.strip().length());          // 3
System.out.println(text.stripLeading().length());   // 5
System.out.println(text.stringTrailing().length()); // 4
```

### Check for Empty or Blank Strings
```
public boolean isEmpty();
public boolean isBlank();

System.out.println(" ".isEmpty());  // false
System.out.println("".isEmpty());   // true
System.out.println(" ".isBlank());  // true
System.out.println("".isBlank());   // true
```

# StringBuilder
mutable String. It modifies own state and returns a reference to itself. `substring()` on it returns an immutable String. `indexOf()`, `length()` and `charAt()` works the same.

## Creation
```java
StringBuilder sb = new StringBuilder("samar");
StringBuilder emptyStr = new StringBuilder();
StringBuilder strFixedCap = new StringBuilder(10);
```

## Appending
```
public StringBuilder append(String str); // it has many signatures for all data types

StringBuilder str = sb.append(" taj");
str = str.append(" shaikh").append(" !!");

System.out.println(sb);     // samar taj shaikh !!
System.out.println(str);    // samar taj shaikh !!
```
both sb and str are referencing to the same string object.

## Insertion
insert at given index and returns reference of current `StringBuilder`
```
public StringBuilder insert(int index, String str); // it too has multiple signatures

StringBuilder sb = new StringBuilder("animals");
sb.insert(7, '-');
sb.insert(0, '-');
sb.insert(4, '-');
System.out.println(sb); // -ani-mals-
```

## Deletion
```
public StringBuilder delete(int startIndex, int endIndex);
public StringBuilder deleteCharAt(int index);

StringBuilder sb = new StringBuilder("abcdef");
sb.delete(1, 3);        // sb = adef
sb.deleteCharAt(5);     // throws an exception
```

## Replacing Portions
It works in parts, first deletion happen then insertion.
```
public StringBuilder replace(int startIndex, int endIndex, String insertionString);

StringBuilder sbStr = new StringBuilder("Elephant restaurant");
sbStr.replace(3, 6, "menta");
System.out.println(sbStr);  // Elementant restaurant
```

## Reversing
```java
public StringBuilder reverse();
```

## String from StringBuilder
```java
public String toString();
```

## Checking equality
`StringBuilder` do not have `equals()` or `==` implementation, you need to convert it to a `String` then do the checking using `equals()`

# String pool
String literals created at compile time are all kept in a pool. Any string operation that creates a new immutable string (or `new` keyword is used) which will be added in the pool. It is possible to create exactly the same new String at runtime which was already added in String pool at compile-time. Now as both are two different string literals even though with same values, will still give false on equality check. To remedy it use `intern()`
```
String name = "Samar Taj"; // created at compile-time in string pool
String fullName = new String("Samar Taj").intern(); // created at runtime, 
// but instead of creating a new literal, String pool in referred to check
// if a String of this kind already exist, if yes then reference to same is returned.
System.out.println(name == fullName);   // true
```
