import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        for (int t = 0; t < 3; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] coin = new int[N];
            int[] cnt = new int[N];
            int total = 0;
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                coin[n] = Integer.parseInt(st.nextToken());
                cnt[n] = Integer.parseInt(st.nextToken());
                total += coin[n] * cnt[n];
            }

            if (total % 2 == 1) sb.append(0);
            else {
                int mid = total / 2;
                int[] dp = new int[50001];
                Arrays.fill(dp, Integer.MAX_VALUE);
                dp[0] = 0;
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < mid; j++) {
                        if(dp[j] == Integer.MAX_VALUE) continue;
                        if(j + coin[i] <= mid && dp[j] < cnt[i])
                            dp[j + coin[i]] = Math.min(dp[j + coin[i]], dp[j] + 1);
                        dp[j] = 0;
                    }
                }


                if (dp[mid] != Integer.MAX_VALUE) sb.append(1);
                else sb.append(0);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}