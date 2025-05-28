# Concurrency
## Explanation
Imagine you're working from home and have some office tasks pending, but you also feel hungry. You decide to cook food, so you place the ingredients in a pressure cooker and set it on the stove. While the cooker is doing its job, you return to your office work. The cooker operates independently, and you're able to focus on your tasks.

Suddenly, the cooker whistles, reminding you that the cooking needs attention. You temporarily pause your office work, tend to the cooker, stabilize everything (e.g., turn off the stove), also placing some food in the oven, and then return to your office tasks. You continue working while the food remains in the oven, perhaps with a timer set to remind you when it's done.

This entire process demonstrates how you, as a single person, are able to perform two tasks concurrently — working and cooking. Both tasks are independent and don't rely on each other, so they can be handled simultaneously without waiting for one to complete before starting the other.

This concept reflects how we design systems to execute tasks concurrently, ensuring no idle time. In computing, when tasks are independent (i.e., one task does not depend on the completion of the other), we can schedule them to run concurrently, allowing better utilization of time and resources. However, if tasks were dependent on one another, they would need to be executed sequentially (one after the other), as the second task could not begin until the first was finished.

In computing systems, this type of multitasking is managed by the underlying architecture of the machine, such as operating systems and programming techniques that allow concurrent execution. By structuring and organizing tasks correctly, machines can handle multiple independent tasks without unnecessary delays, improving overall efficiency.

## Concurrency VS Parallelism
In earlier days, machines could only perform instructions serially, meaning tasks were executed one after another without skipping any. The idea of "jumping" to another task while the current task was still unfinished wasn’t possible. Modern machines, however, allow for skipping over tasks that are dependent on a task which got context switched without getting finished, enabling the processor to switch to an independent task in the meantime. Let’s look at two scenarios to understand the difference between concurrency and parallelism.

### Parallelism
Imagine you're using a machine with two cores. One core is handling your web browsing task, while the other core is performing some calculations. In this scenario, both tasks are being executed simultaneously because each core is dedicated to a specific task. This is called parallelism. Both tasks run in parallel on separate cores.

### Concurrency
Now imagine you're using a single-core processor. The processor sends a request to load a webpage over the internet and, while waiting for the response, it switches to perform some calculations. When the browser receives the response, the operating system switches the processor’s attention back to loading the webpage. This is concurrency, where the single processor time-slices between tasks, performing parts of each one at different times, giving the illusion that tasks are running simultaneously.

## Time Slicing
In concurrency, when multiple tasks are ready to be executed, the processor assigns a specific amount of time to each task (e.g., 2 seconds to task A, then switches to task B for 3 seconds, and so on). This cycle repeats, and although none of the tasks might finish during their allotted time, they all make progress concurrently. This time-slicing approach allows a single processor to handle multiple tasks, appearing as though they're being processed simultaneously.

## Definitions
- **Concurrency:** Managing multiple tasks by working on them one at a time but switching between them in a way that they all make progress concurrently. This typically happens on a single core.
- **Parallelism:** Running multiple tasks literally at the same time on different cores or processors, allowing for true simultaneous execution.
- **Multitasking:** The act of switching between different types of tasks, which can be achieved through either concurrency or parallelism.

## Benefits of Concurrency
- **Increased Responsiveness:** Multiple tasks progress in a time-sliced manner, making the system more responsive, as no task is left idle for too long.
- **Reduced Runtime:** Tasks can be completed more quickly if they’re managed concurrently, reducing overall system latency.
- **Fault Tolerance and Resilience:** By breaking down processes into independent threads, failures in one thread won’t affect others, making the system more resilient.
- **Concurrency and Parallelism Together:** If a machine has multiple cores, tasks can be run concurrently (via time slicing) and in parallel (on different cores) for even greater performance.

## Drawbacks and Considerations
- **Concurrency ≠ Better Performance:** Simply making code concurrent doesn’t always result in better performance. Not all code can be made concurrent, especially if parts are dependent on the completion of other parts.
- **Independent Task Breakdown:** For concurrency to be effective, tasks must be independent. If a process can’t be broken into smaller independent tasks, concurrency will not provide any benefit.
- **CPU-Intensive Tasks:** In a single-core environment, if a task is purely CPU-bound (i.e., it only requires processor time without waiting for external input), breaking it into smaller concurrent tasks may actually slow it down due to the overhead of context switching. In such cases, processing the task sequentially might be faster.
- **Shared Resources:** Not all tasks are suitable for concurrent execution, especially if they rely on shared resources that cannot be safely accessed by multiple threads simultaneously.

