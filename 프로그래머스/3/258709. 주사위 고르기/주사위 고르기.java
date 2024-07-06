import java.util.*;
/*
[문제 설명]
1. n개의 주사위를 가지고 승부
2. A가 n/2 개의 주사위를 가져가면 B가 남은 n/2개의 주사위를 가져감
3. 각각 가져간 주사위를 모두 굴린 뒤, 나온 수들을 모두 합해 점수를 계산.
4. 점수가 더 큰 쪽이 승리. 점수가 같다면 무승부
5. A는 자신이 승리할 확률이 가장 높아지도록 주사위를 가져가려함
[조건]
최대 주사위 개수 10개
[문제 해결 프로세스]
1. n/2개 만큼 주사위 선택
2. 10개 중 5개를 고르는 경우의 수 -> 252개
3. 모든 경우의 수 계산 -> 5개 기준 7776개
4. A와 B의 승률 비교
*/
class Solution {
    static int size;
    static int result = 0;
    static int[] answer;
    static int[][] origin;
    static int[] aDice, bDice; // a, b가 가지고 있는 주사위
    public int[] solution(int[][] dice) {
        origin = dice;
        size = dice.length;
        aDice = new int[size/2];
        bDice = new int[size/2];
        answer = new int[size/2];
        permutation(0, 1);
        return answer;
    }
    
    
    public static void permutation(int depth, int idx) {
        if(depth == size/2) {
            //bDice 초기화
            int bIdx = 0;
            boolean[] check = new boolean[size + 1];
            for(int i : aDice) check[i] = true;
            for(int i = 1; i<= size; i++) {
                if(!check[i]) bDice[bIdx++] = i;
            }
            
            int res = calc();
            if(res > result) {
                result = res;
                for(int i = 0; i < aDice.length; i++) answer[i] = aDice[i];
            }
            return;
        }
        for(int i = idx; i <= size; i++ ) {
            aDice[depth] = i;
            permutation(depth + 1, i + 1);
        }
    }
    // A의 주사위에서 나올 수 있는 모든 합 구하기
    public static void getNumberOfCasesA(int depth, int sum, List<Integer> arr) {
        if(depth == size / 2) {
            arr.add(sum);
            return;
        }
        for(int i = 0; i < 6; i++) {
            getNumberOfCasesA(depth+1, sum + origin[aDice[depth] - 1][i], arr);
        }
        
    }
    
    // B의 주사위에서 나올 수 있는 모든 합 구하기
    public static void getNumberOfCasesB(int depth, int sum, List<Integer> arr) {
        if(depth == size / 2) {
            arr.add(sum);
            return;
        }
        for(int i = 0; i < 6; i++) {
            getNumberOfCasesB(depth+1, sum + origin[bDice[depth] - 1][i], arr);
        }
        
    }
    
    public static int calc() {
        List<Integer> aCases = new ArrayList<>();
        List<Integer> bCases = new ArrayList<>();
        getNumberOfCasesA(0, 0, aCases);
        getNumberOfCasesB(0, 0, bCases);
        Collections.sort(aCases);
        Collections.sort(bCases);
        int a = 0;
        for(int aCase : aCases) {
            a += binarySearch(bCases, 0, bCases.size(), aCase);
        }
        return a;
    }
    
    public static int binarySearch(List<Integer> arr, int left, int right, int key) {
        int mid = (left + right) / 2;
        if(left < right) {
            if(arr.get(mid) < key) return binarySearch(arr, mid + 1, right, key);
            else return binarySearch(arr, left, mid, key);
        }
        return left;
    }
}