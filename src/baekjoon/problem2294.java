package baekjoon;

import java.util.Scanner;
//동전2
public class problem2294 {

    static int N;  // 동전의 개수
    static int K;  // 동전의 합
    static int coins[];
    static int dp[];
    static int max = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        coins = new int[N];
        dp = new int[K+1];
        for(int i=0; i< N; i++)
            coins[i] = sc.nextInt();

        for(int i = 1; i<=K; i++)
            dp[i] = max - 1;

        // 동전의 갯수를 구하는 것에 유의
        for(int i= 0; i< N; i++) {
            for(int j =coins[i] ; j<=K; j++) {
                dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
            }
        }
        if(dp[K] == max -1)
            System.out.println(-1);
        else
            System.out.println(dp[K]);


    }

}
