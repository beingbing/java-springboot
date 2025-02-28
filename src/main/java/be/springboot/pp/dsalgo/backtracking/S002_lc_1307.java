package be.springboot.pp.dsalgo.backtracking;

import java.util.HashSet;
import java.util.Set;

public class S002_lc_1307 {
    private static final int[] POWERS_OF_TEN = {1, 10, 100, 1000, 10000, 100000, 1000000};

    public boolean isSolvable(String[] words, String result) {
        Set<Character> uniqueCharacters = new HashSet<>();
        int[] characterWeights = new int[91]; // ASCII range for 'A' to 'Z' is in 65-90
        boolean[] cannotBeZero = new boolean[91];

        calculateCharacterWeights(words, uniqueCharacters, characterWeights, cannotBeZero);
        updateResultWeights(result, uniqueCharacters, characterWeights, cannotBeZero);

        char[] characterList = new char[uniqueCharacters.size()];
        int index = 0;
        for (char c : uniqueCharacters) {
            characterList[index++] = c;
        }

        return solveWithBacktracking(new boolean[10],
                characterList, cannotBeZero, 0, 0, characterWeights);
    }

    private void calculateCharacterWeights(String[] words,
                                           Set<Character> uniqueCharacters,
                                           int[] characterWeights,
                                           boolean[] cannotBeZero) {
        for (String word : words) {
            char[] characters = word.toCharArray();
            for (int i = 0; i < characters.length; i++) {
                if (i == 0 && characters.length > 1) cannotBeZero[characters[i]] = true;
                uniqueCharacters.add(characters[i]);
                characterWeights[characters[i]] += POWERS_OF_TEN[characters.length - i - 1];
            }
        }
    }

    private void updateResultWeights(String result,
                                     Set<Character> uniqueCharacters,
                                     int[] characterWeights,
                                     boolean[] cannotBeZero) {
        char[] characters = result.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (i == 0 && characters.length > 1) cannotBeZero[characters[i]] = true;
            uniqueCharacters.add(characters[i]);
            characterWeights[characters[i]] -= POWERS_OF_TEN[characters.length - i - 1];
        }
    }

    private boolean solveWithBacktracking(boolean[] usedDigits,
                                          char[] characterList,
                                          boolean[] cannotBeZero,
                                          int index,
                                          int currentSum,
                                          int[] characterWeights) {
        if (index == characterList.length) return currentSum == 0;

        char currentCharacter = characterList[index];
        for (int digit = 0; digit <= 9; digit++) {
            if (!usedDigits[digit] && (digit > 0 || !cannotBeZero[currentCharacter])) {
                usedDigits[digit] = true;
                if (solveWithBacktracking(usedDigits,
                        characterList,
                        cannotBeZero,
                        index + 1,
                        currentSum + characterWeights[currentCharacter] * digit,
                        characterWeights)) return true;
                usedDigits[digit] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        S002_lc_1307 solver = new S002_lc_1307();
        String[] words = {"SEND", "MORE"};
        String result = "MONEY";
        System.out.println(solver.isSolvable(words, result));
    }
}