import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static long[] dp = new long[100];

    public static void main(String[] args) throws IOException {
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(fibo(N));
    }

    public static long fibo(int n) {
        if (dp[n] != -1) return dp[n];
        return dp[n] = fibo(n - 1) + fibo(n - 2);
    }

}