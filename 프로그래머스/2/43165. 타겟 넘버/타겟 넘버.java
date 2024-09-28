class Solution {
    static int answer = 0;
    static int[] arr;
    static int N;
    public int solution(int[] numbers, int target) {
        arr = numbers;
        N = numbers.length;
        calc(0, 0, target);
        return answer;
    }
    
    public void calc(int depth, int result, int target) {
        if(depth == N) {
            if(result == target) answer++;
            return;
        }
        calc(depth + 1, result + arr[depth], target);
        calc(depth + 1, result - arr[depth], target);
    }
}