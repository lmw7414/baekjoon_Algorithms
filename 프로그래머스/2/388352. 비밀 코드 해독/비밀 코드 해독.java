import java.util.*;

/*
[입력]
- n 은 1부터 최대 30까지
- q 주어진 배열의 개수는 최대 10개
- 
서로 다른 정수 5개가 오름차순으로 정렬
*/

class Solution {
    static List<Box> codes = new ArrayList<>();
    static int[] arr;
    static int[] answer = new int[5];
    static int cnt = 0;
    public int solution(int n, int[][] q, int[] ans) {
        init(q, ans);
        arr = new int[n];
        for(int i = 1; i <= n; i++) arr[i - 1] = i;
        DFS(0, 0);
        return cnt;
    }
    
    public void DFS(int depth, int idx) {
        if(depth == 5) {
            if(isOk()) {
                cnt++;
            }
            return;
        }
        
        for(int i = idx; i < arr.length; i++) {
            answer[depth] = arr[i];
            DFS(depth + 1, i + 1);
        }
    }
    
    public static boolean isOk() {
        for(Box b : codes) {
            int cnt = 0;
            for(int target : answer) {
                for(int p : b.arr) {
                    if(p == target) {
                        cnt++;
                        break;
                    }
                }
            }
            if(b.correct != cnt) return false;
        }
        return true;
    }
    
    public void init(int[][] q, int[] ans) {
        for(int i = 0; i < q.length; i++) {
            codes.add(new Box(q[i], ans[i]));
        }
    }
    
    static class Box {
        int[] arr;
        int correct;
        
        public Box(int[] arr, int correct) {
            this.arr = arr;
            this.correct = correct;
        }
    }
}