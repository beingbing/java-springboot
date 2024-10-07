# Array
An array is an area of heap memory with continuous elements space. An array is an ordered list. Read `[]` as array. Data-type kept before `[]` is what goes in it.

## Creation
```java
int[] nums = new int[3]; // all 3 elements are initialized with default value (0)
int[] ams = new int[] {42, 55, 67}; // giving initialization values.
int[] ams = {42, 55, 67}; // shorthand
```

### valid array declarations
```java
int[] nums = new int[3];
int [] nums = new int[3];
int []nums = new int[3];
int nums[] = new int[3];
int nums [] = new int[3];
```

## Print an Array
```
System.out.println(Arrays.toString(ams));   // [42, 55, 67]
System.out.println(ams.length); // 3
```

## Array Sorting
```
Arrays.sort(nums);
```

## Array searching
Java provides binary search on sorted array. If element is found then its index will be returned. If not then negative value will be returned showing one smaller than the negative value of the index, where a match needs to be inserted to preserve sorted order.
```
int[] nums = {2, 4, 6, 8};
System.out.println(Arrays.binarySearch(nums, 2));   // 0
System.out.println(Arrays.binarySearch(nums, 4));   // 1
System.out.println(Arrays.binarySearch(nums, 1));   // -1
System.out.println(Arrays.binarySearch(nums, 3));   // -2
System.out.println(Arrays.binarySearch(nums, 9));   // -5
```

## Array comparing
for comparison, arrays data-type should be same.
```
System.out.println(Arrays.compare(new int[] {1}, new int[] {2})); // negative number
```
A negative value means first different element in first-array < second-array. Zero means both are equal. A positive value means first-array > second-array.

## Finding mismatch in arrays
return -1 if both arrays are equal, otherwise first index where they differ is returned. It is case insensitive
```
System.out.println(Arrays.mismatch(new int[] {1}, new int[] {2})); // 0
```

## passing arrays in methods as arguments
```java
public void method(String[] args);
public void method(String args[]);
public void method(String... args); // a method can have max 1 varargs param and
// it should be the last one in the params list
```
