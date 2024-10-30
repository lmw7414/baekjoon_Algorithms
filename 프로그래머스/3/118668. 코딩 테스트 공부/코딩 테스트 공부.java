import java.util.*;
// 모든 문제를 풀지 않아도 됨. 모든 문제를 풀수 있는 수준의 알고력과 코딩력만 갖춰야 함.
class Solution {
    static final int INF = 987654321;
    static int maxAlp, maxCop; // 도달해야 하는 최대 알고력, 최대 코딩력
    static int[][] dp;
    
    public int solution(int alp, int cop, int[][] problems) {
        maxAlp = alp;
        maxCop = cop;
        for(int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        dp = new int[maxAlp + 1][maxCop + 1];
        for(int i = 0; i <= maxAlp; i++) Arrays.fill(dp[i], INF);
        dp[alp][cop] = 0; // 초기 알고력과 코딩력 -> 시간
        
        for(int al = alp; al <= maxAlp; al++) {
            for(int co = cop; co <= maxCop; co++) {
                // algo력 올리기
                if(al + 1 <= maxAlp) dp[al + 1][co] = Math.min(dp[al + 1][co], dp[al][co] + 1);
                // coding력 올리기
                if(co + 1 <= maxCop) dp[al][co + 1] = Math.min(dp[al][co + 1], dp[al][co] + 1);
                
                // 풀 수 있는 문제가 있다면
                for(int[] problem : problems) {
                    if(problem[0] > al || problem[1] > co) continue; // 조건이 안 맞는 경우
                    int minAl = Math.min(maxAlp, al + problem[2]);
                    int minCo = Math.min(maxCop, co + problem[3]);
                    dp[minAl][minCo] = Math.min(dp[minAl][minCo], dp[al][co] + problem[4]);
                }
                
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}