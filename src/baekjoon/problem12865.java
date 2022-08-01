package baekjoon;

import java.util.Scanner;
//평범한 배낭
public class problem12865 {

    static int N;
    static int M;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        int weight[] = new int[N+1];
        int value[] = new int[N+1];
        int dp[][] = new int[N+1][M+1];


        for(int i= 1 ; i<= N; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        for(int i =1; i<=N; i++) {
            for(int j = 1; j<=M; j++) {
                if(weight[i] <= j)
                    dp[i][j] = Math.max(dp[i-1][j], value[i] + dp[i-1][j - weight[i]]);  // dp배열의 위의 값과 value[i] + weight[i]의 무게가 들어가기 전의 무게 중 더 큰 값을 반환한다.
                else
                    dp[i][j] = dp[i-1][j];  // 무게가 j보다 작을 경우 dp인덱스의 위의 값 그대로 가져온다.
            }
        }
        System.out.println(dp[N][M]);
    }

}
