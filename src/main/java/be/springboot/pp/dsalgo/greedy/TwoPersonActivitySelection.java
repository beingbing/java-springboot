package be.springboot.pp.dsalgo.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TwoPersonActivitySelection {
    public static int maxActivitiesTwoPersons(int[] start, int[] end) {
        int n = start.length;
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < n; i++) activities.add(new Activity(start[i], end[i]));

        activities.sort(Comparator.comparingInt(a -> a.end)); // Sort activities by end time

        int count = 0;
        int endTime1 = 0, endTime2 = 0;

        for (Activity activity : activities) {
            // the second person will always get the earliest finishing activity in comparison
            //  to the first person, so if both can perform an activity then check if the
            // second person can perform it, if yes then assign it to the second person
            // in that way we can get more activities completed
            if (activity.start >= endTime1 || activity.start >= endTime2) {
                count++;
                if (activity.start >= endTime2) endTime2 = activity.end;
                else endTime1 = activity.end;
            }
        }

        return count;
    }
}
