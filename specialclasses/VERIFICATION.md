# Runtime verification

Each case was executed against the compiled Kotlin classes.

## Task 01 — PASS
```text
Input:
paid
Output:
Statuses: NEW, PAID, SHIPPED, DELIVERED, CANCELLED
Current status of your order: true
You entered PAID: step 2 of 5
Next step: SHIPPED
```

## Task 01 — PASS
```text
Input:
Delivered
Output:
Statuses: NEW, PAID, SHIPPED, DELIVERED, CANCELLED
Current status of your order: true
You entered DELIVERED: step 4 of 5
Next step: CANCELLED
```

## Task 01 — PASS
```text
Input:
lost
Output:
Statuses: NEW, PAID, SHIPPED, DELIVERED, CANCELLED
Current status of your order: true
'LOST' is not a status. Valid: [NEW, PAID, SHIPPED, DELIVERED, CANCELLED]
```

## Task 01 — PASS
```text
Input:
cancelled
Output:
Statuses: NEW, PAID, SHIPPED, DELIVERED, CANCELLED
Current status of your order: true
You entered CANCELLED: step 5 of 5
This is the last step.
```

## Task 02 — PASS
```text
Input:
12 * 4
Output:
Expression (for example 12 * 4): 12.0 * 4.0 = 48.0
```

## Task 02 — PASS
```text
Input:
7 / 2
Output:
Expression (for example 12 * 4): 7.0 / 2.0 = 3.5
```

## Task 02 — PASS
```text
Input:
5 / 0
Output:
Expression (for example 12 * 4): Division by zero
```

## Task 02 — PASS
```text
Input:
abc
Output:
Expression (for example 12 * 4): Use: number operator number
```

## Task 02 — PASS
```text
Input:
7 % 3
Output:
Expression (for example 12 * 4): 7.0 % 3.0 = 1.0
```

## Task 02 — PASS
```text
Input:
2 ^ 10
Output:
Expression (for example 12 * 4): 2.0 ^ 10.0 = 1024.0
```

## Task 03 — PASS
```text
Input:
courier
2.5
Output:
Delivery types: PICKUP, COURIER, EXPRESS, DRONE
Delivery type: Weight in kg: Shipping cost: 20000 UZS
```

## Task 03 — PASS
```text
Input:
express
1
Output:
Delivery types: PICKUP, COURIER, EXPRESS, DRONE
Delivery type: Weight in kg: Shipping cost: 45000 UZS
```

## Task 03 — PASS
```text
Input:
pickup
10
Output:
Delivery types: PICKUP, COURIER, EXPRESS, DRONE
Delivery type: Weight in kg: Shipping cost: 0 UZS
```

## Task 03 — PASS
```text
Input:
plane
Output:
Delivery types: PICKUP, COURIER, EXPRESS, DRONE
Delivery type: Unknown delivery type
```

## Task 03 — PASS
```text
Input:
drone
1
Output:
Delivery types: PICKUP, COURIER, EXPRESS, DRONE
Delivery type: Weight in kg: Shipping cost: 28000 UZS
```

## Task 04 — PASS
```text
Input:
120.5
1
8600123412341234
09/28
Output:
Amount to pay: 1) Credit card   2) PayPal   3) Cash
Choose a payment method: Card number: Expiry (MM/YY): You chose: CreditCard(number=8600123412341234, expiry=09/28)
Charged 120.5 to the card ending 1234 (expires 09/28)
```

## Task 04 — PASS
```text
Input:
120.5
3
Output:
Amount to pay: 1) Credit card   2) PayPal   3) Cash
Choose a payment method: You chose: Cash
Please pay 120.5 in cash at the desk
```

## Task 04 — PASS
```text
Input:
120.5
7
Output:
Amount to pay: 1) Credit card   2) PayPal   3) Cash
Choose a payment method: Unknown payment method
```

## Task 05 — PASS
```text
Input:
120
4
W123
Output:
Amount to pay: 1) Credit card   2) PayPal   3) Cash   4) Crypto
Choose a payment method: Wallet address: You chose: Crypto(wallet=W123)
Waiting for a transfer to wallet W123
```

## Task 06 — PASS
```text
Input:
Tashkent

Paris
offline
exit
Output:
City (or 'exit'): Loading...
Tashkent: 24 C
City (or 'exit'): Loading...
Error: The city name is empty
City (or 'exit'): Loading...
Error: No data for 'Paris'
City (or 'exit'): Loading...
No internet connection
City (or 'exit'):
```

## Task 07 — PASS
```text
Input:
login
ali
1234
logout
logout
login
ali
wrong
refresh
token
exit
Output:
Commands: login, logout, refresh, exit
> Username: Password: OK: Ali (u-ali)
> OK: Ali (u-ali)
> Nobody is logged in
> Username: Password: FAILED: Invalid username or password
> Token: FAILED: Refresh is not supported yet
>
```

## Task 08 — PASS
```text
Input:
Ali
25
ali@mail.uz
Output:
Name: Age: E-mail: Registration accepted
```

## Task 08 — PASS
```text
Input:

abc
xyz
Output:
Name: Age: E-mail: name: The name must not be empty
age: 'abc' is not an age between 0 and 120
email: 'xyz' is not an e-mail address
```

