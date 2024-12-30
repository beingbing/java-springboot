~~ segmented sieve ~~

Drawbacks associated with sieve of Erathosthenes algorithm -
- space complexity is O(n)

Problem: print all prime numbers from 1 till n, where n = 10^10

as sieve-array of this large size is impractical and sometimes due to memory limitation not even possible to define, then how will we solve the problem ?

so, lets convert above problem into a different problem. OR consider a totally different problem -

restructured/new problem: Given two large numbers l and r (where l ≤ r and l,r are in the order of 10^10), print all prime numbers in the range [l,r].

Constraints:
Range: l≈10^10, r≈l+500
Output: List all prime numbers in the range [l,r]

Since the range size is relatively small (500), we need an efficient algorithm to check for primes within this range, as naive methods would be too slow.

We can easily declare an array upto 1_000_000, so range is much lower than this but range endpoints are not.

We need to find the range of prime numbers which will be responsible for striking off numbers in the range l to r.

The answer is, primes till sqrt(r) will be responsible for striking off composites till r.

So, we just need to perform sieve till sqrt(r) and using it we can find out primes till r. This is called Segmented sieve.

**How to Use the Segmented Sieve Algorithm**:
- We divide the problem into two parts:
1. Use a **simple sieve** to find all primes up to (sqrt{r}).
2. Use these small primes to mark multiples in the range ([l, r]) as non-prime, leaving only the primes in this range.

Now, the question is, if we start striking any number before l, then it won't contribute to our answer, hence is useless, so if a number is prime then we should start by striking its multiple in the range of l to r only.

we only need to know from which multiple of a prime we need to start striking off so that the number fall in the range of l to r

so, for each prime we can simply find k = ceil(l/p[i]), and start string numbers from p[i]*(k) and keep on striking off until we cross r.

TC: O(sqrt(R)*log(log(sqrt(r)))) + O((r-l) * log(log(sqrt(r))))
as r-l is too small as compared to sqrt(r), so effective time complexity will be, O(sqrt(r)*log(log(sqrt(r))))

and the size of computation array will be (r-l+1) and map all elements from l to r in this array from 0 till r-l

so, if a number is prime in this array hence its actual value is index + l.

2. **Steps of the Segmented Sieve**:
    - **Step 1**: Generate all primes up to \(\sqrt{r}\) using a traditional Sieve of Eratosthenes.
    - **Step 2**: Use these primes to mark non-primes in the range \([l, r]\) by marking multiples of each small prime as non-prime within this range.
    - **Step 3**: The remaining unmarked numbers in the range are primes.




### Problem: Find All Primes in a Large Range

Given two large numbers \( l \) and \( r \) (where \( l \leq r \) and \( l, r \) are in the order of \( 10^{10} \)), print all prime numbers in the range \([l, r]\).

### Constraints:

- **Range**: \( l \approx 10^{10} \), \( r \approx l + 500 \)
- **Output**: List all prime numbers in the range \([l, r]\)

Since the range size is relatively small (500), we need an efficient algorithm to check for primes within this range, as naive methods would be too slow.

### Approach:

1. **Use the Segmented Sieve Algorithm**:
    - The segmented sieve algorithm is ideal for finding primes in a specific range \([l, r]\) when \( r \) is large.
    - We divide the problem into two parts:
        1. Use a **simple sieve** to find all primes up to \(\sqrt{r}\).
        2. Use these small primes to mark multiples in the range \([l, r]\) as non-prime, leaving only the primes in this range.

2. **Steps of the Segmented Sieve**:
    - **Step 1**: Generate all primes up to \(\sqrt{r}\) using a traditional Sieve of Eratosthenes.
    - **Step 2**: Use these primes to mark non-primes in the range \([l, r]\) by marking multiples of each small prime as non-prime within this range.
    - **Step 3**: The remaining unmarked numbers in the range are primes.

### Detailed Explanation:

