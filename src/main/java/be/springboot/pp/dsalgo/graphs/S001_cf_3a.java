package be.springboot.pp.dsalgo.graphs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class S001_cf_3a {

    public static List<String> findKingMoves(String start, String target) {
        // Convert chessboard letters to numeric column indices (a=1, b=2, ..., h=8)
        int startColumn = start.charAt(0) - 'a' + 1;
        int startRow = start.charAt(1) - '0';
        int targetColumn = target.charAt(0) - 'a' + 1;
        int targetRow = target.charAt(1) - '0';

        // List to store the sequence of moves
        List<String> moves = new ArrayList<>();

        // Iterate until the king reaches the target position
        while (startColumn != targetColumn || startRow != targetRow) {
            StringBuilder currentMove = new StringBuilder();

            // Adjust column position (left or right)
            if (startColumn < targetColumn) {
                currentMove.append("R"); // Move right
                startColumn++;
            } else if (startColumn > targetColumn) {
                currentMove.append("L"); // Move left
                startColumn--;
            }

            // Adjust row position (up or down)
            if (startRow < targetRow) {
                currentMove.append("U"); // Move up
                startRow++;
            } else if (startRow > targetRow) {
                currentMove.append("D"); // Move down
                startRow--;
            }

            // Add the calculated move to the list
            moves.add(currentMove.toString());
        }

        return moves;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = br.readLine().trim();
        String target = br.readLine().trim();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        List<String> moves = findKingMoves(start, target);
        result.append(moves.size()).append("\n");
        for (String move : moves) result.append(move).append("\n");

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
