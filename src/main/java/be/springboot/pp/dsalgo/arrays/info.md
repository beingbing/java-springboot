~~ special searching problem ~~

Given a 2-d matrix with dimensions m*n. Given an integer key. Check if key is  present in matrix. If present print coordinates otherwise print (-1, -1). The special thing about matrix is each row and column is sorted in ascending order.

approach 1: Brute-force -
check every cell one by one and look for a match. Time complexity in this case will be O(m*n)

approach 2: binary search -
perform binary search in 2-d matrix. Time complexity in this case will me O(m * logn)
but this would have worked even if only rows were sorted. But we have sorted column as well.
We should take advantage of that as well.

approach 3:
We need to figure out a way to curtail the size of our search space with every comparison. There is a unique property of both cells on (o, n-1) and (n-1, 0).
from (0, n-1) every left movement gives a number smaller than complete column and every down movement gives a number greater than current row.
Similarly, for (n-1, 0) every right movement gives a number greater than complete column and every up movement gives a number smaller than current row.
If we go out of the bounds of matrix this means key is not present in the matrix.

algorithm:
- start searching from point (0, n-1)
- at every point check for 3 things -
  - key == matrix element ( we got our answer)
  - key < matrix element ( our answer is on left side)
  - key > matrix element ( go down to find the answer)
- if we go out of the bounds of matrix, means key is not present in the matrix.

```java
    bool searchMatrix(int[][] matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        int i = 0, j = n-1;

        while (i < m && j >= 0) {
            if (target < matrix[i][j]) j--; // Move left
            else if (target > matrix[i][j]) i++; // Move down
            else return true; // Found the target
        }
        return false; // Target not found
    }
```

Time complexity will be O(m+n)
