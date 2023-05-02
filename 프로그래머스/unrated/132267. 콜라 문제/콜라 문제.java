class Solution {
    public int solution(int a, int b, int n) {
        return result(a, b, n, 0);
    }
    
    public int result(int a, int b, int bottle, int answer) {
        if(bottle < a)
            return answer;
        int newBottle = bottle/a * b;
        answer += newBottle;
        bottle = newBottle + bottle % a;
        return result(a, b, bottle, answer);
    }
}