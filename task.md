# Practice Practical Assessment 2

## INSTRUCTIONS TO CANDIDATES

1. The total mark is 40.  We may deduct up to 4 marks for violating of
   style.

2. This is a CLOSED-BOOK assessment. You are only allowed to refer to one
   double-sided A4-size paper.

3. You should see the following files/directories in your home directory.

   * The subdirectories Q1a, Q1b, Q1c, and Q2.

   * ListAPI.md, StreamAPI.md and ComparatorAPI.md contain the API for
     Java's List, Stream, and Comparator, respectively.

   * For Question 1,

       * test.jar contains the automated tests Test1, Test2, Test3 for testing.

       * Q1a/Value.java is a skeleton code for you to complete.

       * Q1b/Transformer.java and Q1c/Transformer.java are provided to you
         to solve Question 1.

       * Q1c/BooleanCondition.java is provided to you to solve Question 1.

   * For Question 2,

       * test.jar contains the automated tests Test4 for testing.

       * Q2/Demo.java to demonstrate the use of the methods you will implement.

       * Q2/Flat.java is provided to you to solve Question 2.  You should
         not change this class.

       * Q2/Q2a.java to Q2/Q2d.java contain working code that you need to change
         to solve the question.

4. Write your student number on top of EVERY FILE you created or edited as
   part of the @author tag. Do not write your name.


5. To compile your code, run

   javac -Xlint:unchecked -Xlint:rawtypes *.java.

   You can also compile the files individually if you wish.

## Question 1 (24 marks): Generics

In this question, we will write a container for values that can either
be processed sequentially with a single possible outcome, or branched
into two distinct outcomes based on a condition.  The branches can be
merged back into a single outcome if certain conditions are met.  It
provides functional-style operations to map transformations and
conditionally branch the stored value into true and false cases for
further processing.

Use of generic classes and methods are required for this question.
The type arguments and the return type of methods are not always
specified in the question.  If so, you should determine the correct
type arguments to use in declaring your classes and methods and the
correct return type to use for the methods.  Use wildcards
appropriately to make your classes and methods as general as possible.

Build up your solution incrementally in different parts of this
question, by solving them under different subdirectories `Q1a`, `Q1b`,
and `Q1c`.  The files under these subdirectories will be graded
independently.

### Part (a) (8 marks)

**In the `Q1a` subdirectory**, create an abstract generic class `Value`
with the following specifications:

- `Value` contains two abstract methods `getIfTrueValue` and
  `getIfFalseValue` that take no arguments and return the corresponding
  values of the branch.

- `Value` contains an abstract method `isSequential` that takes no
  arguments and returns a boolean value.

- `Value` contains a private static nested generic class `Sequential`.
  The class `Sequential` must:

  - inherit from `Value`

  - contain a private field `value` of a generic type

  - have a constructor that initializes the `value`

  - provide the implementation for `getIfTrueValue` and
    `getIfFalseValue`, both methods return the `value`

  - override the implementation for `toString` to return the string
    representation of `value`

  - override the implementation for `isSequential` to return `true`

- `Value` contains a static factory method `sequential` that creates
  and returns a `Sequential` instance.

Write your class so that it behaves as follows:

```text
jshell> new Value<>("hello")
|  Error: Value is abstract; cannot be instantiated
jshell> new Value.Sequential<>("hello")
|  Error: Value.Sequential has private access in Value
jshell> Value.<String>sequential("hello")
$.. ==> hello
jshell> Value.<Integer>sequential("hello")
|  Error: incompatible types: java.lang.String cannot be converted to java.lang.Integer
jshell> Value.sequential(5).toString()
$.. ==> "5"
jshell> int x = Value.sequential(5).getIfTrueValue()
x ==> 5
jshell> int x = Value.sequential(5).getIfFalseValue()
x ==> 5
jshell> Value.sequential(5).isSequential()
$.. ==> true
```

You are encouraged to test your code in jshell first, following the
commands and interactions above. When you are ready, run the automated
test provided in Test1:

```
$ java -cp ../test.jar:. Test1
```

### Part (b) (4 marks)

Copy the file `Value.java` from subdirectory `Q1a` to subdirectory
`Q1b`.  **In the subdirectory `Q1b`**, update `Value.java` with the
following additions.

Now that we have a way to encapsulate a value, we can apply a
transformation to the value.  Add an abstract method `map` to the
`Value` class.  The method takes a lambda expression of type
`Transformer` and returns a new `Value` instance.

Implement the `map` method in the `Sequential` class so that a new
`Value` instance containing the transformed value (after applying the
given lambda expression) is returned.

Write your class so that it behaves as follows:

