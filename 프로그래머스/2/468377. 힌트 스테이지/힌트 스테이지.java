import java.util.*;
/**
1. n개의 스테이지를 순서대로 해결해야 함
2. 하나의 스테이지에서 사용할 수 있는 힌트권의 최대 개수는 0 ~ n-1개
*/
class Solution {
    static int ANSWER = Integer.MAX_VALUE;
    static int N;
    public int solution(int[][] cost, int[][] hint) {
        N = cost.length;
        
        DFS(0, 0, cost, hint, new int[N + 1]);
        return ANSWER;
    }
    
    public void DFS(int depth, int curCost, int[][] cost, int[][] hint, int[] bundle) {
        if(ANSWER <= curCost) return;
        if(depth == N) {
            ANSWER = Math.min(curCost, ANSWER);
            return;
        }
        
        // 현재 스테이지 비용
        //쿠폰 최대 개수
        int available = Math.min(bundle[depth + 1], N - 1);
        int val = cost[depth][available];
        
        // 힌트를 안산다.
        DFS(depth + 1, curCost + val, cost, hint, bundle);
        
        // 힌트를 산다
        if(depth < N - 1) {
            int[] h = hint[depth];
            for(int i = 1; i < hint[depth].length; i++) {
                bundle[h[i]]++;
            }
            DFS(depth + 1, curCost + val + h[0], cost, hint, bundle);
            for(int i = 1; i < hint[depth].length; i++) {
                bundle[h[i]]--;
            }
        }
    }
}