import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a, b, d, n;
        a = Integer.parseInt(st.nextToken()); // 성체가 되는 날
        b = Integer.parseInt(st.nextToken()); // 분열 금지
        d = Integer.parseInt(st.nextToken()); // 죽음
        n = Integer.parseInt(st.nextToken()); // N일 째

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            if (i < a) dp[i] = dp[i - 1] % 1000;
            else if (i < b) dp[i] = (dp[i - 1] + dp[i - a]) % 1000; // 기존 개체 + 새로 추가되는 개체
            else dp[i] = (dp[i - 1] + dp[i - a] - dp[i - b] + 1000) % 1000;  // 첫번째 개체 정지 구간 돌입 | 못낳는 애들 빼줘야 함
        }
        if (n - d >= 0) System.out.println((dp[n] - dp[n - d] + 1000) % 1000);
        else System.out.println(dp[n]); // n일이 개체 죽기 전인 경우
    }

}