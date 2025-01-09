package be.springboot.pp.dsalgo.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KPersonActivitySelection {
    public static int maxActivitiesKPersons(int[] start, int[] end, int k) {
        int n = start.length;
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < n; i++) activities.add(new Activity(start[i], end[i]));

        activities.sort(Comparator.comparingInt(a -> a.end));

        int count = 0;
        PriorityQueue<Integer> availablePersons = new PriorityQueue<>(); // Priority queue to store the earliest available time of each person
        for (int i = 0; i < k; i++) availablePersons.add(0); // Initialize all persons as available from time 0

        for (Activity activity : activities) {
            if (!availablePersons.isEmpty() && availablePersons.peek() <= activity.start) { // Check if the earliest available person can take this activity
                // Assign the activity to this person
                availablePersons.poll(); // Remove this person from the heap
                availablePersons.add(activity.end); // Update their availability
                count++; // Increment the count of activities performed
            }
        }

        return count;
    }
}
