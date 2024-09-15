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


