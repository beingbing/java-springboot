package be.springboot.pp.dsalgo.stacks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class S004_cf_343b {
    public static String canUntangle(String inputString) {
        Stack<Character> characterStack = new Stack<>();

        for (char currentChar : inputString.toCharArray())
            if (!characterStack.isEmpty() && characterStack.peek() == currentChar) characterStack.pop();
            else characterStack.push(currentChar);

        return characterStack.isEmpty() ? "Yes" : "No";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();
        String S = br.readLine().trim();
        result.append(canUntangle(S)).append("\n");
        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