### Conclusion
- Concurrency is beneficial when tasks by nature can be broken down into independent units and when tasks involve external dependencies (like waiting for I/O operations).
- For CPU-bound tasks, concurrency might not provide any advantage and can even degrade performance, especially on single-core processors.
- Even with multiple cores, we need to figure out optimal number of threads that can run program in minimal time.

## Danger Zones in Concurrent Programming
When writing concurrent programs, you must watch out for these common issues:
- **Race Conditions:** Occurs when multiple threads try to update the same shared resource simultaneously without proper synchronization, leading to unpredictable results.
- **Memory Visibility Problems:** Changes made by one thread might not be immediately visible to other threads, leading to inconsistent data access.
- **Deadlocks:** Avoid situations where two or more threads are waiting indefinitely for each other to release locks.

### Race Condition Prevention
- when there is no shared resource among running threads.
- when shared data is read only by nature (Immutable)
- when shared data is mutable but critical section is synchronized (client should not worry about synchronization)

# Threads
## Programming Implementation of Concurrency Concepts
One of the most commonly used constructs for implementing concurrency in Java is called Threads. A thread allows a portion of a program's logic to run concurrently with other parts of the program.

Even in non-concurrent Java programs, there is always a main thread called the "main thread", which executes the code inside the main() method. To introduce concurrency, we can create new threads, kickstart them, and have them run concurrently alongside the main thread or other existing threads.

### Threads and the Runnable Interface
When we create a new thread, it requires an object that implements the Runnable interface. The Runnable interface has only one method - `public abstract void run();`

The run() method contains the logic that the thread will execute when it is started. Once a thread is created and started, the logic inside its run() method is executed concurrently with the program's other threads.

### Local and Shared Variables
Each thread has its own set of local variables, but there may also be shared variables that multiple threads access. Shared variables can lead to race conditions if not properly managed, since multiple threads might try to modify them simultaneously, leading to unpredictable results.

## callstack
Each thread has its own call stack, which is a structure that tracks function calls and their return values. The call stack is private to each thread, meaning one thread’s stack cannot be accessed or modified by another thread. For example, if thread A starts thread B, and thread B throws an exception, thread A will not be able to catch that exception because each thread operates within its own call stack.

Similarly, if thread B performs a computation, thread A cannot directly access the result of that computation unless the result is stored in a shared variable that both threads can access. After thread B completes its execution, thread A can retrieve the result from this shared variable.

## critical section
A critical section is a part of the code where shared resources are accessed and where race conditions are likely to occur. To prevent race conditions, we must ensure that critical sections are thread-safe, meaning that only one thread can execute the critical section at a time.

# Lock
## Locking and Synchronization
One way to ensure thread safety in critical sections is by using a lock object. A thread must acquire a lock before entering the critical section and release it after leaving. While one thread holds the lock object, no other thread can enter the critical section. In Java, the `synchronized` keyword is used to implement this locking mechanism on any object. It ensures that only one thread can access a block of code or an object at a time.

## Identifying the Critical Section
When a code section is kept under lock, it becomes a sequential process and can never be concurrent. Hence, when designing concurrent programs, it’s essential to decide on a critical section, which is as small and as easy to go through as possible. This way, the program can maximize the benefits of concurrency by minimizing the time threads spend waiting for a lock. Locking too large a section of code can lead to reduced concurrency on the locked section and degraded performance due to other threads waiting longer for acquiring lock.

## Synchronized Blocks and Atomicity
When a section of code is marked as synchronized, Java ensures that the operations inside that block are atomic. This means the entire block is executed as an uninterruptible unit, preventing race conditions. Java's synchronized mechanism guarantees that no race conditions will occur when acquiring a lock for the critical section.

# Executor Framework
Instead of managing threads directly, Java provides the Executor framework to simplify concurrent programming. The Executor interface provides a method execute() that accepts a Runnable task and executes it.

## ExecutorService
The ExecutorService interface extends Executor and provides additional lifecycle management features, some of them are:
- `shutdown()`: Gracefully shuts down the executor, allowing previously submitted tasks to complete.
- `shutdownNow()`: Attempts to stop all actively executing tasks and halts the processing of waiting tasks.
- `isTerminated(`): Checks if all tasks have completed after a shutdown.

The ExecutorService also allows for more advanced execution models, such as:
- **Sequential Execution:** Tasks are executed one after another.
- **Thread Pool:** A pool of threads is maintained, and tasks are assigned to the next available thread.
- **New Thread:** A new thread is created for each task.
The ExecutorService replaces the need for manually creating and managing thread pools, providing a more robust and manageable approach to concurrency.

# Custom locks
We used synchronized keyword until now to performing locking, but there is a custom way as well. 

