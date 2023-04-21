import java.util.*;

class Solution {
    public long solution(int[] sequence) {

    int n = sequence.length;
    long[] psum = new long[n+1];
    
    for (int i = 0; i < n; i++) {
        psum[i+1] = psum[i] + sequence[i]*((i % 2 == 0) ? 1 : -1);
    }

    long max = Long.MIN_VALUE;
    long min = Long.MAX_VALUE;
    for (int i = 0; i <= n; i++) {
        max = Math.max(max, psum[i]);
        min = Math.min(min, psum[i]);
    }

    return max - min;
    }
}
/*
2  3 -6  1 3 -1 2 4
1 -1  1 -1 1 -1 1 -1

2 -3 -6 -1 3 1 2 -4
2 -1 -7 -8 -5 -4 -2 -6
최대 2
최소 -8

 2 3 -6 1  3 -1  2 4
-1 1 -1 1 -1  1 -1 1

-2 3 6 1 -3 -1 -2 4
-2 1 7 8  5  4  2 6
최대 8
최소 -2

-3 -6 -1 10
3 -6 1 10
3 -3 -2 8

*/
