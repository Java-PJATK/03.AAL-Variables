# 3.AAL-Variables

### Examples of Creating and Using Variables

The program below illustrates various aspects of variable creation and usage:

## Listing 3 AAL-Variables/Variables.java Page 14

```java
public class Variables {
    public static void main(String[] args) {
        int ifour = 4;
        double xhalf = 0.5;
        double four = ifour; // automatic conversion
        
        // int badFour = 4.0; // WRONG
        
        int k = 1, m, n = k + 3;
        m = 2;
        final double two = xhalf * ifour;
        
        // two = two + 2; // WRONG
        
        boolean b = true;
        if (b) System.out.println(
            "k=" + k + ", m = " + m + ", n = " + n +
            "\nSum by 4 is equal to " + (k + m + n) / ifour);
        
        String john, mary = "Mary";
        john = "John";
        System.out.println(john + " and " + mary);
        
        john = mary;
        System.out.println(john + " and " + mary);
    }
}
```

Output:
```
k=1, m = 2, n = 4
Sum by 4 is equal to 1
John and Mary
Mary and Mary
```

Note that variables `john` and `mary` are not objects of type `String` — they are references (pointers) whose values are addresses of such objects! Therefore, `john = mary` means that we copy the address of the object corresponding to "Mary" to the variable `john`; from now on, both `john` and `mary` refer to exactly the same object somewhere in memory. The object previously referred to by the variable `john` is now lost (because we have lost its address) and can be garbage collected.

### 3.4 Conversions

Sometimes a value of one type should be used as a value of another type. Creating a value of one type based on a value of another type is called conversion or casting. Of course, it is impossible to change the type of a variable: conversions always involve values. For example, in

```java
int a = 7;
double x = a + 1;
```

the value on the right-hand side in the second line is of type `int`, and a `double` is needed to initialize the variable `x`. However, the compiler will silently convert the `int` value to the corresponding `double` value and assign it to `x`. Such conversions, performed automatically by the compiler, are called implicit conversions. Generally, they will be performed if they don’t lead to a loss of information. Conversion in the opposite direction, as shown below, will not be performed:

```java
double x = 7.7;
int a = x; // WRONG
```

This is because an `int` occupies four bytes and has no fractional part, while `double`s have a fractional part and occupy eight bytes. Hence, conversion from `double` to `int` would lead to inevitable loss of information. We can, however, enforce the compiler to perform such conversions (taking the responsibility for possible consequences). We do it by specifying, in parentheses, the name of the type we want to convert to:

```java
double x = 7.7;
int a = (int) x; // now OK
```

Of course, after conversion, `a` will be exactly 7, as there is no way for an `int` to have a fractional part. Note also that this conversion does not affect the variable `x` as such: its type is still `double`, and its value is still 7.7.

The exact rules of conversions are more complicated, but the general principle is that conversions from “narrow” types to “wider” are performed implicitly (`byte, char, short → int, int → float, long → double`, etc.), while conversions in the opposite direction must be explicit.

### 3.5 The Stack and the Heap

Let us briefly explain what the stack and the heap are.

Both are parts of memory that are available to the program at runtime. The stack is simpler: when a new value (e.g., of a variable) is to be added, it is always added at the “top” of the stack. The address of the current “top” of the stack is always available at runtime: actually, there is a special register of the processor dedicated only to store information on the current location of the top of the stack. In particular, no “looking for enough room in memory” is involved.

Whenever we create a new variable inside a block of code (e.g., inside a function), its value is pushed onto the stack. Then, every time the flow of control leaves the block (e.g., a function exits), all the variables pushed onto the stack in this block are freed (deleted) in the reverse order as they were pushed, and the stack is, as we say, rewound — the top of the stack is again at the position it had before entering the block. This means that all variables declared inside a block are lost forever when we leave it: they are all local. The process of removing values from the stack is called “popping off the stack”.

The advantage of using the stack to store variables is that memory is managed for you automatically. We don’t have to allocate memory by hand or free it once we don’t need it anymore. Also, the CPU organizes stack memory very efficiently: reading from and writing to the stack is very fast.

The heap is a region of memory that is not managed automatically. When the program asks the system to store some data on the heap, the system searches for free space there, writes this data in this location, marks this region as occupied, and returns its address — this address can be stored in a variable of a reference type (which itself may be on the stack).

Note that if the value of this variable is lost (for example, because it was a local variable in a block), the data on the heap it referred to is no longer available, as we have lost its address! In Java, such an unavailable object on the heap may eventually be freed automatically by the process of the so-called garbage collector.

Unlike the stack, variables created on the heap are accessible not only locally but wherever their address is known. Heap memory is slower to be read from and written to because one has to use pointers (which contain addresses) and follow them to access memory on the heap. Also, the process of allocating memory on the heap is rather complicated and time-consuming.
