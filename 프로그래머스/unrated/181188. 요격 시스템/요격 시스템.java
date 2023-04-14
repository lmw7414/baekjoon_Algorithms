import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        PriorityQueue<Mi> pq = new PriorityQueue<>(
            (o1, o2) -> {
                if(o1.start == o2.start)
                    return o1.gap - o2.gap;
                return o1.start - o2.start;
            });
        for(int[] target : targets) {
            Mi mi = new Mi(target[0], target[1], target[1] - target[0]);
            pq.add(mi);
        }
        while(!pq.isEmpty()) {
            Mi mi = pq.poll();
            int shortedEnd = mi.end;
            answer++;
            
            while(pq.peek() != null) {
                Mi next = pq.peek();
                if(next.start == mi.start)
                    pq.poll();
                else if(next.start > mi.start  && next.start < shortedEnd) 
                    pq.poll();
                else
                    break;
                if(shortedEnd > next.end)
                    shortedEnd = next.end;
            }
            
        }
        
        return answer;
    }
    
    static class Mi {
        int start;
        int end;
        int gap;
        Mi (int start, int end, int gap) {
            this.start = start;
            this.end = end;
            this.gap = gap;
        }
    }
}