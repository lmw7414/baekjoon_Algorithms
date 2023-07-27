import java.util.Arrays;
import java.util.Scanner;

public class Main {

        static final int INF = 100_000_000;
        static int[] dp;
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            dp = new int[5001];
            Arrays.fill(dp, INF);
            dp[3] = 1;
            dp[5] = 1;
            for(int i = 1; i<= input; i++)
                topDown(input);
            if(dp[input] >= INF) System.out.println(-1);
            else System.out.println(dp[input]);
        }
        public static int topDown(int num) {
            if(num < 0) return INF;
            if(num == 3 || num == 5) return 1;
            if(dp[num] != INF) return dp[num];
            
            return dp[num] = Math.min(topDown(num - 3), topDown(num - 5)) + 1;
        }

}
