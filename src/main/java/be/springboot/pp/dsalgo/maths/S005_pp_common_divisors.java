package be.springboot.pp.dsalgo.maths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S005_pp_common_divisors {

    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    private static long commonDivisorsCount(long a, long b) {
        long gcd = gcd(a, b);
        System.out.println("gcd: " + gcd);

        int count = 0;

        for (long i = 1; i*i <= gcd; i++) {
            if (gcd%i == 0) {
                if (i == gcd/i) count++;
                else count += 2;
            }
        }
        return count;
    }

    public static void main (String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            String[] inputs = br.readLine().trim().split(" ");
            bw.write(commonDivisorsCount(Long.parseLong(inputs[0]), Long.parseLong(inputs[1])) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
