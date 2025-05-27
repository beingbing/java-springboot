There is a banking system which we want to code with multiple running threads. Each thread is responsible for 1 txn.

Each txn is defined as amount, src account, dest account. A simple txn is deduct an amount from source and add it to destination. No transaction should finish partially. For now, we have fixed number of accounts.

We will have 1 special thread called auditor thread, that will run with a frequency, and confirm that total amount before and after txns remains the same. As money can't be created nor destroyed.
