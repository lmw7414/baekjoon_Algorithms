package baekjoon;

import java.io.IOException;
import java.util.Scanner;

public class problem11051 {

    static int N;
    static int K;

    static int[][] dp;
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        dp = new int[N+1][K+1];
        System.out.println(cal(N, K));

    }

    public static int cal(int n, int k) {

        if(dp[n][k] > 0)
            return dp[n][k];

        if (n == k || k == 0)
            return dp[n][k] = 1;

        return dp[n][k] = (cal(n - 1, k - 1) + cal(n - 1, k)) % 10007;
    }
}
