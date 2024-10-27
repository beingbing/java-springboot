package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class S007_cf_154b {
    static final int MAX = 100001;
    private static int[] spf = new int[MAX+1];
    private static Map<Integer, Integer> primeProducerMap = new HashMap<>();
    private static Set<Integer> activeColliders = new HashSet<>();

    private static void processSpfTill(int n) {
        for (int i = 1; i <= n; i++) spf[i] = i;
        for (int p = 2; p * p <= n; p++) {
            if (spf[p] == p) {
                for (int multiple = p * p; multiple <= n; multiple += p) {
                    if (spf[multiple] == multiple) spf[multiple] = p;
                }
            }
        }
    }

    private static boolean hasConflict(int prime, BufferedWriter bw) throws IOException {
        if (primeProducerMap.containsKey(prime)) {
            bw.write("Conflict with " + primeProducerMap.get(prime) + "\n");
            return true;
        }
        return false;
    }

    private static boolean conflictFound(int x, BufferedWriter bw) throws IOException {
        while (x > 1) {
            int prime = spf[x];
            if (hasConflict(prime, bw)) return true;
            while (x % prime == 0) x /= prime;
        }
        return false;
    }

    private static void createMapping(int collider, BufferedWriter bw) throws IOException {
        int x = collider;

        if (conflictFound(x, bw)) return;

        while (x != 1) {
            int prime = spf[x];
            primeProducerMap.put(prime, collider);
            while (x % prime == 0) x /= prime;
        }
        activeColliders.add(collider);
        bw.write("Success\n");
    }

    private static boolean wasActive(int collider, BufferedWriter bw) throws IOException {
        if (!activeColliders.contains(collider)) return false;

        int x = collider;
        while (x > 1) {
            int prime = spf[x];
            primeProducerMap.remove(prime);
            while (x % prime == 0) x /= prime;
        }
        activeColliders.remove(collider);
        bw.write("Success\n");
        return true;
    }

    public static void main(String[] args) throws IOException {
        processSpfTill(MAX);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().trim().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        while (m-- > 0) {
            String[] vals = br.readLine().trim().split(" ");
            int collider = Integer.parseInt(vals[1]);
            if (vals[0].equals("+")) {
                if (!activeColliders.contains(collider)) createMapping(collider, bw);
                else bw.write("Already on\n");
            } else {
                if (!wasActive(collider, bw)) bw.write("Already off\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
