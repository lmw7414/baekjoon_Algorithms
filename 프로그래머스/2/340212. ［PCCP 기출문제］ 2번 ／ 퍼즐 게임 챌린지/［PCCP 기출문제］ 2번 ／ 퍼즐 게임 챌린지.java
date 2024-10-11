import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    public int solution(int[] diffs, int[] times, long limit) {
        int minLevel = 1;
        int maxLevel = Arrays.stream(diffs).max().getAsInt();
        binarySearch(minLevel, maxLevel, diffs, times, limit);
        //System.out.println(calc(diffs, times, 39354));
        return answer;
    }
    
    public void binarySearch(int left, int right, int[] diffs, int[] times, long limit) {
        int mid = (left + right) / 2;  // level
        
        if(left <= right) {
            long time = calc(diffs, times, mid);
            if(time <= limit) { // 여유 시간이 있는 경우 -> 레벨을 낮춰야함
                answer = Math.min(answer, mid);
                // if(answer == mid) {
                //     System.out.println(mid + " " + time);
                // }
                binarySearch(left, mid - 1, diffs, times, limit);
            } else { // 시간을 오버하는 경우 -> 레벨을 높여야 함
                // System.out.println(mid + " " + time + "레벨을 높여야 함");
                binarySearch(mid + 1, right, diffs, times, limit);
            }
        }
    }
    
    public long calc(int[] diffs, int[] times, int level) {
        long time = 0;
        for(int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];
            if(diff <= level) { // 숙련도가 더 높으면
                time += times[i];
            } else {
                int t = diff - level;
                int previous = (i - 1 == -1) ? 0 : times[i-1];
                time += (previous + times[i]) * t + times[i];
            }  
        }
        return time;
    }
}