package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//이동하기
public class problem11048 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int board[][] = new int[N+1][M+1];
        for(int i = 1; i<= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=M; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i<= N; i++){
            for(int j = 1; j<=M; j++){
                board[i][j] = board[i][j]  + Math.max(Math.max(board[i][j-1], board[i-1][j-1]), board[i-1][j]);
            }
        }

        System.out.println(board[N][M]);

    }
}
