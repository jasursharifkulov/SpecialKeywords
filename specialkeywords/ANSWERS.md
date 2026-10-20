# Laboratory 5 — Kotlin Special Keywords

## Task 1 — inline

For the input abc, 200, 35, the expected number of attempts is 3.
abc cannot be converted to Int. The number 200 is outside 0..120.
Both inputs produce null, so ask repeats the question.

With inline, the function and lambda bodies are inserted at the call
site. In this example, main does not need an ask call or a Ref.IntRef
wrapper for attempts.

Without inline, main calls ask and passes a lambda object. The captured
mutable variable attempts uses Ref.IntRef so the lambda and main can
share and modify the same value.

Inlining is useful for small higher-order functions because it can
avoid lambda allocation and call overhead. Inlining a large function
at many call sites increases the generated code size.

## Task 2 — noinline

The reminder parameter must be marked noinline because it is stored
in the pending list.

For Write report, the output is:

Saved: Write report
Pending reminders: 1
Reminder: finish 'Write report'

Adding pending.add(save) causes a compilation error because save is
an inline parameter. It may be called directly but cannot be stored
as a function object unless it is also marked noinline.

The reminder code runs through a lambda object kept in pending.
Its captured task name remains available after addTask finishes.
An inlined body alone would not provide an object to store and call later.

## Task 3 — Non-local return

For ali, findFirst prints Ali Karimov and immediately returns.
findAll prints Ali Karimov, Aliyah Smith and Bobur Aliev.

For xyz, findFirst prints:
No contact contains 'xyz'

findAll prints no matches.

return@forEach finishes only the current lambda invocation.
It behaves similarly to continue in a normal loop.

After a match in findFirst, the "No contact contains..." line is skipped.
"Search finished" still appears because main continues after findFirst.

A regular each function does not allow a bare return from its lambda.
Marking each inline allows the return to leave findWithEach.

A bare non-local return is allowed when the lambda is inlined into
the enclosing function and is not restricted by noinline or crossinline.

## Task 4 — crossinline

The action parameter needs crossinline because it is called inside
the Runnable object.

Expected results:
1 → Hello!
2 → Goodbye!
3 → Time to rest.
4 → Settings opened
9 → Unknown command

A bare return inside command { ... } is forbidden because crossinline
prohibits non-local returns. The Runnable can execute after the function
that created it has already returned.

Task 3 allows the return because its lambda is directly inlined without
the crossinline restriction.

crossinline still permits local returns such as return@command.

## Task 5 — break and continue

Input:
10
abc
25.5
done

Expected output includes:
'abc' is not a number, skipped
Items: 2, total: 35.5

Input:
10
abc
20000
5

Expected output includes:
'abc' is not a number, skipped
Price 20000.0 is too large, stopping
Items: 1, total: 10.0

The value 5 is not read because the loop has already stopped.

In the original code, continue is inside the run lambda and break
is inside the let lambda. Both affect the surrounding while loop.
The break after checking "done" is directly inside the loop.

The final version replaces run with orSkip. A regular orSkip function
does not allow this non-local continue; marking it inline allows it.

Non-local break and continue in inline lambdas require Kotlin 2.2
or the appropriate compiler support.

## Task 6 — reified typed input

For 25, 1.75, true and Samarkand, the values are:

age=25
height=1.75
subscribed=true
city=Samarkand

For x, 1,75, yes and an empty city line:
- age is null because x is not an integer.
- height is null because 1,75 is not accepted as a Double.
- subscribed is null because strict Boolean parsing accepts true or false.
- city is an empty String, not null.

reified allows the inline function to access the concrete type T.

Removing reified makes T::class invalid. It occurs in the when selector
and in the unsupported-type error message. Casts such as as T? also
lose the concrete runtime type information and may become unchecked.

readValue<Char>() compiles, but fails at runtime because Char is not
supported by the when branches. The else branch calls error(...).

Long support was added with:
Long::class -> text.toLongOrNull() as T?

The value 998901234567 can be represented as Long but exceeds Int's range.

## Task 7 — Filtering by type

For:
10 apples 2.5 7 pears 0.75

Expected lists:
Ints: [10, 7]
Doubles: [2.5, 0.75]
Words: [apples, pears]

7 becomes Int because toIntOrNull is checked before toDoubleOrNull.

Without reified, item is T is invalid because the generic type is erased:
the concrete type argument is not normally available for this runtime check.

filterIsInstance<Int>() produces the same result as onlyOfType<Int>().
Its declaration uses inline and a reified type parameter.

Boolean parsing was added with toBooleanStrictOrNull().

For true 5 false x:
Ints: [5]
Doubles: []
Words: [x]
Booleans: [true, false]

## Task 8 — Inline properties

For price 100 and sentence "the quick  brown fox", the expected result is:

Price with VAT: 112.00
Words: 4

withVat is marked inline as a whole property.
wordCount has only its getter marked inline.
Both are read-only computed properties, so their getter behaviour is equivalent.

inline val taxRate: Double = 0.12 is invalid because it needs a backing field.
Inline properties can inline accessors, but cannot have backing fields.

Int.kilobytes was added with a getter that multiplies the value by 1024.
For 2, the result is 2048.

## Task 9 — @PublishedApi

A public inline function cannot directly access a private declaration.
Changing private to internal alone does not fix this restriction.

