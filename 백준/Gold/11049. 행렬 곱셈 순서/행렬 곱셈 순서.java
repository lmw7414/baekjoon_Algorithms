import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int INF = Integer.MAX_VALUE;
    static int[][] dp;
    static int[] arr; // 행렬 r, c 저장(행렬 A와 B 사이의 중복되는 수는 하나만 저장)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            arr[i + 1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N][N];
        // Bottom-Up 방식
        for (int i = 2; i < N + 1; i++) {
            for (int j = 0; j < N - i + 1; j++) { // 시작 지점부터 N에서 i 뺀 만큼
                dp[j][j + i - 1] = INF;
                for (int k = j; k < j + i - 1; k++) {
                    int value = dp[j][k] + dp[k + 1][j + i - 1]
                            + (arr[j] * arr[k + 1] * arr[j + i]);
                    dp[j][j + i - 1] = Math.min(value, dp[j][j + i - 1]);
                }
            }
        }
        System.out.println(dp[0][N - 1]);
        
    }


}