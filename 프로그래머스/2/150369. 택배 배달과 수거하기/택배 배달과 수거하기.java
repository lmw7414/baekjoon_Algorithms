import java.util.*;

/*
[문제 설명]
1. n개의 집에 택배 배달
2. 배달할 물건은 모두 크기가 같은 재활용 택배 상자
3. 배달을 다니면서 빈 재활용 택배 상자를 수거
4. i번째 집은 물류창고에서 i만큼 떨어져 있음
5. 트럭에는 최대 cap개를 실을 수 있음.
6. 트럭은 배달할 재활용 택배 상자들을 실어서 집에 배달하고, 빈 재활용 택배 상자를 수거
7. 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동거리
[조건]
- 최대 실을 수 있는 택배의 양은 50개
- 최대 거리의 집은 100000
[문제 해결 프로세스]
- 마지막 집까지 배달 및 수거를 마쳐야 함
- 특정 구간 ~ 배달[n] 까지 cap이 최대가 되도록 하고
- cap를 최대로 쓰는 조건
  - 마지막 집이 cap보다 배달할 물건이 많은 경우
    - 어차피 여러번 왔다가야함
    - 최대로 채우고 
*/
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        long d = 0;
        long p = 0;
        
        for(int i = n - 1; i >= 0; i--) {
            int cnt = 0;
            d += deliveries[i];
            p += pickups[i];
            
            while(d > 0 || p > 0) {
                d -= cap;
                p -= cap;
                cnt++;
            }
            answer += ((i+1) * 2) * cnt;
            
        }
        
        return answer;
    }
}