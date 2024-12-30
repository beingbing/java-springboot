package be.springboot.pp.dsalgo.recursion;

public class S002_hr_recursive_digit_sum {

    private String reduceStr(String str) {
        if (str.length() == 1) return str;

        long sum = 0;
        for (char ch : str.toCharArray()) sum += (ch-'0');

        return reduceStr(String.valueOf(sum));
    }

    public int superDigit(String n, int k) {
        String str = reduceStr(n);
        str = String.valueOf(Integer.parseInt(str) * k);
        return Integer.parseInt(reduceStr(str));
    }
}
