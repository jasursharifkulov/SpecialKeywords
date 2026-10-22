# Laboratory 6 — Special Classes

The source files contain the final working versions, including the requested additions. Task 04 preserves the original payment example; Task 05 contains its Crypto extension. Runtime checks are recorded in VERIFICATION.md. The compiler-error explanations below describe the deliberate experiments; broken code is not left in the module.

## Task 1 — Order status

- uppercase() matches the case-sensitive enum names accepted by valueOf. paid becomes PAID; Delivered becomes DELIVERED.
- In the original four-constant enum, PAID has ordinal 1 (step 2 of 4), with SHIPPED next. DELIVERED is the last step.
- LOST is invalid. Without the catch, valueOf throws IllegalArgumentException. With the catch, the program prints the valid names and returns.
- The alternative is `OrderStatus.entries.firstOrNull { it.name.equals(text.trim(), ignoreCase = true) }`. It returns null for invalid input and is convenient for normal user mistakes. This version is provided as parseStatus in SafeParsing.kt.
- After appending CANCELLED, DELIVERED reports CANCELLED as the next step without changing the next-step calculation. Only the enum declaration changes. This illustrates declaration order, not a real-world order-transition policy.
- `OrderStatus.PAID === OrderStatus.valueOf("PAID")` is true: each enum constant is a singleton.

## Task 2 — Operations

- 12 * 4 gives 48.0; 7 / 2 gives 3.5.
- The explicit DIVIDE-and-zero check rejects 5 / 0. The parts.size check rejects abc.
- The symbol is stored in the enum's symbol property, initialized by each constant's constructor argument. Addition is implemented by ADD.apply.
- abstract fun apply requires each concrete constant to provide its implementation. MODULO without a body fails this requirement.
- MODULO uses a % b: 7 % 3 gives 1.0. POWER uses kotlin.math.pow: 2 ^ 10 gives 1024.0.
- fromSymbol belongs to the companion object, so it is accessed through Operation without selecting an enum instance.

## Task 3 — Delivery

- courier and 2.5 cost 20000 UZS; express and 1 cost 45000; pickup and 10 cost 0. plane is rejected before asking for weight.
- Adding DRONE makes shippingCost's when incomplete. main searches entries generically and does not enumerate delivery cases, so it needs no new branch.
- DRONE costs 25000 + 3000 * weightKg; for 1 kg it costs 28000.
- An else -> 0.0 hides the missing case and incorrectly gives free delivery. An explicit branch lets the compiler catch forgotten cases.

## Task 4 — Payment

- A payment of 120.5 with card 8600123412341234 and expiry 09/28 reports the last four digits 1234 and the expiry date. Cash requests payment at the desk; option 7 is rejected.
- The card data class prints its properties. The data object prints Cash. Replacing data object with ordinary object gives the inherited class-name/hash-style representation, whose suffix varies.
- Payment cannot be instantiated directly: a sealed class is abstract, and its constructor is not a public constructor for callers.
- CreditCard and PayPal are classes because each payment can have different details. Cash is an object because it needs no per-instance data.

## Task 5 — New subclass

- Adding Crypto makes the when in process incomplete. The string-based menu still compiles, but must be extended so the user can select Crypto.
- Option 4 reads a wallet. W123 produces: Waiting for a transfer to wallet W123.
- A fallback else hides the omitted Crypto handler and prints Unknown payment for a valid type.
- A sealed hierarchy gives the compiler a closed set of cases. A String has an unbounded set of possible values, so it cannot infer all intended commands.

## Task 6 — Weather state

- Tashkent produces Loading... followed by Tashkent: 24 C. Empty input reports an empty-city error; Paris reports missing data; exit stops the loop.
- Loading has no data and is a data object. Success carries city and temperature; Error carries a message.
- Adding Offline makes render's when incomplete. loadWeather returns individual states and does not exhaustively match them, so the new type does not invalidate it.
- The final version returns Offline for offline and renders No internet connection.

## Task 7 — Requests and responses

- Successful login for ali/1234 prints OK: Ali (u-ali). The first logout also returns that user's success response; a second logout prints Nobody is logged in.
- ali/wrong produces an invalid-credentials error.
- is LoginRequest checks a class whose instances carry different credentials. LogoutRequest matches the singleton object directly.
- Changing ApiRequest to a sealed class requires constructor calls in the subclasses: `: ApiRequest()` instead of `: ApiRequest`.
- RefreshRequest makes handleRequest incomplete. The final refresh branch returns Error("Refresh is not supported yet"); the console reads a token for the request.

## Task 8 — Validation and constructors

- Ali, 25, ali@mail.uz is accepted. Empty name, abc, xyz produce three errors: name, age and email.
- ValidationError cannot be instantiated directly because it is sealed and abstract.
- For its secondary constructor, no modifier (protected by default), protected and private are allowed. public and internal are forbidden. private restricts calls to the class's own scope; protected also allows subclasses to call it.
- TooYoung is added for ages 0 through 17. Age 15 produces You must be 18 or older. Invalid or out-of-range input still produces InvalidAge.

