There is a banking system which we want to code with multiple running threads. Each thread is responsible for 1 txn.

Each txn is defined as amount, src account, dest account. A simple txn is deduct an amount from source and add it to destination. No transaction should finish partially. For now, we assume, we have fixed number of accounts. For our consideration, no account can be created and destroyed.

We will have 1 special thread called auditor thread, that will run with a daily frequency of every 1 hour or so, and confirm that total amount across all the accounts before and after txns remains the same. As money can't be created nor destroyed.

Whenever it runs, the auditor thread returns the amount present in an account and total overall amount.