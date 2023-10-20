# hw2- Design & Implementation

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 


## Group: Git Lucky
- Ishita Chaudhary
- Mihir Thalanki

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## Functionalities Added:
1. Encapsulation & Immutability of transactions:
- converted the transaction class to private final
- converted the transaction list to unmodifiable
- made all the fields private final and remove the setters

2. Filtering functionality:
- Added a functionality that allows a user to filter out transactions based on category or amount. 
- Once, the user has added the desired transactions, they can toggle between the amount and category filter. They can then type the value to be filtered. Transactions filtered out are highlighted in the app.