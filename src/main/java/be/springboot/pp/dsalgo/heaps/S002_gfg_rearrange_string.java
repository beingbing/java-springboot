package be.springboot.pp.dsalgo.heaps;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class S002_gfg_rearrange_string {
    public String rearrangeString(String str, int d) {
        if (d == 0) return str;

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : str.toCharArray())
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);

        PriorityQueue<CharFrequency> maxHeap = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet())
            maxHeap.offer(new CharFrequency(entry.getKey(), entry.getValue(), 0));

        // Step 3: Resultant string construction
        StringBuilder result = new StringBuilder();
        Queue<CharFrequency> cooldownQueue = new LinkedList<>();
        int index = 0;

        while (!maxHeap.isEmpty() || !cooldownQueue.isEmpty()) {
            // Check cooldown queue for reinsertion
            if (!cooldownQueue.isEmpty() && cooldownQueue.peek().nextAvailable <= index)
                maxHeap.offer(cooldownQueue.poll());

            if (maxHeap.isEmpty()) return "Not possible";

            // Place the highest frequency character
            CharFrequency current = maxHeap.poll();
            result.append(current.c);
            current.freq--;

            // Add to cooldown queue if still has frequency left
            if (current.freq > 0) {
                current.nextAvailable = index + d;
                cooldownQueue.offer(current);
            }

            index++;
        }

        return result.toString();
    }
}

class CharFrequency {
    char c;
    int freq;
    int nextAvailable;

    CharFrequency(char c, int freq, int nextAvailable) {
        this.c = c;
        this.freq = freq;
        this.nextAvailable = nextAvailable;
    }
}
