import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Round> pq = new PriorityQueue<>((a,b) -> b.dead - a.dead);
        int idx = 0;
        boolean isWin = false;
        for(int soldier = n; ;) {
            isWin = false;
            if(idx >= enemy.length)
                break;
            
            if(enemy[idx] <= soldier) {
                isWin = true;
                pq.add(new Round(idx, enemy[idx]));
                soldier -= enemy[idx];
                idx++;
            } 
            
            if(!isWin) {
                if(k > 0) {
                    if(!pq.isEmpty() && pq.peek().dead > enemy[idx]) {
                        Round rescue = pq.poll();
                        soldier += rescue.dead;
                        k--;
                    } else {
                        k--;
                        idx++;
                    }
                } else
                    break;
            }
            
        }
        
        return idx;
    }
    
    static class Round {
        int round;
        int dead;
    
        public Round(int round, int dead) {
            this.round = round;
            this.dead = dead;
        }
    }
    
}



/*
적이 많은 판을 기준으로 오름차순한다면 해당 라운드가 진행될 수 있는가에 대해 고민해봐야함
판의 숫자 파악
1. 무조건 싸워서 병사를 많이 잃는 판에 사용하자
병사가 다 죽을 때까지 싸움
죽고나서 많이 잃은 판에 병사 부활(한판만 우선 사용) -> 우선순위 큐
부활된 숫자로 다시 싸움
못이기겠으면 과거에 졌던 판에서 부활
*/
