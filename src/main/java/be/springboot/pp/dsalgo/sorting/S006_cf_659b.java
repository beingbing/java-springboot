package be.springboot.pp.dsalgo.sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class S006_cf_659b {
    // Sorting participants by score allows direct comparison of the top scorers,
    // simplifying the process of determining unique teams.
    // Processing each region independently ensures that operations remain
    // manageable, even for large inputs.
    // By always selecting the top two scorers unless ties exist, the algorithm
    // efficiently identifies valid teams or invalid cases.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: number of participants and regions
        int participants = sc.nextInt();
        int regions = sc.nextInt();

        // Map to group participants by region
        Map<Integer, List<Participant>> regionMap = new HashMap<>();

        // Read participant data
        for (int i = 0; i < participants; i++) {
            String name = sc.next();
            int region = sc.nextInt();
            int score = sc.nextInt();

            // Add participant to the corresponding region group
            regionMap.putIfAbsent(region, new ArrayList<>());
            regionMap.get(region).add(new Participant(name, score));
        }

        // Output results for each region
        for (int i = 1; i <= regions; i++) {
            List<Participant> regionParticipants = regionMap.get(i);

            // Sort participants in descending order of score
            regionParticipants.sort((a, b) -> Integer.compare(b.score, a.score));

            // Check for ties between 2nd and 3rd position participant
            if (regionParticipants.size() > 2
                    && regionParticipants.get(1).score == regionParticipants.get(2).score)
                System.out.println("?");
            else System.out.println(regionParticipants.get(0).name + " " + regionParticipants.get(1).name);
        }
        sc.close();
    }
}

class Participant {
    String name;
    int score;

    Participant(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
