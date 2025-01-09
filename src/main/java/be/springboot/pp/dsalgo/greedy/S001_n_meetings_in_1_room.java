package be.springboot.pp.dsalgo.greedy;

import java.util.ArrayList;
import java.util.List;

public class S001_n_meetings_in_1_room {
    public int maxMeetings(int[] start, int[] end) {
        int n = start.length;
        List<Meeting> meetings = new ArrayList<>(); // Create a list of meetings
        for (int i = 0; i < n; i++) meetings.add(new Meeting(start[i], end[i]));

        meetings.sort((a, b) -> a.end - b.end); // Sort meetings by their end time (meetings.sort(Comparator.comparingInt(m -> m.end));)

        int count = 0; // To count the maximum number of meetings
        int lastEndTime = -1; // To track the end time of the last selected meeting

        for (Meeting meeting : meetings) {
            if (meeting.start > lastEndTime) { // Check if the meeting can be selected
                count++;
                lastEndTime = meeting.end; // Update the end time of the last selected meeting
            }
        }

        return count; // Return the maximum number of meetings
    }
}

class Meeting {
    int start;
    int end;

    Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
