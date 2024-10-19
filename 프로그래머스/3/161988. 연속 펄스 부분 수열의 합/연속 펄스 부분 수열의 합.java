import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        
        int size = sequence.length;
        long[] prefixSum = new long[size + 1];
        
        for(int i = 0; i < size; i++) 
            prefixSum[i + 1] = prefixSum[i] + sequence[i]*((i % 2 == 0) ? 1 : -1);
    
        long maxValue = Long.MIN_VALUE;
        long minValue = Long.MAX_VALUE;
        for (int i = 0; i <= size; i++) {
            maxValue = Math.max(maxValue, prefixSum[i]);
            minValue = Math.min(minValue, prefixSum[i]);
        }
        return maxValue - minValue;
    }
}