```text
jshell> Value<Integer> v = Value.sequential("hello").map(s -> s.length())
v ==> 5
jshell> Value.sequential("hello").map(s -> s.length()).isSequential()
$.. ==> true
jshell> Value.sequential("hello").<Integer>map(x -> x + 1)
|  Error: incompatible types: bad return type in lambda expression
    java.lang.String cannot be converted to java.lang.Integer
jshell> Value<Number> v = Value.<Number>sequential(1).map((Object o) -> o.hashCode())
v ==> 1
jshell> v.getIfTrueValue()
$.. ==> 1
jshell> v.getIfFalseValue()
$.. ==> 1
jshell> Value.sequential(1).map(x -> x + 1).map(x -> x * 2)
$.. ==> 4
```

You are encouraged to test your code in jshell first, following the
commands and interactions above. When you are ready, run the automated
test provided in Test2:

```
$ java -cp ../test.jar:. Test2
```

### Part (c) (12 marks)

Now that we have a way to encapsulate a value in a program and perform
computation on the value, we want to create a branch of two possible
outcomes based on a given condition.

Copy the file `Value.java` from subdirectory `Q1b` to subdirectory
`Q1c`.  **In the subdirectory `Q1c`**, update `Value.java` with the
following additions.

- Add an abstract method called `branch` that takes a `BooleanCondition`
  `cond` and two `Transformer` expressions.

- Add an abstract method called `merge` that takes a `Comparator`.

- Implement a private static nested generic class `Branch` in the
  `Value` class.  The class `Branch` must:

  - inherit from `Value`

  - contain two private fields, of the same generic type, that store
    the values for the true and false branches

  - have a constructor that initializes the two values

  - provide the implementation for `getIfTrueValue` and
    `getIfFalseValue` that returns the value of the true/false branch
    respectively

  - provide the implementation for `toString` to return the string
    representation of both values, as per example output below

  - provide the implementation for `branch` to throw an
    `UnsupportedOperationException` (this exception is provided by
    Java -- you do not have to declare it)

  - provide the implementation for `map` to map both true and false
    values with a given transformer

  - provide the implementation for `merge` to collapse both branches
    into a single value.  `merge` should return an instance of
    `Sequential` with the value of the true branch if the comparator
    returns 0.  It returns the calling `Branch` object unchanged
    otherwise.

  - provide the implementation for `isSequential` to return `false`

- Implement a method `branch` in the `Sequential` class that takes a
  `BooleanCondition` `cond` and two `Transformer` expressions `t1` and
  `t2`.  It produces two possible values based on the condition:

  - If the condition is true, the true value is computed by applying
    `t1` to the current value; the false value is computed by applying
    `t2` to the current value.

  - On the other hand, if the condition is false, the true value is
    computed by applying `t2` to the current value; the false value is
    computed by applying `t1` to the current value.

  The method returns an instance of a `Branch`.

- Implement a method `merge` in the `Sequential` class that does
  nothing and simply returns the calling `Sequential` object unchanged.

Write your class so that it behaves as follows:

```text
jshell> Value.sequential(+1).branch(x -> x > 0, x -> "+ve", x -> "-ve")
$.. ==> T: +ve, F: -ve
jshell> Value.sequential(-5).branch(x -> x > 0, x -> "+ve", x -> "-ve")
$.. ==> T: -ve, F: +ve
jshell> Value.sequential(+1).branch(x -> x > 0, x -> "+ve", x -> "-ve").isSequential()
$.. ==> false
jshell> Value<String> v1 = Value.sequential(-5).branch(x -> x > 0, x -> "+ve", x -> "-ve")
v1 ==> T: -ve, F: +ve
jshell> v1.map(str -> str.toUpperCase())
$.. ==> T: -VE, F: +VE
jshell> v1.branch(s -> s.equals("+VE"), s -> 1, s -> 0)                                        
jdk.jshell.EvalException
	at Value$Branch.branch(#4:1)
	at .(#12:1)
jshell> Transformer<Object, Integer> t1 = obj -> obj.hashCode()
t1 ==> $Lambda$/0x..@########
jshell> Transformer<Object, Integer> t2 = obj -> -obj.hashCode()
t2 ==> $Lambda$/0x..@########
jshell> Value<Integer> v2 = Value.<Number>sequential(1).branch(x -> x.intValue() > 0, t1, t2)
v2 ==> T: 1, F: -1
jshell> v2.getIfTrueValue()
$.. ==> 1
jshell> v2.getIfFalseValue()
$.. ==> -1
jshell> Value<Integer> v3 = v2.merge((x, y) -> (x == -y) ? 0 : 1)
v3 ==> 1
jshell> v3.isSequential()
$.. ==> true
jshell> v3.getIfTrueValue()
$.. ==> 1
jshell> v3.getIfFalseValue()
$.. ==> 1
jshell> Value<Integer> v4 = v2.merge((x, y) -> (x == y) ? 0 : 1)
v4 ==> T: 1, F: -1
jshell> v4.isSequential()
$.. ==> false
jshell> v4.getIfTrueValue()
$.. ==> 1
jshell> v4.getIfFalseValue()
$.. ==> -1
```

