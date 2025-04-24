
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int DIV = 1_000_000;
        int[][][] dp = new int[N + 1][2][3]; // 일수|지각|연속 결석 -> 현재까지의 개근상 경우 개수

        dp[1][0][0] = 1; // 출석
        dp[1][1][0] = 1; // 지각
        dp[1][0][1] = 1; // 결석

        //dp[N][0][0] += dp[N-1][0][0] + dp[N-1][0][1] + dp[n-1][0][2]                 | N-1일차에(출석, 연속 결석 1회, 연속 결석 2회)
        //dp[N][1][0] += dp[N-1][1][0] + dp[N-1][0][0] + dp[N-1][0][1] + dp[n-1][0][2] | N-1일차에(출석, (지각1회 연속 결석 1회), (지각 1회 연속 결석 2회))
        //dp[N][1][1] += dp[N-1][1][0]                                                 | N-1일차에(출석)
        //dp[N][1][2] += dp[N-1][1][1]                                                 | N-1일차에(연속 결석 1회)
        for (int i = 2; i <= N; i++) {
            dp[i][0][0] += (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % DIV;
            dp[i][1][0] += (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2] + dp[i - 1][1][2]) % DIV;
            dp[i][0][1] += (dp[i - 1][0][0]) % DIV;
            dp[i][1][1] += (dp[i - 1][1][0]) % DIV;
            dp[i][0][2] += (dp[i - 1][0][1]) % DIV;
            dp[i][1][2] += (dp[i - 1][1][1]) % DIV;
        }

        System.out.println((dp[N][0][0] + dp[N][1][0] + dp[N][0][1] + dp[N][0][2] + dp[N][1][1] + dp[N][1][2]) % DIV);
    }
}
