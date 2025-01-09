package be.springboot.pp.dsalgo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// this is Java implementation of my C++ code
public class SlidingWindowMedianMultiset {
    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> medians = new ArrayList<>();
        MedianTracker medianTracker = new MedianTracker(k);

        for (int i = 0; i < nums.length; i++) {
            medianTracker.addNumber(nums[i]); // Add the current number to the tracker
            if (i >= k - 1) { // If the window is fully formed
                medians.add(medianTracker.findMedian()); // Calculate the median
                medianTracker.removeNumber(nums[i - k + 1]); // Remove the outgoing number
            }
        }

        // Convert the result to a primitive double array
        return medians.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static void main(String[] args) {
        SlidingWindowMedianMultiset slidingWindowMedian = new SlidingWindowMedianMultiset();

        // Test Case 1
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("Test Case 1 Output: " + Arrays.toString(slidingWindowMedian.medianSlidingWindow(nums1, k1)));

        // Test Case 2
        int[] nums2 = {1, 2, 3, 4, 2, 3, 1, 4, 2};
        int k2 = 3;
        System.out.println("Test Case 2 Output: " + Arrays.toString(slidingWindowMedian.medianSlidingWindow(nums2, k2)));
    }
}

class MedianTracker {
    private final TreeSet<Element> numbers; // Ordered set of elements
    private final Map<Integer, Integer> frequencyMap; // Tracks frequency of numbers
    private final int windowSize;

    public MedianTracker(int windowSize) {
        this.numbers = new TreeSet<>();
        this.frequencyMap = new HashMap<>();
        this.windowSize = windowSize;
    }

    public void addNumber(int num) {
        Element element = new Element(num, frequencyMap.getOrDefault(num, 0));
        numbers.add(element);
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    public void removeNumber(int num) {
        Element element = new Element(num, frequencyMap.get(num) - 1);
        numbers.remove(element);
        frequencyMap.put(num, frequencyMap.get(num) - 1);
        if (frequencyMap.get(num) == 0) {
            frequencyMap.remove(num);
        }
    }

    public double findMedian() {
        int medianIndex1 = (windowSize - 1) / 2;
        int medianIndex2 = windowSize / 2;
        Element[] sortedArray = numbers.toArray(new Element[0]);
        if (windowSize % 2 == 0) {
            return ((double)sortedArray[medianIndex1].value + sortedArray[medianIndex2].value) / 2;
        } else {
            return sortedArray[medianIndex2].value;
        }
    }
}

class Element implements Comparable<Element> {
    int value;
    int frequency;

    Element(int value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Element other) {
        if (this.value != other.value) {
            return Integer.compare(this.value, other.value);
        }
        return Integer.compare(this.frequency, other.frequency);
    }
}