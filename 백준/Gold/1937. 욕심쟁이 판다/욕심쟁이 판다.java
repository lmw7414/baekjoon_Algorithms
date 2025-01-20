import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//욕심쟁이 판다
public class Main {

    static int N;
    static int MAX = 0;
    static int board[][];
    static int dp[][];
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        dp = new int[N][N];
        visited = new boolean[N][N];

        for(int i= 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i= 0 ; i< N; i++ ) {
            for(int j = 0; j< N; j++) {
                MAX = Math.max(DFS(i, j), MAX);
            }
        }
        System.out.println(MAX);
    }

    static int DFS(int x, int y) {
        int dx[] = {0, 1, 0, -1};
        int dy[] = {-1, 0, 1, 0};

        if (dp[x][y] != 0)
            return dp[x][y];

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cx < N && cy >= 0 && cy < N)
                if (board[x][y] < board[cx][cy])
                    dp[x][y] = Math.max(dp[x][y], DFS(cx, cy) + 1);
        }
        return dp[x][y];
    }
}
