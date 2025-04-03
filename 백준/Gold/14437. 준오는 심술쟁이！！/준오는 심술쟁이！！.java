
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[][] dp = new int[str.length() + 1][S + 1]; //[i][j] i번째 문자까지 사용한 횟수 j
        dp[0][0] = 1;
        for(int i = 1; i <= str.length(); i++) {
            for(int j = 0; j <= S; j++) {
                if(dp[i - 1][j] != 0) {
                    for(int k = 0; k <= 25; k++) {
                        if(j + k > S) break;
                        dp[i][j + k] = (dp[i][j + k] + dp[i-1][j]) % 1000000007;
                    }
                }
            }
        }
        System.out.println(dp[str.length()][S]);
    }

}