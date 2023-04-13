import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int tool = picks[0] + picks[1] + picks[2];
        PriorityQueue<MS> pq = new PriorityQueue<>(
            (o1, o2) ->  o2.dur - o1.dur
        );
                
        for(int i = 0; i< minerals.length;) {
            MS ms = new MS();
            for(int j = 0; j < 5; j++) {
                if(i == minerals.length)
                    break;
                
                if(minerals[i].equals("diamond")) {
                    ms.diamond++;
                    ms.dur += 25;
                } else if(minerals[i].equals("iron")) {
                    ms.iron++;
                    ms.dur += 5;
                } else if(minerals[i].equals("stone")) {
                    ms.stone++;
                    ms.dur += 1;
                }
                i++;
            }
            
            if(i <= minerals.length)
                pq.add(ms);
            tool--;
            if(tool == 0)
                break;
        }
        while(!pq.isEmpty()) {
            MS ms = pq.poll();
            
            if(picks[0] != 0) {
                answer += ms.diamond + ms.iron + ms.stone;
                picks[0]--;
            } else if(picks[1] != 0) {
                answer += (ms.diamond * 5) + ms.iron + ms.stone;
                picks[1]--;
            } else if(picks[2] != 0) {
                answer += (ms.diamond * 25) + (ms.iron * 5) + ms.stone;
                picks[2]--;
            } else {  // 모두 다 0이면 종료
                break;
            }
        }
        
        
        return answer;
    }
    
    class MS {
        int diamond = 0;
        int iron = 0;
        int stone = 0;
        int dur = 0;
        
        public int total() {
            return diamond + iron + stone;
        }
    }
    
}
