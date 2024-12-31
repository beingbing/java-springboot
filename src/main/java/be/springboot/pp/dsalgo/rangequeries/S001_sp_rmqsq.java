package be.springboot.pp.dsalgo.rangequeries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S001_sp_rmqsq { // range minimum queries

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int numberOfElements = Integer.parseInt(bufferedReader.readLine().trim());

        int[] inputArray = new int[numberOfElements];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        for (int index = 0; index < numberOfElements; index++)
            inputArray[index] = Integer.parseInt(tokenizer.nextToken());

        int numberOfQueries = Integer.parseInt(bufferedReader.readLine().trim());

        SqrtDecomposition sqrt = new SqrtDecomposition(inputArray); // Ideal for static range queries with occasional updates.
        SparseTable st = new SparseTable(inputArray); // Best for static range queries when no updates are required.
        SegmentTree segTree = new SegmentTree(inputArray); // Optimal when frequent updates and queries are needed.

        StringBuilder queryResults = new StringBuilder();
        int queryType, leftIndex, rightIndex, updateIndex, newValue;

        for (int queryIndex = 0; queryIndex < numberOfQueries; queryIndex++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            queryType = Integer.parseInt(tokenizer.nextToken());

            if (queryType == 1) {
                leftIndex = Integer.parseInt(tokenizer.nextToken());
                rightIndex = Integer.parseInt(tokenizer.nextToken());
                queryResults.append(sqrt.query(leftIndex, rightIndex)).append("\n");
                queryResults.append(st.query(leftIndex, rightIndex)).append("\n"); // Pre-processing: O(n log n), query: O(1)
                queryResults.append(segTree.query(leftIndex, rightIndex)).append("\n");
            } else {
                updateIndex = Integer.parseInt(tokenizer.nextToken());
                newValue = Integer.parseInt(tokenizer.nextToken());
                inputArray[updateIndex] = newValue;
                sqrt.update(updateIndex, newValue);
                st.update(updateIndex, newValue, inputArray.length);
                segTree.update(updateIndex, newValue);
            }
        }

        // Write all results in one go
        bufferedWriter.write(queryResults.toString());
        bufferedWriter.flush();

        // Close streams
        bufferedReader.close();
        bufferedWriter.close();
    }

}
