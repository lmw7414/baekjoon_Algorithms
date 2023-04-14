import java.util.*;

class Solution {
    //m -가로 x     n | 세로 y
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int idx = 0;
        for(int[] ball : balls) {
            answer[idx++] = minDistance(m, n, new Point(startX, startY), new Point(ball[0], ball[1]));
        }
        
        return answer;
    }
    
    public int minDistance(int m, int n, Point p1, Point p2) {
        int min = Integer.MAX_VALUE;
            
        // 상 대칭 -> p2의 x는 그대로, p2의 y는  p2.y += 2* (n - p2.y)
        if(!(p1.x == p2.x && p2.y > p1.y)) {
            int d1 = (int)Math.pow(p2.x - p1.x, 2) + (int)Math.pow((p2.y + (2 * (n - p2.y))) - p1.y, 2);
            min = Math.min(min, d1);
        }
        // 하 대칭 -> p2의 x는 그대로  p2의 y는  p2.y = -p2.y
        if(!(p1.x == p2.x && p2.y < p1.y)) {
            int d2 = (int)Math.pow(p2.x - p1.x, 2) + (int)Math.pow(-p2.y - p1.y, 2);
            min = Math.min(min, d2);
        }
        // 좌 대칭 -> p2.x = -p2.x, p2.y는 그대로
        if(!(p1.y == p2.y && p1.x > p2.x)) {
            int d3 = (int)Math.pow(-p2.x - p1.x, 2) + (int)Math.pow(p2.y - p1.y, 2);
            min = Math.min(min, d3);
        }
        // 우 대칭 -> p2.x += 2 * (m - p2.x) p2.y는 그대로
        if(!(p1.y == p2.y && p1.x < p2.x)) {
            int d4 = (int)Math.pow((p2.x + (2 * (m - p2.x))) - p1.x, 2) + (int)Math.pow(p2.y - p1.y, 2);
            min = Math.min(min, d4);
        }                                                  
        return min;
                                                                         
    }
    
    static class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
