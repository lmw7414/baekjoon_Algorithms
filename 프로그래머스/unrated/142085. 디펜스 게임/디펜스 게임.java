import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0;
        for(int e : enemy) {
            pq.add(e);
            idx++;
            n -= e;
            
            if(n < 0) {
                if(k == 0)
                    return idx-1;
                n += pq.poll();
                k--;
            }
        }
        
        return enemy.length;
    }
    
    
}
