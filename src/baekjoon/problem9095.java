package baekjoon;

import java.util.Scanner;

public class problem9095 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] tc = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            int input = sc.nextInt();
            if (max < input)
                max = input;
            tc[i] = input;
        }
        int[] dp = new int[max + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int j = 4; j <= max; j++)
            dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];

        for (int i = 0; i < n; i++) {
            System.out.println(dp[tc[i]]);
        }
    }

}
