import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dp;
    static Set<Integer> midToy = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            dp[X][Y] = K; // X번 부품의 K부품 개수 = Y
            midToy.add(X);
        }
        int[] result = calc(N);
        for (int i = 1; i <= N; i++) {
            if (midToy.contains(i)) continue;
            System.out.println(i + " " + result[i]);
        }
    }

    public static int[] calc(int idx) {
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!midToy.contains(i)) {
                result[i] = dp[idx][i];
                continue;
            }
            if (dp[idx][i] == 0) continue;
            int scope = dp[idx][i];
            dp[idx][i] = 0;
            int[] k = calc(i);
            for (int d = 1; d <= N; d++) {
                dp[idx][d] += scope * k[d];
                result[d] = dp[idx][d];
            }
        }
        return result;
    }

}