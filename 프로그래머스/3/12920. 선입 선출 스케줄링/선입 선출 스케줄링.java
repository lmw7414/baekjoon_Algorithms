import java.util.*;

/*
[문제 해결 프로세스]
1. 시간을 알면 완료된 작업의 개수를 알 수 있음
2. 마지막 작업이 포함된 시간초 파악
3. lower bound로 해당 시간에서 첫 작업을 파악 후 코어를 작업을 돌며 마지막 작업 파악
*/
class Solution {
    public int solution(int n, int[] cores) {
        int left = 1;
        int right = 10000 * n; // 코어당 처리하는 시간 * 작업의 개수
        
        while(left < right) {
            int mid = (left + right) / 2;
            int cnt = cores.length; // 0초에 처리가능한 작업 수
            for(int core : cores) cnt += mid / core;
            
            if(cnt < n) { // 작업의 개수가 적음 -> 시간 초를 늘려야 함
                left = mid + 1;
            } else {
                right = mid;
            } 
        }
        
        int time = left;
        int cnt = cores.length;
        for(int core : cores) cnt += (time - 1)/ core;
        int answer = -1;
        for(int i = 0; i < cores.length; i++) {
            if(time % cores[i] == 0) {
                if(++cnt == n) {
                    answer = i + 1;
                    break;
                }
            }
        }
        return answer;
    }
    
    
}