import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M; // 최대 100
    static int INF = 987654321;
    static int maxCoupon = 40;
    static int[][] dp;
    static boolean[] days;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[maxCoupon + 1][N + 6];
        for(int i = 0; i < maxCoupon + 1; i++) Arrays.fill(dp[i], INF);
        days = new boolean[106];
        if(M > 0) {
            st = new StringTokenizer(br.readLine());
            for(int m = 0;  m < M; m++) days[Integer.parseInt(st.nextToken())] = true;
        }
        


        dp[0][0] = 0;
        for(int day = 0; day < N; day++) {
            for(int coupon = 0; coupon <= maxCoupon; coupon++) {
                if(dp[coupon][day] == INF) continue;
                if(days[day + 1]) // 다음 날이 쉬는 날 인경우
                    dp[coupon][day + 1] = Math.min(dp[coupon][day], dp[coupon][day + 1]);
                else // 1일권 구매
                    dp[coupon][day + 1] = Math.min(dp[coupon][day] + 10000, dp[coupon][day + 1]);
                // 3일권
                if(coupon + 1 <= maxCoupon && day + 3 <= N) dp[coupon + 1][day + 3] = Math.min(dp[coupon][day] + 25000, dp[coupon + 1][day + 3]);
                // 5일권
                if(coupon + 2 <= maxCoupon && day + 5 <= N) dp[coupon + 2][day + 5] = Math.min(dp[coupon][day] + 37000, dp[coupon + 2][day + 5]);

                if(coupon >= 3) {
                    if(!days[day+1] && day + 1 <= N)dp[coupon - 3][day + 1] = Math.min(dp[coupon][day], dp[coupon - 3][day + 1]);
                }
            }
        }
        int answer = INF;
        for(int i = 0; i <= maxCoupon; i++) answer = Math.min(answer, dp[i][N]);
        System.out.println(answer);
    }
}