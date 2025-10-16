import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[] seeds = new boolean[8];
    static int[] choose = new int[2];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            seeds[f] = true;
            for (int i = x; i < x + l; i++) {
                for (int j = y; j < y + l; j++) {
                    arr[i][j] = f;
                }
            }
        }
        dfs(0, 1);
        System.out.println(answer * answer);
    }

    public static void dfs(int depth, int i) {
        if (depth == 2) return;

        for (int n = i; n <= 7; n++) {
            if (!seeds[n]) continue;
            choose[depth] = n;
            check();
            dfs(depth + 1, n + 1);
            choose[depth] = 0;
        }
    }

    public static void check() {
        int[][] copy = new int[N][N];
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if (arr[j][k] == 0) continue;
                if(arr[j][k] == choose[0] || arr[j][k] == choose[1]) copy[j][k] = 1;
            }
        }
        int[][] dp = new int[N][N];
        int max = 0;
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if (j - 1 < 0 || k - 1 < 0) {
                    dp[j][k] = copy[j][k];
                    continue;
                }
                if (copy[j][k] == 0) continue;

                dp[j][k] = Math.min(Math.min(dp[j - 1][k], dp[j][k - 1]), dp[j - 1][k - 1]) + 1;
                max = Math.max(max, dp[j][k]);
            }
        }
        answer = Math.max(max, answer);
    }

}