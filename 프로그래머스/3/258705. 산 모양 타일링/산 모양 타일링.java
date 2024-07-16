/*
[문제 설명]
1. 한 변의 길이가 1인 정삼각형 2n+1 개를 이어붙인다.
2. 윗변의 길이가 n, 아랫변의 길이가 n+1인 사다리꼴을 만든다.
*/
class Solution {
    public int solution(int n, int[] tops) {
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        a[0] = 0;
        b[0] = 1;
        
        for(int i = 1; i<= n; i++) {
            a[i] = (a[i - 1] + b[i - 1]) % 10007;
            if(tops[i - 1] == 1) {
                b[i] = (2 * a[i - 1] + 3 * b[i - 1]) % 10007;
            } else {
                b[i] = (a[i - 1] + 2 * b[i - 1]) % 10007;
            }
        }
        return (a[n] + b[n]) % 10007;
    }
}