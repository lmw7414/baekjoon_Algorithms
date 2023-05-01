import java.util.*;

class Solution {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        int idx = 0;
        for(int sc : score)
            answer[idx++] = presentationScore(k, sc);
        
        return answer;
    }
    
    public int presentationScore(int k, int score) {
        if(pq.size() < k) {
            pq.add(score);
        } else {
            if(score > pq.peek()) {
                pq.poll();
                pq.add(score);
            }
        }
        return pq.peek();
    }
}