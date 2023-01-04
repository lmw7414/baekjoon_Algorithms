package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//최소비용 구하기
public class problem1916 {

    static int N;  // 노드 개수
    static int C;  // 길 개수

    static int INF = 987654321;
    static int[][] board;
    static int[] D;
    static boolean[] visited;
    static int start;
    static int end;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());

        board = new int[N + 1][N + 1];
        D = new int[N + 1];
        visited = new boolean[N + 1];

        initBoard();

        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(board[a][b] != -1 && board[a][b] < c)
                continue;

            board[a][b] = c;
        }
        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());


        dijkstra(start);
        System.out.println(D[end]);

    }

    public static void dijkstra(int start) {
        for (int i = 1; i < N + 1; i++)
            D[i] = board[start][i];
        visited[start] = true;

        for (int i = 0; i < N - 2; i++) {
            int idx = getSmallIdx();

            visited[idx] = true;
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && D[j] > D[idx] + board[idx][j])
                    D[j] = D[idx] + board[idx][j];
            }
        }
    }

    public static int getSmallIdx() {
        int min = INF;
        int index = 0;

        for (int i = 1; i <= N; i++) {
            if (D[i] < min && !visited[i]) {
                min = D[i];
                index = i;
            }
        }
        return index;
    }

    public static void initBoard() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                board[i][j] = INF;
            }
            board[i][i] = -1;
        }

    }
}
