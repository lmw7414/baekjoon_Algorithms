
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] table, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        table = new int[2][N + 1];
        dp = new int[2][N + 1]; // dp[0][]: 해당 상담을 스킵 | dp[1] 해당 상담 진행

        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            table[0][i] = Integer.parseInt(st.nextToken());
            table[1][i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            dp[0][i] = Math.max(dp[0][i-1], dp[1][i-1]);
            int nextIdx = table[0][i] + i - 1;
            if(nextIdx > N) continue;
            dp[1][nextIdx] = Math.max(dp[1][nextIdx], table[1][i] + dp[0][i]);
        }

        System.out.println(Math.max(dp[0][N], dp[1][N]));
    }

}