
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int[][] board;

    static final int INF = 999_999_999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        initBoard(N);

        for(int i = 0; i< M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(board[s][e] > w)
                board[s][e] = w;
        }


        for(int k = 1; k<= N; k++) {
            for(int s = 1; s <=N; s++) {
                for(int e = 1; e <=N; e++) {
                    board[s][e] = Math.min(board[s][e], board[s][k] + board[k][e]);
                }
            }
        }

        for(int i =1; i<=N; i++) {
            for(int j = 1; j<=N; j++) {
                if(board[i][j] == INF)
                    System.out.print(0 + " ");
                else
                    System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void initBoard(int N) {
        for(int i = 1; i<= N; i++)
            Arrays.fill(board[i], INF);

        for(int i = 1; i<= N; i++)
            board[i][i] = 0;
    }
}

