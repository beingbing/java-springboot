# Concurrency

## definition

## example

## multitasking

## benefits

## drawback scenarios

## programming implementation of concurrency concepts
most frequent used construct to implement concurrency is called `Thread(s)`. When we create a new Thread, it contains a logic, which can be made to run
concurrently. 
Even our non-concurrent programs, which we wrote until now had a Thread called 'main thread'. It contains logic which is contained in main().
We can create a new Thread and kickstart it in our ongoing threads and execution of those newly created threads will be done concurrently.
Inside every Thread declaration we need to pass an object whose type should be `Runnable`.

Runnable interface just has a single method `public abstract void run()`, it just executes the logic written while providing definition.

Thread is the most basic way in which we can write our concurrent program.

When we create a thread, it has its own set of local variables. but threads to have shared variables as well, which raises disputes and give birth to
race conditions.

# Threads

## callstack
Its simple stack of function calls that are waiting for called function to complete and pop to continue execution.

Whatever popping functions returns/throws is caught by calling function if they are in the same callstack.

Every thread has their own callstack. They are private to a thread, they are not shared.

If thread A starts thread B then If thread B throws an exception, then thread A won't be able to catch it.

Similarly, if we initialize a thread to do some computation concurrently then we can not get hold of the result of its computation.
To get hold of it we can let the thread store computation result in a global/shared variable which is accessible from my process thread.
And once the thread terminates, we can read the value stored in that variable.

# pitfalls of concurrency to avoid
- race condition: when shared resource is not thread-safe then in multi-thread environment, a non-deterministic thread execution
pattern may lead to the unpredictable value of shared resource.

## critical section
Section of code which can indulge in race condition. To avoid race condition, guard critical sections and prevent them from indulging
in race condition.
To do that, we need to make critical sections to be atomic, either all of them will happen, or none of them will happen.
For achieving that we have concept of Locking.

## Lock
If a thread before entering inside a critical section acquires the lock then until it leaves the critical section and releases that
lock, no other thread can enter inside it.
In other words, lock ensures that only one thread goes through critical section and until it is completely through with the section,
no other thread can start execution of that section of code.
This term, "acqire the lock", mean get hold of a variable and make it unavailable for other threads, and Java has a construct
which help us in doing exactly that, which is "synchronize".

### what if race condition happens in acquiring lock?
Theoretically, this concern is understandable but practically it won't happen. You can assume that synchronized-blocks are designed
by Java creators such that this scenario will not occur. Because going into detail of this is not of much use to us.

### how to identify the correct placement of lock
When you put a code section under lock, it will become a sequential process rather than being concurrent.
This means we are constraining to run complete process locked inside critical section to be executed in one attempt.
So, the tiniest and most quickest to finish the critical section, the better it is for reaping benifits of concurrency, hence
find a sub-critical-section of a critical section which ensures thread safety and is shortest to complete.
In other words, keep the lock on the lowest level.
