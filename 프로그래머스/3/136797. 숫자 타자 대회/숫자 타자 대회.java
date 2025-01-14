import java.util.*;

class Solution {
    static Map<Integer, Point> hm = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0, 1, -1, 1, -1};
    static int[] dy = {0, 0, -1, 1, 1, -1, -1, 1};
    static int[][] cost = new int[10][10];
    static int[][][] dp;
    static int size;
    public int solution(String numbers) {
        int answer = 0;
        init();
        size = numbers.length();
        dp = new int[numbers.length() + 1][10][10];
        for(int i = 0; i <= numbers.length(); i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k < 10; k++){
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        return calc(numbers, 0, 4, 6);
    }
    
    public int calc(String number, int idx, int left, int right) {
        if(idx == size) return 0;
        if(dp[idx][left][right] != Integer.MAX_VALUE) return dp[idx][left][right];
        int key = number.charAt(idx) - '0';
        
        int val = Integer.MAX_VALUE;
        // 이미 손가락이 해당 숫자에 올려져 있다면 그 손으로 눌러야 함
        if(right != key) {// 왼쪽 손으로 누를 경우
            val = Math.min(calc(number, idx + 1, key, right) + cost[left][key], val);
        }
        if(left != key) { // 오른쪽 손으로 누를 경우
            val = Math.min(calc(number, idx + 1, left, key) + cost[key][right], val);
        }
        return dp[idx][left][right] = val;
    }
    
    
    public int getCost(int sx, int sy, int ex, int ey) {
        if((sx == ex && sy != ey) || (sx != ex && sy == ey)) return (Math.abs(sx - ex) + Math.abs(sy - ey)) * 2;
        else if(Math.abs(sx - ex) == Math.abs(sy - ey)) return Math.abs(sx - ex) * 3;
        
        int[][] cost = new int[4][3];
        for(int i = 0; i < 4; i++) Arrays.fill(cost[i], Integer.MAX_VALUE);
        cost[sx][sy] = 0;
        PriorityQueue<Point> queue = new PriorityQueue<>((a1, b1) -> cost[a1.x][a1.y] - cost[b1.x][b1.y]);
        queue.add(new Point(sx, sy));
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            if(cur.x == ex && cur.y == ey) return cost[ex][ey];
            // 상하좌우, 대각선
            for(int d = 0; d < 8; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                int val = (d < 4) ? 2:3;
                val += cost[cur.x][cur.y];
                if(OOB(nx, ny)) continue;
                if(cost[nx][ny] <= val) continue;
                
                cost[nx][ny] = val;
                queue.add(new Point(nx, ny));
            }
        }
        return -1;
    }
    
    public boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= 4 || y >= 3;
    }
    
    public void init() {
        char[][] arr = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'},
            {'*', '0', '#'}
        };
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j< 3; j++) {
                hm.put(arr[i][j] - '0', new Point(i, j));
            }
        }
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(cost[i][j] != 0) continue;
                if(i == j) {
                    cost[i][j] = 1;
                    continue;
                }
                Point f = hm.get(i);
                Point s = hm.get(j);
                int val = getCost(f.x, f.y, s.x, s.y);
                //System.out.printf("%d %d -> %d\n", i, j, val);
                cost[i][j] = cost[j][i] = val;
            }
        }
    }
    
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}