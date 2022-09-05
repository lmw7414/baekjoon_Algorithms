package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//로봇 조종하기
public class problem2169 {

    static int N;
    static int M;
    static int board[][];
    static int dp[][];
    static int temp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dp = new int[N][M];
        temp = new int[2][M+2];  //비교할 때 규칙성을 부여하기 위해 양옆으로 추가 인덱스 생성하여 dp의 첫번째와 마지막 데이터를 temp의 양쪽에 배치한다.

        for(int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = board[0][0];

        for(int i = 1; i< M; i++)
            dp[0][i] = dp[0][i-1] + board[0][i];


        for(int i = 1; i < N; i++) {
            // To right  -> 위와 오른쪽을 비교
            temp[0][0] = dp[i-1][0];
            for(int j = 1; j <= M; j++)
                temp[0][j] = Math.max(temp[0][j-1], dp[i-1][j-1]) + board[i][j-1];

            // To left   <-  위와 왼쪽을 비교
            temp[1][M+1] = dp[i-1][M-1];
            for(int j = M; j>0; j--)
                temp[1][j] = Math.max(temp[1][j+1], dp[i-1][j-1]) + board[i][j-1];

            // 최댓값 찾아서 dp 갱신
            for(int j=1; j<= M; j++)
                dp[i][j-1] = Math.max(temp[0][j], temp[1][j]);

//            System.out.println("\n---temp---\n");
//            for(int k = 0; k< 2; k++){
//                for(int j = 1; j<= M; j++)
//                    System.out.printf(temp[k][j] + " ");
//                System.out.println();
//            }
        }
//        System.out.println();
//        for(int i = 0; i< N; i++){
//            for(int j = 0; j < M; j++)
//                System.out.printf(dp[i][j] + " ");
//            System.out.println();
//        }

        System.out.println(dp[N-1][M-1]);
    }



}