## Lock interface
Any classes implementing it got to implement its methods then we can use objects of that class as locks.
- `void lock()`: simply allows us to lock an object. 
- `boolean tryLock()`: It acquires a lock only if it is free at the time of invocation. Acquires the lock if it is available and returns immediately with a value true. If the lock is not available then this method will return false immediately.
It is very different from `synchronized`. In that, we had to wait for a lock to get freed, until then we stayed stuck. But it is giving us a new option to do something else while waiting for the lock. It can help us in dealing with deadlocks as well.

## Reentrant Lock
An implementation of Lock interface allowing us to acquire lock on the same object multiple times. With a condition, that we need to release lock as many times as it was acquired.`synchronized` keyword is one such implementation.

> Note: Custom locks needs to be release manually without miss if an exception occurs, otherwise other threads will get left out. In case of language provided locks, this is done automatically.

## LiveLock
Objects are not stuck or halted, but they keep oscillating between states unable to make progress.

## Hand-over-hand locking
in built-in locks, like `synchronized` keyword, we always needed to release locks in reverse order of the order in which they were acquired. But with custom locks, we are not bound with that condition. We can release multiple acquired locks in any order depending on our needs. This approach is caled hand-over-hand locking.

## Latch
In real-life latch is something used to close a door. If you have a thread A whose work is dependent upon some other thread B. If you want to make A wait until B has done some job, then you use a latch.

Analogy: You are waiting outside the room, it is latched and others are decorating the room. So you wait outside until they finish. Once they are done, they will open the latch and you can come inside. 

In java, we have CountDownLatch. You initialize with a counter, and a thread is made to wait on that latch by calling `.await()`, then it will wait until counter becomes 0. Other threads responsibility is to make that counter 0. All the threads on which current thread has a dependency, will hold an instance of the latch, until they are running. Once done with their job, other threads will decrement the counter. Once the counter goes back to 0, waiting thread is woken up and made to proceed.

## Semaphore
It's a controlling synchronizer. It allows you to control the number of permissions/permits. It has a wide variety of use-cases.

Example, you want to establish your backend connection with database for that you want to create a fixed thread connection pool. Because your DB connection has limit and can entertain only a limited amount of connection at any given time. Which mean if there are more connection then that, it will collapse. But our BE is capable of handling 10x the load. Then although it doesn't matter how many requests BE accepted, only a fixed k amount of connections can be made between BE and DB.
The connection pool maintains a fixed amount of objects/threads, and the incoming request need to pick 1 out of that to talk to DB, After processing, the object will be put back. So that some other thread can use it.
There can be race condition in trying to pick an object from the connection pool for establishing connection. This is what Semaphore allows us to take care of.
Semaphore allows us to create a connection-pool with fixed amount of permit objects which can be used to establish a connection, and then the object can be released for next request in line.

This is true anywhere, API rate-limiting is also implemented using Semaphore. So, If you are asked to build a synchroniser with let's say 10 connection, then you will use a Semaphore.

By releasing the permit object means, increment the count back, and by acquiring we mean, decrementing the permit count.

The difference between Semaphore and Mutex is that Semaphore allows a fixed amount of threads work concurrently whereas Mutex is to ensure mutual exclusion between two threads. Thus Semaphore works at a much larger scale.

## What are synchronizers?
They are simply some types which takes care of synchronization needs, so that the client do not have to worry about it.

## BlockingQueue
Our first synchronizer DS. If we use BlockingQueue in our producer/consumer problem, then we don't need to worry our concurrency in our producer/consumer implementation. It will be taken care of by quue itself.

So, if working with Queue and multiple threads, go with blocking-queue. 

`put()` and `take()` provides us concurrency facilities. 

Similarly, we have ConcurrentHashMap.

## Barrier
If we launch 10 concurrent threads to solve a complicated problem. Once all of them finished their jobs, then we need to do some inspection. Trying to merge the result, or checking if it fits intended problem or not. And if it's not the case, then launch those threads again. They will attempt again, and again evaluation will be done. And if eventually inspection succeeds, then we will terminate, otherwise we will launch those threads again.

> Useful Synchronisers: Lecture 5: Barrier got left out. Thus skipping Lecture 6 and 7 as well, which were Barrier implementation 1 and 2.

## Futures
### Callable
Till now we have seen Runnable, which exposes `void run()`. We didn't had any way to make threads communicate as well by returning a value from exiting thread using Runnable.Because different threads have their own execution stack.

If we had a `get()` which blocks until an asynchronously executing thread is completed to get us a value, then our work will be very easy. This is what `Callable` is capable of doing.

Thread consumes FutureTask (an implementation of Runnable) which can consume Callable and execute it
FutureTask has a `get()` method which returns the result of `run()` of `FutureTask`.
