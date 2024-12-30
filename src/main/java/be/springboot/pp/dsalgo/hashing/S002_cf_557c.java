package be.springboot.pp.dsalgo.hashing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class S002_cf_557c {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int[] lengths = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] energies = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        S002_cf_557c solver = new S002_cf_557c();
        writer.println(solver.minEnergyToStabilize(n, lengths, energies));
        writer.close();
        br.close();
    }

    private int minEnergyToStabilize(int n, int[] lengths, int[] energies) {
        Leg[] legs = new Leg[n];
        for (int i = 0; i < n; i++) legs[i] = new Leg(lengths[i], energies[i]);

        Arrays.sort(legs, Comparator.comparingInt(Leg::getLength)); // asc sorting

        int totalEnergy = Arrays.stream(energies).sum(); // energy needed to remove all legs

        // Array to count the frequency of energy costs (200 is the max energy cost per leg)
        int[] energyFrequency = new int[201]; // energy of groups already considered before
        // this energy will be added back to current-energy again if the table is still not stable
        // after removing all legs with length longer than current-group.

        Arrays.fill(energyFrequency, 0);
        int minEnergy = Integer.MAX_VALUE;

        for (int i = 0; i < n; ) {
            int start = i; // Start index of the current group of legs with the same length

            // Deduct energy of the current group from total energy
            while (i < n && legs[i].getLength() == legs[start].getLength()) {
                totalEnergy -= legs[i].getEnergy();
                i++;
            }

            int groupSize = i - start; // Number of legs in the current group
            int legsToKeep = groupSize; // Number of legs of current length to keep
            int legsToRemove = start; // Remaining legs to potentially remove

            int currentEnergy = totalEnergy; // contains energy of all the legs longer than current group

            // Calculate the energy needed to stabilize by removing excess shorter legs
            for (int j = 1; j <= 200; j++) { // a tree-hash could be used instead of freq-ar, but need first entry as 0:0
                if (legsToRemove - energyFrequency[j] < legsToKeep) {
                    currentEnergy += j * Math.max(0, legsToRemove - legsToKeep + 1);
                    break;
                } else {
                    legsToRemove -= energyFrequency[j];
                    currentEnergy += j * energyFrequency[j];
                }
            }

            minEnergy = Math.min(minEnergy, currentEnergy);

            // Update frequency count for the legs in the current group
            // will be added again to stabilize groups with length longer than current-group
            for (int j = start; j < i; j++)
                energyFrequency[legs[j].getEnergy()]++;
        }
        return minEnergy;
    }
}

class Leg {
    private int length; // Length of the leg
    private int energy; // Energy required to remove the leg

    Leg(int length, int energy) {
        this.length = length;
        this.energy = energy;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