You are encouraged to test your code in jshell first, following the
commands and interactions above. When you are ready, run the automated
test provided in Test3:

```
$ java -cp ../test.jar:. Test3
```

## Question 2 (16 marks): Streams

Under the subdirectory `Q2`, you are provided a class `Flat`
which represents an HDB rental unit. Each flat has a town name,
number of rooms, monthly rent, and a list of nearby MRT stations.

You are also provided four classes `Q2a`, `Q2b`, `Q2c` and `Q2d`
each with a method that uses traditional loops to process data from
Singapore's HDB rental listings. Each method operates on a list of
`Flat` objects.

Your task is to refactor each method into a single Java Stream
pipeline by editing these classes.  You can either comment out the
given code or delete the given code.

**IMPORTANT:** For the rest of this paper, all methods written
should follow the following constraints:

- The method body must be exactly one statement, specifically a
  return statement.

- The method body must not contain any loop or conditional statement.

- The method body must not use any lambda expression with blocks
  (e.g., `() -> { .. }`.)

Useful APIs are provided in the files `ListAPI.md` and
`StreamAPI.md`.

You can test your implementation by running the following commands.
```
$ java -cp ../test.jar:. Test4
```

### Part (a) (3 marks): getAllTownNames

This method takes in a list of flats and returns an alphabetically
sorted list of all distinct town names.

Write your method so that it behaves as follows:

```text
jshell> List<Flat> flats = List.of(
..>     new Flat("Woodlands", 3, 1200.0, List.of("Woodlands MRT")),
..>     new Flat("Woodlands", 4, 1500.0, List.of("Admiralty MRT", "Woodlands MRT")),
..>     new Flat("Tampines", 3, 1300.0, List.of("Tampines MRT")),
..>     new Flat("Tampines", 5, 1800.0, List.of("Tampines MRT", "Tampines East MRT")),
..>     new Flat("Jurong", 2, 1000.0, List.of("Boon Lay MRT")),
..>     new Flat("Jurong", 2, 950.0, List.of("Lakeside MRT")),
..>     new Flat("Hougang", 3, 1250.0, List.of("Hougang MRT", "Kovan MRT"))
..> );
flats ==> [3-room in Woodlands ($1200.0), 4-room in Woodlands ($1500.0), 3-room in Tampines ($1300.0), 5-room in Tampines ($1800.0), 2-room in Jurong ($1000.0), 2-room in Jurong ($950.0), 3-room in Hougang ($1250.0)]
jshell> Q2a.getAllTownNames(flats);
$.. ==> [Hougang, Jurong, Tampines, Woodlands]
```

### Part (b) (3 marks): getTotalRentByRoomType

This method takes in a list of flats and a room count (e.g. 3), and
returns the total rent for all flats with that number of rooms.

Write your method so that it behaves as follows (continuing from the
previous jshell input):

```text
jshell> Q2b.getTotalRentByRoomType(flats, 2);
$.. ==> 1950.0
jshell> Q2b.getTotalRentByRoomType(flats, 3);
$.. ==> 3750.0
```

### Part (c) (5 marks): getFlatsInGivenTowns

This method takes in a list of towns and a list of flats, and returns
all flats in the selected towns, sorted by rent in ascending order.

Write your method so that it behaves as follows (continuing from the
previous jshell input):

```text
jshell> List<String> selectedTowns = List.of("Tampines", "Woodlands");
selectedTowns ==> [Tampines, Woodlands]
jshell> Q2c.getFlatsInGivenTowns(selectedTowns, flats);
$.. ==> [3-room in Woodlands ($1200.0), 3-room in Tampines ($1300.0), 4-room in Woodlands ($1500.0), 5-room in Tampines ($1800.0)]
```

### Part (d) (5 marks): getMRTStationsWithLargeRentGap

This method takes in a list of flats and a rent threshold `x`. It
returns a list of distinct MRT station names (sorted in alphabetical
order) that are shared between a pair of flats where the absolute rent
difference exceeds `x`.

Write your method so that it behaves as follows (continuing from the
previous jshell input):

```text
jshell> Q2d.getMRTStationsWithLargeRentGap(flats, 400);
$.. ==> [Tampines MRT]
jshell> Q2d.getMRTStationsWithLargeRentGap(flats, 200);
$.. ==> [Tampines MRT, Woodlands MRT]
jshell> Q2d.getMRTStationsWithLargeRentGap(flats, 500);
$.. ==> []
```


## END OF PAPER
