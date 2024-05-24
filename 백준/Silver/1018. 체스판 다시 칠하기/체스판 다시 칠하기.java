import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static boolean[][] board;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                if (c == 'B') board[i][j] = true;
                else board[i][j] = false;
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                int count = 0;
                boolean flag = board[i][j];
                for (int k = i; k < i + 8; k++) {
                    for (int m = j; m < j + 8; m++) {
                        if (board[k][m] != flag) count++;
                        flag = !flag;
                    }
                    flag = !flag;
                }
                answer = Math.min(answer, Math.min(count, 64 - count));
            }
        }

        System.out.println(answer);
    }
}