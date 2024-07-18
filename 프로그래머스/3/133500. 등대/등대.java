/*
[문제 설명]
1. 1~n까지 서로 다른 번호가 매겨진 등대가 있다.
2. 등대와 등대 사이를 오가는 뱃길이 n-1개 존재
3. 어느 등대에서 출발해도 다른 모드 등대까지 이동할 수 있음
4. 전력을 아끼기 위해 이 중 몇개의 등대만 켜두려고 함
5. 전력을 아낄 수 있도록 등대의 개수를 최소화 해라
[제한 사항]
2 ≤ n ≤ 100,000

[문제 해결 프로세스]
1. 한 뱃길의 양쪽 끝 등대 중 적어도 하나는 켜져 있어야 함.
2. degree가 1인 등대를 높은 우선순위로 큐에 넣음
3. 다음 등대는 무조건 불을 킴
4. 불 킨 등대의 모든 경로 제거
*/
import java.util.*;
class Solution {
    static int answer = 0;
    static Set<Integer>[] lhList;
    static int[] degree;
    public int solution(int n, int[][] lighthouse) {
        degree = new int[n + 1];
        lhList = new Set[n + 1];
        for(int i = 1; i<= n; i++) {
            lhList[i] = new HashSet<>();
        }
        for(int[] line : lighthouse) {
            lhList[line[0]].add(line[1]);
            lhList[line[1]].add(line[0]);
            degree[line[0]]++;
            degree[line[1]]++;
        }
        calc(n);
        return answer;
        
    }
    
    public void calc(int n) {
        boolean flag = false;
        while(!flag) {
            flag = true;
            for(int i = 1; i <= n; i++) {
                if(degree[i] == 1) {
                    Iterator<Integer> it = lhList[i].iterator();
                    flag = false;
                    deleteRoute(it.next());
                }
            }
        }
        
    }
    
    public void deleteRoute(int num) {
        for(int next : lhList[num]) {
            lhList[next].remove(num);
            degree[next]--;
        }
        lhList[num] = null;
        degree[num] = 0;
        answer++;
    }
    
    
    
    
}