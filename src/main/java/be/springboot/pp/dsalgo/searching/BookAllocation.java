package be.springboot.pp.dsalgo.searching;

public class BookAllocation {
    private static boolean isFeasible(int[] books, int students, int maxPages) {
        int studentCount = 1;
        int currentPages = 0;

        for (int pages : books) {
            if (currentPages + pages > maxPages) {
                studentCount++;  // Assign new student
                currentPages = pages;  // Reset page count to current book
                if (studentCount > students) return false;
            } else currentPages += pages;
        }
        return true;  // Allocation feasible with given maxPages
    }

    public static int findMinPages(int[] books, int students) {
        if (students > books.length) return -1;

        int low = Integer.MIN_VALUE;
        int high = 0;

        for (int pages : books) {
            low = Math.max(low, pages);
            high += pages;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isFeasible(books, students, mid)) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
}