1. **Finding Small Primes up to \(\sqrt{r}\)**:
    - Calculate the square root of \( r \).
    - Generate all primes up to \(\sqrt{r}\) using the Sieve of Eratosthenes. These primes will help eliminate non-primes in the range \([l, r]\).

2. **Marking Non-Primes in the Range \([l, r]\)**:
    - Create a boolean array `isPrime` of size \( r - l + 1 \), initialized to `true`, to represent each number in the range as a potential prime.
    - For each small prime \( p \):
        - Calculate the starting point within \([l, r]\) to begin marking multiples of \( p \). This starting point is the smallest multiple of \( p \) in the range \([l, r]\).
        - Mark multiples of \( p \) in the range \([l, r]\) as non-prime.

3. **Collecting Results**:
    - After marking, the numbers in \([l, r]\) that are still marked as `true` in `isPrime` are primes.

### Code Implementation:

```java
import java.util.ArrayList;
import java.util.Arrays;

public class LargeRangePrimeFinder {

    // Function to find primes up to √r using Sieve of Eratosthenes
    public static ArrayList<Integer> sieve(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        ArrayList<Integer> primes = new ArrayList<>();
        
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not primes
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = i * 2; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return primes;
    }

    // Function to find all primes in the range [l, r] using the Segmented Sieve approach
    public static ArrayList<Long> segmentedSieve(long l, long r) {
        int limit = (int) Math.sqrt(r);
        ArrayList<Integer> primes = sieve(limit);

        // Boolean array for marking non-prime numbers in the range [l, r]
        boolean[] isPrime = new boolean[(int) (r - l + 1)];
        Arrays.fill(isPrime, true);

        // Marking multiples of each prime in the range [l, r]
        for (int prime : primes) {
            long start = Math.max(prime * prime, (l + prime - 1) / prime * prime);
            for (long j = start; j <= r; j += prime) {
                isPrime[(int) (j - l)] = false;
            }
        }

        // Collect all primes from the range [l, r]
        ArrayList<Long> result = new ArrayList<>();
        for (int i = 0; i <= r - l; i++) {
            if (isPrime[i]) {
                result.add(l + i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        long l = 10000000000L;
        long r = 10000000500L;

        ArrayList<Long> primesInRange = segmentedSieve(l, r);
        System.out.println("Primes in the range [" + l + ", " + r + "]: " + primesInRange);
    }
}
```

### Explanation of Code:

1. **`sieve` function**:
    - Finds all primes up to \(\sqrt{r}\) using the Sieve of Eratosthenes and returns them as a list.

2. **`segmentedSieve` function**:
    - Initializes an array `isPrime` for the range \([l, r]\) to keep track of potential primes.
    - Uses each small prime from the `sieve` function to mark non-primes in the range by marking multiples.
    - The remaining `true` values in `isPrime` correspond to primes in the range \([l, r]\).

3. **Main Function**:
    - Calls `segmentedSieve` with given \( l \) and \( r \) and prints the result.

### Complexity Analysis:

- **Time Complexity**:
    - **Preprocessing with Sieve of Eratosthenes**: ( O(sqrt{r} log \log \sqrt{r}) \)
    - **Segmented Sieve Marking**: \( O(\sqrt{r} \times \frac{r - l}{\sqrt{r}}) = O(r - l) \), efficient due to the limited range.

  Combined complexity is approximately \( O(\sqrt{r} \log \log \sqrt{r} + r - l) \).

- **Space Complexity**:
    - **Sieve Storage**: \( O(\sqrt{r}) \) for storing small primes.
    - **Segmented Range**: \( O(r - l) \) for marking primes in the range.

### Example:

For \( l = 10^{10} \) and \( r = 10^{10} + 500 \), the program will:
- Generate primes up to \(\sqrt{10^{10} + 500}\), which is around 100,000.
- Use these primes to mark multiples within the range \([10^{10}, 10^{10} + 500]\) efficiently.

---