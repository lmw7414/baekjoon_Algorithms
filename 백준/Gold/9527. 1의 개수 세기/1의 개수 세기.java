import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long A, B;
        String[] str = br.readLine().split(" ");
        A = Long.parseLong(str[0]);
        B = Long.parseLong(str[1]);

        // 1. init dp
        int size = Long.toBinaryString(B).length();
        dp = new long[size + 1];
        dp[1] = 1; // 2 보다 작은 수들 중 1의 개수
        for(int i = 2; i <= size; i++) {
            dp[i] = dp[i-1] * 2 + (1L << (i - 1));
        }
        System.out.println(calc(B) - calc(A - 1));

    }

    public static long calc(long num) {
        if(num == 0L) return 0;
        else if(num == 1L) return 1;

        int size = Long.toBinaryString(num).length();
        long next = num - (1L << (size - 1));
        return dp[size - 1] + next + 1 + calc(next);
    }

}