## Task 9 — Incidents

- For file report.txt, slow /home, db users, crash, hello, end: hello is rejected; the output order is DatabaseDown, Crash, FileProblem, SlowPage. Sorting is descending by enum declaration order; equal severities retain their input order.
- Two database reports are separate objects, so their reference comparison is false. Both hold the same Severity.CRITICAL singleton, so comparing their severity references gives true.
- Adding BLOCKER and OutOfMemory makes describe incomplete. Sorting still works using enum order, and the original exact-CRITICAL counter still counts CRITICAL without changes; it does not include BLOCKER.
- The final memory command creates OutOfMemory. ALERT is printed for severity >= CRITICAL, including BLOCKER.

## Task 10 — Player commands

- play starts at 0s; seek 90 moves to 90s; pause reports 90s. volume 150 is rejected; volume 80 is accepted. stop resets position; fly is rejected; quit exits.
- An enum already has Enum as its base class and cannot extend another class. It can implement PlayerCommand when PlayerCommand is an interface.
- Removing Basic.STOP makes handle's when non-exhaustive.
- MUTE, its parser entry and the handler were added. mute prints Muted as required by the exercise.

## Task 11 — Multiple interfaces

- Originally Phone and Headphones implement both Chargeable and Wireless; SmartLamp implements Wireless. Toaster is allowed because the list element type is Any. car is unknown.
- A class cannot inherit from both Gadget and Appliance: Kotlin permits only one class superclass. Multiple interfaces are allowed.
- chargingNote originally does not need SmartLamp because it accepts Chargeable and SmartLamp originally is not Chargeable.
- After SmartLamp implements Chargeable with 4 battery hours, chargingNote needs its branch. The final version includes it; phone, lamp and headphones give three chargeable devices.
- Waterproof was added as a sealed interface, implemented by Headphones. Its abilities include waterproof.

## Task 12 — Inheritance location

- DiskFull can directly extend IoError from another file in the same package and module.
- missing a.txt and disk 12 are reported as I/O; timeout as timeout; quota 5 and other oops as custom.
- A direct subclass of IoError in task12.other is forbidden because its package differs.
- Overheated can extend the open CustomError from task12.other. It is an indirect implementation of AppError and is covered by report's is CustomError branch.
- Local and anonymous direct subclasses of a sealed type are forbidden; direct subclasses need qualified names.
- AppError: direct implementations must be in the same package and module.
- IoError: direct subclasses must be in the same package and module.
- CustomError: subclasses may be declared wherever the class and its constructor are accessible.

## Task 13 — Guards

- With base fare 2000: Dilnoza aged 2 pays 0; Vali aged 9 pays 1000; Karim aged 70 pays 1400; Ali aged 30 pays 2000.
- Placing the unconditional Child branch first causes it to match all children, so age 2 would pay 1000. The first matching branch wins.
- Removing Adult makes the when non-exhaustive.
- Senior now carries age. The guarded branch for age > 80 precedes the ordinary Senior branch: 81 pays 0, while 80 still pays 1400.

## Task 14 — Generic outcome

- Quantity 3 and price 12.5 produce Total: 37.5.
- x produces a whole-number quantity error; price -1 produces a positive-price error; quantity 500 exceeds the limit of 100.
- Fail implements Outcome<Nothing>. Because Outcome is covariant (out T) and Nothing is a subtype of every type, Fail fits Outcome<Int>, Outcome<Double> and Outcome<String>.
- Removing out makes Outcome invariant, causing the Fail return expressions to have incompatible types.
- parseEmail returns Ok(text) if text contains @ and Fail otherwise. main reads and displays this result too.
- To avoid deeply nested whens, check each result and return immediately on failure. After those checks, use the successful values to calculate the total.

## Task 15 — Support desk

| Type | Choice | Reason |
| --- | --- | --- |
| Priority | enum | A fixed ordered set of singleton labels with no per-ticket data. |
| Action | sealed interface | A closed set of command types with different data, such as a title, ID or comment text. |
| Ticket | normal class | Many independent mutable tickets with their own IDs, titles and comments. |

- The sample opens tickets 1, 2 and 3, comments on 1, closes 3 and reports that ticket 9 does not exist. list orders them by priority: 1 (URGENT), 3 (NORMAL, closed), 2 (LOW), with two open tickets. hello is rejected.
- HIGH is inserted between NORMAL and URGENT. The parser, priority display and sorting automatically use the enum entries and their order; no additional priority-specific branch is needed.
- Reopen makes execute incomplete until its handler is added. The final handler opens the matching ticket again or reports a missing ID. parse accepts reopen ID.
- A sealed Priority with objects would lose the automatically supplied entries, valueOf and enum ordering, requiring manual replacements.
- An Action enum would not naturally represent independent command instances with different IDs, titles and comment text: every constant would be one shared instance.
