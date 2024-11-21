import java.util.*;

/**
[입력]
n : 화살의 개수(최대 10발)
info : 10 -> 0 까지의 어피치가 맞힌 점수별 활 개수

[출력]
이길 수 있는 경우 : info와 동일하게 11자리
  - 이길 수 있는 경우가 여러가지 인 경우 가장 낮은 점수를 더 많이 맞힌 경우로
비기거나 이길 수 없는 경우 : -1
*/

class Solution {
    static int[] lion = new int[11], apeach = new int[11], answer = new int[11];
    static int diff = 0;
    static int minScore = 11;
    static int N;
    public int[] solution(int n, int[] info) {
        init(n, info);
        DFS(0, 0);
        if(diff == 0) return new int[] {-1};
        return answer;
    }
    
    // 10점 부터 -> 0점 순으로 가야 함
    public void DFS(int depth, int idx) {
        if(depth > N) return;
        if(depth == N) {
            int score = calc();  // 점수 격차 계산
            
            if(diff <= score) { // 점수 비교(같거나 크면 정답으로 지정)
                // 가장 낮은 점수 체크
                int min = 0;
                for(int i = 10; i >= 0; i--) {
                    if(lion[i] != 0) {
                        min = 10 - i;
                        break;
                    }
                }
                
                if(diff < score) { // 점수 격차가 큰 경우
                    
                    minScore = min;
                    for(int i = 0; i <= 10; i++) answer[i] = lion[i];
                    diff = score;
                } else { // 최대 점수는 같으나 최소 점수는 더 작은 경우
                    
                    if(min < minScore) {
                        minScore = min;
                        for(int i = 0; i <= 10; i++) answer[i] = lion[i];
                        diff = score;
                    }
                }
                return;
            }
        }
        
        for(int i = idx; i <= 10; i++) {
            if(i < 10) {
                int winCase = apeach[i] + 1;
                // 해당 점수를 쏘거나
                lion[i] += winCase;
                DFS(depth + winCase, i + 1);
                // 해당 점수를 안 쏘거나
                lion[i] -= winCase;
                DFS(depth, i + 1);
            } else { // 화살 소진
                int surplus = N - depth;
                lion[i] = surplus;
                DFS(N, i + 1);
                lion[i] -= surplus;
            }
            
        }
    }
    
    public int calc() {
        int l = 0;
        int p = 0;
        for(int i = 0; i <= 10; i++) {
            if(lion[i] == 0 && apeach[i] == 0) continue;
            if(lion[i] > apeach[i]) l += 10 - i;
            else p += 10 - i;
        }
        return l - p;
    }
    
    public void init(int n, int[] info) {
        for(int i = 0; i <= 10; i++) apeach[i] = info[i];
        N = n;
    }
}











