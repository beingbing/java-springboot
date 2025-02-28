package be.springboot.pp.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class StringPermutations {
    private void generatePermutations(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        for (int newCharIndex = index; newCharIndex < chars.length; newCharIndex++) {
            swap(chars, index, newCharIndex);
            generatePermutations(chars, index + 1, result);
            swap(chars, index, newCharIndex);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public List<String> permute(String s) {
        List<String> result = new ArrayList<>();
        generatePermutations(s.toCharArray(), 0, result);
        return result;
    }

    private static void relativePermute(String prefix, String remaining, List<String> result) {
        if (remaining.isEmpty()) {
            result.add(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            relativePermute(prefix + remaining.charAt(i), remaining.substring(i + 1) + remaining.substring(0, i), result);
        }
    }

    public List<String> relativePermute(String s) {
        List<String> result = new ArrayList<>();
        relativePermute("", s, result);
        return result;
    }

    public List<String> cyclicPermutation(String s) {
        List<String> result = new ArrayList<>();
        cyclicPermutation(s.toCharArray(), 0, result);
        return result;
    }

    private static void cyclicPermutation(char[] arr, int index, List<String> result) {
        if (index == arr.length) {
            result.add(new String(arr));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            cyclicShiftRight(arr, index, i); // Shift right to maintain order
            cyclicPermutation(arr, index + 1, result);
            cyclicShiftLeft(arr, index, i); // Revert the shift (backtrack)
        }
    }

    private static void cyclicShiftRight(char[] arr, int start, int end) {
        char temp = arr[end];
        for (int i = end; i > start; i--) arr[i] = arr[i - 1];
        arr[start] = temp;
    }

    private static void cyclicShiftLeft(char[] arr, int start, int end) {
        char temp = arr[start];
        for (int i = start; i < end; i++) arr[i] = arr[i + 1];
        arr[end] = temp;
    }

    public static void main(String[] args) {
        StringPermutations permuter = new StringPermutations();
        System.out.println(permuter.permute("pmla"));  // Expected output: [abc, acb, bac, bca, cab, cba]
        System.out.println(permuter.relativePermute("pmla"));  // Expected output: [abc, acb, bac, bca, cab, cba]
        System.out.println(permuter.cyclicPermutation("pmla"));  // Expected output: [abc, acb, bac, bca, cab, cba]
    }
}
