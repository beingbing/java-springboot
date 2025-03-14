package be.springboot.pp.dsalgo.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class S003_cf_81c {
    public int[] assignSubjects(int n, int a, int b, int[] marks) {
        int[] result = new int[n];

        if (a != b) {
            Mark[] markList = new Mark[n];

            for (int i = 0; i < n; i++)
                markList[i] = new Mark(marks[i], i);

            Arrays.sort(markList, (x, y) -> {
                if (y.value == x.value) {
                    if (a < b) return x.index - y.index; // assign 1 to smallest indices
                    else return y.index - x.index; // assign 2 to largest indices
                }
                return y.value - x.value;
            });
//        for (int i = 0; i < n; i++) System.out.println("mark: " + markList[i].value + " " + markList[i].index);

            int smallerGroup = Math.min(a, b);
            int group1 = a < b ? 1 : 2;
            int group2 = a < b ? 2 : 1;

            for (int i = 0; i < smallerGroup; i++) result[markList[i].index] = group1;
            for (int i = smallerGroup; i < n; i++) result[markList[i].index] = group2;
        } else {
            for (int i = 0; i < a; i++) result[i] = 1;
            for (int i = a; i < n; i++) result[i] = 2;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        String[] groups = br.readLine().trim().split("\\s+");
        int a = Integer.parseInt(groups[0]);
        int b = Integer.parseInt(groups[1]);

        int[] marks = new int[n];

        String[] elements = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++)  marks[i] = Integer.parseInt(elements[i]);

        S003_cf_81c solver = new S003_cf_81c();
        int[] result = solver.assignSubjects(n, a, b, marks);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < n; i++) output.append(result[i]).append(" ");
        output.append("\n");

        bw.write(output.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class Mark { //} implements Comparable<Mark> {
    int value, index;

    Mark(int value, int index) {
        this.value = value;
        this.index = index;
    }

//    @Override
//    public int compareTo(Mark other) {
//        if (this.value != other.value)
//            return Integer.compare(other.value, this.value);
//        return Integer.compare(other.index, this.index);
//    }
}