import java.util.*;

class Solution {
    public int solution(int n, long l, long r) {
        return cal(n, r) - cal(n, l - 1);
    }
    
    public static int cal(int depth, long r) {
        int answer = 0;
        int[] arr =new int[6];
        long border = 0;
        while(depth != 0) {
            border = (long)Math.pow(5, depth - 1);
            int sectorR = (int)(r / border) + 1;
            // r 범위 제외, 나머지 더해줌

            for(int i = 1; i < arr.length; i++)
                arr[i] = (int)Math.pow(4, depth - 1);
            arr[3] = 0;
            for(int i = 1; i< sectorR; i++)
                answer += arr[i];
            // sectorR이 3인 경우는 0만 있는 부분
            if(sectorR == 3)
                break;
            depth--;
            r -= (sectorR - 1) * border;
        }
        return answer;
    }
    
}

/*
1. 특정 위치가 담긴 값의 범위만 제외하고 나머지 결과는 더해줌
2. 특정 위치가 담긴 범위를 분할(depth를 낮춤)
3. 반복
*/