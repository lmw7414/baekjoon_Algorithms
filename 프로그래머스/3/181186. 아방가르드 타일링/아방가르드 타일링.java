class Solution {
    public int solution(int n) {
        int MOD = 1_000_000_007;
        long[] dp = new long[100001];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23; // dp[3] + dp[2] * 2 + dp[1] * 5 + 2
        dp[5] = 62; // dp[4] + dp[3] * 2 + dp[2] * 5 + dp[1] * 2 + 2
        dp[6] = 170;
        
        if(n <= 6) return (int)dp[n];
        for(int i = 7; i <= n; i++) {
            dp[i] = (dp[i-1] + (2 * dp[i-2] % MOD) + (6 * dp[i-3] % MOD) + (dp[i-4] - dp[i-6] + MOD) % MOD) % MOD;
        }
        return (int)dp[n];
    }
}

/*
1 -> 1 | 1
2 -> 3 | 2
3 -> 10 | 5
4 -> 23 | 2
5 -> 62 | 2
6 -> 170 | 4
*/