import java.util.*;

class Solution {
    /*
    x의 한점(x, 0)에서 최대로 도달할 수 있는 y 구하기
    거리로 접근
    x**2 + y**2 = d**2
    */
    public long solution(int k, int d) {
        long answer = 0;
        for(int a = 0; a <= d; a++) {
            long x = a * k;
            if(x > d)
                break;
            long maxY = findY(x, d);
            answer += maxY / k + 1;
        }
        return answer;
    }
    
    public static long findY(long x, int d) {
        long y = (long)Math.sqrt((long)Math.pow(d,2) - (long)Math.pow(x, 2));
        //System.out.println(y);
        return y;
    }
}

// a = 0, 1, 2, 3, 4, 5, 6
// b = 0, 1, 2, 3, 4, 5, 6