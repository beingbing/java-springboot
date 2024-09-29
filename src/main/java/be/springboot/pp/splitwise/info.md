# Splitwise

## requirement
- create a splitwise clone
- make an api `/addExpense` which has a token-based authentication and which take userId of the user who is adding it, groupId of the group in which it is added and other expense details like amount, splitting-strategy, list of people and person-who-paid to split the difference. alternatively, if token is validated then user-detail can be fetched from its decrypted details as well. 
- once user is authenticated and user details are fetched, then check if the group in which user is trying to add an expense, user is part of that group or not, if not then user is not authorized to add that expense.
- an api to pay/settle, `/pay`, which takes token, groupId, amount and expenseId 
- here authorization can be that user is part of that expense which we are registering for user to be as paid off.
- there can be different strategies of expense sharing
- a group balance sheet `/groupBalanceSheet`, to know which user owe me how much in that group. 
- an api to get balance-sheet `/balanceSheet` to know which user owe me how much amount.
- also, i should also get whom i owe in the same api

## explanation
- there can be multiple groups, example "FLAT #13", "Men in Pune", etc.
- every group is composed of multiple users, so lets say, group1 has userId1, userId2, userId3, and userId4.
- for every group, there will be an expense history. 
```json
{
  "expenses": {
    "idli_and_dosa" : {
      "amount": 360,
      "payer" : [
        "userId1"
      ],
      "split_in" : [
        "userId1",
        "userId2",
        "usrId4"
      ]
    },
    "movie1" : {
      "...": "..."    
    }
  }
}
```

## db schema
### User
id, name, age, gender, password, ...
### groups
id, name, creation date, ...
### user:group linker table
surrogate id, user id, group id
### expenses
id, group id, creation date, name, total amount, ....
### expense splits
id, expense id, provider id, borrower id, amount, isSettled / date of settlement, ...
### payments
id, split id, amount, payment date, ...
