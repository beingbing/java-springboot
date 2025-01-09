package be.springboot.pp.dsalgo.graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class S004_lc_0433 {

    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> geneBank = new HashSet<>(Arrays.asList(bank));

        // If endGene is not in the bank, return -1
        if (!geneBank.contains(endGene)) return -1;

        // BFS initialization
        Queue<String> queue = new LinkedList<>();
        queue.add(startGene);

        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        char[] choices = {'A', 'C', 'G', 'T'};
        int mutations = 0;

        // BFS loop
        while (!queue.isEmpty()) {
            int size = queue.size();
            mutations++;

            for (int i = 0; i < size; i++) {
                String currentGene = queue.poll();

                // Try mutating each character in the current gene
                for (int j = 0; j < currentGene.length(); j++) {
                    char[] geneArray = currentGene.toCharArray();

                    for (char choice : choices) {
                        if (geneArray[j] == choice) continue; // Skip if no mutation
                        geneArray[j] = choice;

                        String mutatedGene = new String(geneArray);

                        // Check if the mutated gene is valid
                        if (mutatedGene.equals(endGene)) return mutations;
                        if (geneBank.contains(mutatedGene) && !visited.contains(mutatedGene)) {
                            queue.add(mutatedGene);
                            visited.add(mutatedGene);
                        }
                    }
                }
            }
        }

        // If endGene is unreachable
        return -1;
    }

}
