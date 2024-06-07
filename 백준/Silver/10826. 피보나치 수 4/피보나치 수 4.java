import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BigInteger[] dp = new BigInteger[10001];

    public static void main(String[] args) throws IOException {
        dp[0] = new BigInteger("0");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("1");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 2].add(dp[i - 1]);
        }
        System.out.println(dp[N]);
    }


}