## Task 08 — PASS
```text
Input:
Ali
15
a@b
Output:
Name: Age: E-mail: age: You must be 18 or older
```

## Task 09 — PASS
```text
Input:
file report.txt
slow /home
db users
crash
hello
memory
end
Output:
Report: file NAME, db NAME, slow URL, crash, memory, or end
> > > > > Unknown report
> > ALERT
[BLOCKER] Out of memory
ALERT
[CRITICAL] Database users is down
ALERT
[CRITICAL] The application crashed
[MAJOR] File report.txt cannot be read
[MINOR] Page /home is slow
Critical incidents: 2 of 5
```

## Task 09 — PASS
```text
Input:
db users
db orders
end
Output:
Report: file NAME, db NAME, slow URL, crash, memory, or end
> > > ALERT
[CRITICAL] Database users is down
ALERT
[CRITICAL] Database orders is down
Same incident: false
Same severity: true
Critical incidents: 2 of 2
```

## Task 10 — PASS
```text
Input:
play
seek 90
pause
volume 150
volume 80
stop
fly
mute
quit
Output:
Commands: play, pause, stop, mute, seek N, volume N (0-100), quit
> Playing from 0s
> Jumped to 90s
> Paused at 90s
> Unknown command
> Volume is now 80%
> Stopped
> Unknown command
> Muted
>
```

## Task 11 — PASS
```text
Input:
phone
lamp
toaster
headphones
car
done
Output:
Add devices: phone, headphones, lamp, toaster (type 'done' to finish)
> > > > > Unknown device
> Phone: battery 24 h, wireless 50 m
SmartLamp: battery 4 h, wireless 30 m
Toaster: no special abilities
Headphones: waterproof, battery 8 h, wireless 10 m
Chargeable devices: 3
```

## Task 12 — PASS
```text
Input:
missing a.txt
disk 12
timeout
quota 5
other oops
end
Output:
Describe an error: missing PATH, denied PATH, disk MB, timeout, quota N, other TEXT, or end
> [I/O] File not found: a.txt
> [I/O] Disk full, only 12 MB free
> [timeout] The operation timed out
> [custom] Quota of 5 exceeded
> [custom] oops
>
```

## Task 13 — PASS
```text
Input:
Dilnoza
2
Output:
Name: Age: Child(name=Dilnoza, age=2) pays 0 UZS
```

## Task 13 — PASS
```text
Input:
Vali
9
Output:
Name: Age: Child(name=Vali, age=9) pays 1000 UZS
```

## Task 13 — PASS
```text
Input:
Karim
70
Output:
Name: Age: Senior(name=Karim, age=70) pays 1400 UZS
```

## Task 13 — PASS
```text
Input:
Ali
30
Output:
Name: Age: Adult(name=Ali) pays 2000 UZS
```

## Task 13 — PASS
```text
Input:
Elder
81
Output:
Name: Age: Senior(name=Elder, age=81) pays 0 UZS
```

## Task 13 — PASS
```text
Input:
Elder
80
Output:
Name: Age: Senior(name=Elder, age=80) pays 1400 UZS
```

## Task 14 — PASS
```text
Input:
3
12.5
a@b
Output:
Quantity: Unit price: E-mail: E-mail: a@b
Total: 37.5
```

## Task 14 — PASS
```text
Input:
x
12.5
a@b
Output:
Quantity: Unit price: E-mail: E-mail: a@b
Quantity error: 'x' is not a whole number
```

## Task 14 — PASS
```text
Input:
3
-1
a@b
Output:
Quantity: Unit price: E-mail: E-mail: a@b
Price error: The price must be positive
```

## Task 14 — PASS
```text
Input:
500
1
a@b
Output:
Quantity: Unit price: E-mail: E-mail: a@b
Quantity error: We cannot sell more than 100 at once
```

## Task 14 — PASS
```text
Input:
3
1
wrong
Output:
Quantity: Unit price: E-mail: E-mail error: E-mail must contain @
Total: 3.0
```

## Task 15 — PASS
```text
Input:
open urgent Printer is broken
open low Change the wallpaper
open normal Add new user
comment 1 Checked the cable
close 3
close 9
list
hello
reopen 3
open high High issue
list
exit
Output:
Commands: open PRIORITY TITLE, close ID, reopen ID, comment ID TEXT, list, exit
Priorities: LOW, NORMAL, HIGH, URGENT
> Opened #1: Printer is broken [URGENT]
> Opened #2: Change the wallpaper [LOW]
> Opened #3: Add new user [NORMAL]
> Comment added to #1 (1 in total)
> Closed #3
> There is no ticket #9
> #1 open   [URGENT] Printer is broken
#3 closed [NORMAL] Add new user
#2 open   [LOW] Change the wallpaper
Open tickets: 2
> I do not understand that
> Reopened #3
> Opened #4: High issue [HIGH]
> #1 open   [URGENT] Printer is broken
#4 open   [HIGH] High issue
#3 open   [NORMAL] Add new user
#2 open   [LOW] Change the wallpaper
Open tickets: 4
>
```
