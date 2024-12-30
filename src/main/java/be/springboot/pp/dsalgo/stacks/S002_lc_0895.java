package be.springboot.pp.dsalgo.stacks;

import java.util.HashMap;
import java.util.Stack;

class S002_lc_0895 {
    private HashMap<Integer, Integer> freqMap;  // Maps element to its frequency
    private HashMap<Integer, Stack<Integer>> groupMap; // Maps frequency to a stack of elements
    private int maxFreq; // Tracks the current maximum frequency

    public S002_lc_0895() {
        freqMap = new HashMap<>();
        groupMap = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        // Update frequency map
        int freq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, freq);

        // Update group map
        groupMap.computeIfAbsent(freq, x -> new Stack<>()).push(val);

        // Update max frequency
        maxFreq = Math.max(maxFreq, freq);
    }

    public int pop() {
        // Retrieve the element with the maximum frequency
        Stack<Integer> maxFreqStack = groupMap.get(maxFreq);
        int val = maxFreqStack.pop();

        // Decrease frequency in frequency map
        freqMap.put(val, freqMap.get(val) - 1);

        // If the stack for the max frequency is empty, decrement maxFreq
        if (maxFreqStack.isEmpty()) {
            maxFreq--;
        }

        return val;
    }
}
