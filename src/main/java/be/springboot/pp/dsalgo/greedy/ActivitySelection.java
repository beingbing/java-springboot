package be.springboot.pp.dsalgo.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActivitySelection {
    public static int maxActivities(int[] start, int[] end) {
        int n = start.length;
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < n; i++) activities.add(new Activity(start[i], end[i]));

        activities.sort(Comparator.comparingInt(a -> a.end)); // Sort activities by end time

        int count = 0;
        int lastEnd = -1;

        for (Activity activity : activities) {
            if (activity.start >= lastEnd) {
                count++;
                lastEnd = activity.end;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        System.out.println("Max activities: " + maxActivities(start, end)); // Output: 4
    }
}

class Activity {
    int start, end;
    public Activity(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
