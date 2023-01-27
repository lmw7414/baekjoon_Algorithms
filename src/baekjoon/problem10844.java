package baekjoon;

import java.util.Scanner;

public class problem10844 {

    static final int MOD = 1_000_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long answer = 0;
        long[][] dp = new long[N+1][10];

        for(int i = 1; i< 10; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i<=N; i++) {
            for(int j = 0; j <= 9; j++) {
                if( j == 0) {
                    dp[i][j] = dp[i-1][1];
                } else if( j == 9) {
                    dp[i][j] = dp[i-1][8];
                } else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
                    dp[i][j] %= MOD;
                }
            }
        }
        for(int i = 0; i< 10; i++)
            answer += dp[N][i];
        System.out.println(answer % MOD);
    }
}
