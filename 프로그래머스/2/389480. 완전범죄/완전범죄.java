class Solution {
    public int solution(int[][] info, int n, int m) {
        int[][][] dp = new int[info.length + 1][n][m];
        for(int i = 0; i <= info.length; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        dp[0][0][0] = 0;
        for(int i = 0; i < info.length; i++) {
            int A = info[i][0];
            int B = info[i][1];
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    if(dp[i][j][k] == Integer.MAX_VALUE) continue;
                    
                    // A 선택
                    if(j + A < n) {
                        dp[i + 1][j + A][k] = Math.min(dp[i + 1][j + A][k], dp[i][j][k] + A);
                    }
                    // B 선택
                    if(k + B < m) {
                        dp[i + 1][j][k + B] = Math.min(dp[i + 1][j][k + B], dp[i][j][k] + B);
                    }
                }
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(dp[info.length][i][j] != Integer.MAX_VALUE) return i;
            }
        }
        
        return -1;
    }
}