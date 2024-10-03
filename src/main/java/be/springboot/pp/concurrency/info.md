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
