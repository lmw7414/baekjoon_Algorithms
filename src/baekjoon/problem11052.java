package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem11052 {

    static int N;
    static int dp[];
    static int p[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        p = new int[N + 1];

        String[] inputs = br.readLine().split(" ");

        for (int i = 1; i <= N; i++) {
            p[i] = Integer.parseInt(inputs[i-1]);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i-j] + p[j]);
            }
        }
        System.out.println(dp[N]);
    }
}
