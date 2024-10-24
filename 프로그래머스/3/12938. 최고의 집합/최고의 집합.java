
import java.util.*;

/*
n : 집합 원소의 개수 <최대 1만개>
s : 원소들의 합 <최대 1억>
*/

class Solution {
    
    public int[] solution(int n, int s) {
        int[] answer = {};
        if(s / n < 1) return new int[] {-1};        
        int num = s / n;
        answer = new int[n];
        Arrays.fill(answer, num);
        
        int cnt = num * n;
        for(int i = n - 1; i >= 0; i--) {
            if(cnt == s) break;
            answer[i]++; 
            cnt++;
            
        }
        return answer;
    }
}