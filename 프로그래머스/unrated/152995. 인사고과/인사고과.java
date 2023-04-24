import java.util.*;

class Solution {
    
    public int solution(int[][] scores) {
        int[] w = scores[0];
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        
        int rank = 1;
        int maxScore = 0;
        int wScore = w[0] + w[1];
        
        for(int[] score : scores) {
            if(score[1] >= maxScore) {
                maxScore = Math.max(maxScore, score[1]);
                if(score[0] + score[1] > wScore)
                    rank++;
            } else {
                if(score.equals(w))
                    return -1;
            }
        }
        
        return rank;
    }
}