package be.springboot.pp.dsalgo.hashing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class S003_cf_705c {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split("\\s+");

        int n = Integer.parseInt(input[0]); // Number of applications
        int q = Integer.parseInt(input[1]); // Number of events

        int[] appUnread = new int[n + 1]; // Unread notifications per application
        int[] appTotalTillNow = new int[n + 1]; // To track if a notification is read
        int totalUnread = 0; // Total unread notifications
        int readIndex = 0; // Index for type 3 operations
        ArrayList<Integer> chronologicalNotifications = new ArrayList<>();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < q; i++) {
            input = br.readLine().trim().split("\\s+");
            int type = Integer.parseInt(input[0]);

            if (type == 1) { // new unread noti added in both app and home-screen
                int app = Integer.parseInt(input[1]);
                appUnread[app]++; // unread message counter inside app
                appTotalTillNow[app]++; // unread message counter for home-screen
                totalUnread++;
                chronologicalNotifications.add(app); // Track notifications by app
            } else if (type == 2) { // noti read from inside app
                int app = Integer.parseInt(input[1]);
                totalUnread -= appUnread[app]; // if noti read from inside app
                appUnread[app] = 0; // only app noti counter resets, home-screen still has that noti
            } else if (type == 3) { // noti read from home-screen
                int t = Integer.parseInt(input[1]); // remove t notifications from home-screen
                while (readIndex < t) {
                    int app = chronologicalNotifications.get(readIndex);
                    if (appTotalTillNow[app] > 0) appTotalTillNow[app]--; // if not read from home-screen
                    if (appUnread[app] > appTotalTillNow[app]) { // app counter resets as well
                        appUnread[app]--;
                        totalUnread--;
                    }
                    readIndex++; // Move to the next home-screen notification
                }
            }
            result.append(totalUnread).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
