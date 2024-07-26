import java.util.*;

/*
[설명]
- 상담유형은 최대 5개
- 멘토는 최소 1명 ~ 20명. 유형별 최소 한명은 멘토로 있어야 함
- 상담 신청자는 최소 3명 ~ 300명
  - 도착시간은 최대 1000분
  - 상담 최대 시간은 100분
  - 신청자간 도착 시간은 중복되지 않음
  
[문제 해결 프로세스]
1. 모든 유형에 한번씩 인원 투입
2. 투입하지 않았을 때 - 투입했을 때의 차가 큰 유형을 선택
*/

class Solution {
    static PriorityQueue<Integer>[] pq;
    static int[] waiting; // 유형별 대기시간 - original
    static int[] mento;  // 유형별 멘토 인원
    
    public int solution(int k, int n, int[][] reqs) {
        waiting = new int[k + 1];
        mento = new int[k + 1];
        Arrays.fill(mento, 1);
        
        // 유형별 우선순위 큐 설정
        pq = new PriorityQueue[n + 1];
        for(int i = 1; i <= n; i++) pq[i] = new PriorityQueue<Integer>((a1, b1) -> a1 - b1);
        // 멘토를 투입하지 않았을 때 대기 시간
        waiting = calc(k, 0, reqs);
        
        int[] diff;
        for(int curMento = k; curMento < n; curMento++) {
            diff = new int[k + 1];
            for(int i = 1; i<= k; i++) {
                int[] temp = calc(k, i, reqs);
                diff[i] = waiting[i] - temp[i];
            }
            mento[findMax(k, diff)]++;
            waiting = calc(k, 0, reqs); // 최신화
        }
        
        int answer = 0;
        for(int i = 1; i<= k; i++) {
            answer += waiting[i];
        }
        return answer;
    }
    
    public int findMax(int k, int[] arr) {
        int idx = 1;
        int max = 0;
        for(int i = 1; i <= k; i++) {
            if(max < arr[i]) {
                max = arr[i];
                idx = i;
            }
        }
        return idx;
    }
    
    public void initPQ(int k, int[] mento) {
        for(int i = 1; i<= k; i++) {
            pq[i].clear();
            for(int j = 1; j <= mento[i]; j++)
                pq[i].add(0);
        }
    }
    
    public void printArr(int k, int[] arr) {
        for(int i = 1; i <= k; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    /**
    * k : 유형 개수
    * type : 멘토를 투입할 유형
    * return : 대기시간
    */
    public int[] calc(int k, int type, int[][] reqs) {
        // 추가했을 때 대기시간
        int[] tempWaiting = new int[k + 1];
        int[] tempMento = new int[k + 1];
        for(int i = 1; i <= k; i++) {
            tempMento[i] = mento[i];
        }
        tempMento[type]++;

        initPQ(k, tempMento);
        for(int[] next : reqs) {
            // next : 시작시간, 상담시간, 상담 타입
            int endTime = pq[next[2]].poll();
            if(endTime > next[0]) { // 대기시간이 있는 경우
                tempWaiting[next[2]] += endTime - next[0];  // 유형별 상담의 대기시간 증가
                pq[next[2]].add(endTime + next[1]);
            } else {
                pq[next[2]].add(next[0] + next[1]);         
            }   
        }
        return tempWaiting;
    }
    
}