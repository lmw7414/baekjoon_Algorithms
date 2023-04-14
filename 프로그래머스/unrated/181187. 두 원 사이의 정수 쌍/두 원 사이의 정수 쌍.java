import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long r1dots = 0;
        long r2dots = 0;
        //r1안에 들어있는 점 구하기
        int r = r1;
        int border = r1;
        int count = 0;
        for(long i = 0; i <= r1; i++) {
            if(Math.pow(i, 2) + Math.pow(border, 2) <= Math.pow(r1, 2)) {
                if(Math.pow(i, 2) + Math.pow(border, 2) == Math.pow(r1, 2))
                    count++;
                r1dots += border + 1;
            } else {
                border--;
                i--;
            }
        }
        r1dots -= (2*r1 +1);
        r1dots *=4;
        r1dots += 4 * r1 + 1;

        
        
        
        // r2안에 들어있는 점 구하기
        r = r2;
        border = r2;
        for(long i = 0; i <= r2; i++) {
            if(Math.pow(i, 2) + Math.pow(border, 2) <= Math.pow(r2, 2)) {
                r2dots += border + 1;
            } else {
                border--;
                i--;
            }
        }
        r2dots -= (2*r2 +1);
        r2dots *=4;
        r2dots += 4 * r2 + 1;
        
        count *= 4;
        count -=4;
        
        
        answer = r2dots - r1dots + count;
        return answer;
    }
}