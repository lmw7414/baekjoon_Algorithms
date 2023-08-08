import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        dp = new int[4][N + 1];  // 소형 기관차 3대를 이용

        for(int i = 1; i < 4; i++) {
            for(int j = i * M; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j - M] + (arr[j] - arr[j - M]));
            }
        }
        System.out.println(dp[3][N]);
    }

}