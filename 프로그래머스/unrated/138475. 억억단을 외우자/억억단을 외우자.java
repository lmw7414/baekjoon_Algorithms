import java.util.*;
class Solution {
    static int[] dp;
    
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        fillDP(e);
        int[] cnt = new int[e + 1];
        int cur = 0;
        for(int i = e; i>=0; i--) {
            if(cur <= dp[i]) {
                cur = dp[i];
                cnt[i] = i;
            } else
                cnt[i] = cnt[i+1];
        }
        for(int i = 0; i < starts.length; i++) {
            answer[i] = cnt[starts[i]];
        }

        return answer;
    }

    public void fillDP(int e) {
        dp = new int[e + 1];
        Arrays.fill(dp, 1);
        for (int i = 2; i <= e; i++) {
            int cnt = e / i;
            for (int j = 1; j <= cnt; j++)
                if(i*j <= e)
                    dp[i * j]++;
        }
    }

}