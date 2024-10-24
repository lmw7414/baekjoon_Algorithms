
import java.util.*;

/*
n : 집합 원소의 개수 <최대 1만개>
s : 원소들의 합 <최대 1억>

[문제 해결 프로세스]
1. pq -> maxHeap(내림 차순)
2. 처음 S를 투입<size == 1>
3. n > size 이면 heap에서 큰 수를 뽑아 반절로 나눔
4. n == size가 되었다면 리스트로 변환 후 오름차순
5. 더 이상 나눌 수 없다면 -1
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