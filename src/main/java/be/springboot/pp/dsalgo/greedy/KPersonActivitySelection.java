package be.springboot.pp.dsalgo.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class KPersonActivitySelection {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            movies.add(new Movie(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxMovies(movies, k)).append("\n");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int maxMovies(List<Movie> movies, int k) {
        movies.sort(Comparator.comparingInt(m -> m.end)); // Sort movies by ending time

        TreeMap<Integer, Integer> available = new TreeMap<>();
        available.put(0, k); // All `k` members start available at time 0

        int count = 0;
        for (Movie movie : movies) {
            Integer latestAvailable = available.floorKey(movie.start); // Find the latest available person
            if (latestAvailable != null) {
                // Remove the availability
                if (available.get(latestAvailable) == 1) available.remove(latestAvailable);
                else available.put(latestAvailable, available.get(latestAvailable) - 1);

                // Assign the movie and update new availability
                available.put(movie.end, available.getOrDefault(movie.end, 0) + 1);
                count++;
            }
        }
        return count;
    }
}

class Movie {
    int start;
    int end;

    public Movie(int s, int e) {
        this.start = s;
        this.end = e;
    }
}