@PublishedApi allows the internal declaration to be used by public
inline code. It makes that declaration part of the effective binary API.

For yes:
(building the report...)
[DEBUG] report with 1000 rows
Program finished

For no:
Program finished

The lambda is called only when debugEnabled is true.

If debug accepts a String and is called as debug(buildReport()),
buildReport() runs before debug is entered, even when logging is disabled.
The lambda version delays building the message until it is needed;
this laziness comes from using a lambda, while inline can reduce its overhead.

## Task 10 — typealias gradebook

For Ali 85, Dilnoza 90, Ali 70, bad, Bobur x, end:
- bad is rejected because it does not contain two parts and has no grade.
- Bobur x is rejected because x cannot be converted to Int.

Expected results:
Ali: 85, 70; average = 77.5; best = 85
Dilnoza: 90; average = 90.0; best = 90

Gradebook stands for:
MutableMap<String, MutableList<Int>>

The IDE may display the alias or its expanded type.

Replacing Gradebook with its expanded type still works.
A typealias provides another name; it does not create a separate type.

Grades is an alias for MutableList<Int>.
Grades.best() uses maxOrNull(), returning null for an empty list.

## Task 11 — Function type aliases

For a@b.uz and secret123, both checks succeed.
For abc and short, both checks fail.

A plain lambda is passed here:
check("Not blank", email) { it.isNotBlank() }

A Validator is assigned to a plain function type here:
val plain: (String) -> Boolean = isEmail

Both compile because Validator is exactly the same type as
(String) -> Boolean.

Meters and Feet are both aliases for Double.
For 4 meters, toFeet returns 13.12336.
Passing that result to describeRoom prints 13.12336 m, which is
semantically wrong but accepted by the compiler.

Distinct wrapper types, such as separate value classes, could prevent
mixing units. They are not implemented in this exercise.

Rule is an alias for Predicate<String>.
startsWithCode checks whether a string begins with +998.
It checks only the prefix, not whether the whole phone number is valid.

## Task 12 — Any and equality

With the original Contact class, println(a) uses the inherited JVM
Object.toString() implementation. It prints a class name and a hexadecimal
hash value, for example task12.Contact@..., whose exact suffix varies.

For two separately created contacts with the same phone:
a == b: false
a === b: false
book contains b: false

By default, this class inherits reference-based equality.

After overriding toString, equals and hashCode, for Ali and Ali K
with phone 991234567:

Ali (991234567)
a == b: true
a === b: false
book contains b: true

== uses equals, while === checks whether both references point to
the same object.

Without the matching hashCode override, HashSet membership is unreliable
and will typically report false for b. Equal objects must have equal
hash codes so hash-based collections can locate them consistently.

equals accepts Any? because it overrides the general equality method,
which must accept objects of any type, including null.

When other is null, other is Contact evaluates to false.

## Task 13 — Type checks and casts

The original describe function handles ratio with its else branch.
The final version adds an is Double branch.

With the original value as String:

- volume: ClassCastException because Int is not String.
- darkMode: ClassCastException because Boolean is not String.
- username: succeeds and gives Dilnoza.
- theme: NullPointerException because null cannot be cast to non-null String.
- ratio: ClassCastException because Double is not String.
- Unknown key: prints "No such setting" and returns before the cast.

Typical JVM messages mention Integer, Boolean or Double being unable
to be cast to String. For theme, the message commonly says:
null cannot be cast to non-null type kotlin.String

Exact exception messages depend on the runtime.

With as? String:
- volume gives null.
- theme gives null.

With as String?:
- volume still throws ClassCastException.
- theme succeeds and gives null.

Any.typeName() can be called on non-null values because all non-null
Kotlin types are subtypes of Any. It cannot be called directly on null.

5.typeName() returns Int.
"x".typeName() returns String.

## Task 14 — Nothing and validation

Expected results:

20 → You will be 21 next year
abc → Input error: Age must be a whole number
-5 → Input error: Age cannot be negative

The exception is caught by the catch block in main.

age has type Int because the right side of ?: has type Nothing.
That branch cannot produce a value and continue normally.

If fail returns Unit instead, the expression combines Int and Unit,
so age is inferred as their common supertype, Any.
The numeric comparison and returning age as Int then fail to compile.

A statement after fail("always") is unreachable because fail never
returns normally.

TODO() and error() also return Nothing.

## Task 15 — Nothing and the calculator

runCalculator can return Nothing because it loops forever unless
exitProcess terminates the process. It never returns normally to main.

println("This line is never reached") is unreachable, and the IDE
can warn about it.

For add 5, sub 2, show, foo, exit:
Total = 3
Unknown command 'foo'
Bye, final total = 3

Coupon results:
student → 20%
friend → 10%
zzz → IllegalStateException for an unknown coupon

An unknown coupon stops the program before the calculator begins.

The when expression in discountFor has type Int.
Its branches return Int, Int and Nothing; Nothing is a subtype of Int.

List<Nothing> can be assigned to List<String> and List<Int> because
Nothing is a subtype of every type and read-only List is covariant.
The shared empty list contains no elements.

The reset command sets total to 0.
The mul N command multiplies total by N.

For add 5, sub 2, show, mul 4, show, reset, show, exit:
Total = 3
Total = 12
Total = 0
Bye, final total = 0