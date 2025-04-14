import java.util.*;

class Solution {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static char[][] arr;
    static int answer = 0;
    public int solution(String[] storage, String[] requests) {
        initArr(storage);
        
        for(String request : requests) {
            if(request.length() == 1) {
                run1(request);
            }
            else {
                run2(request);
            }
        }
        
        // for(int i = 0; i < N + 2; i++) {
        //     for(int j = 0; j < M + 2; j++) {
        //         if(arr[i][j] == '0') System.out.print("  ");
        //         else System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return answer;
    }
    
    //한글자|지게차를 사용해 출고 요청이 들어온 순간 접근 가능한 컨테이너를 꺼냅니다.
    public void run1(String request) {
        boolean[][] visit = new boolean[N + 2][M + 2];
        Queue<Point> queue = new ArrayDeque<>();
        Queue<Point> target = new ArrayDeque<>();
        queue.add(new Point(0 , 0));
        visit[0][0] = true;
        
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            
            for(int d = 0;  d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if(OOB(nx, ny)) continue;
                if(visit[nx][ny]) continue;
                visit[nx][ny] = true;
                if(arr[nx][ny] == '0') queue.add(new Point(nx, ny));
                else {
                    if(arr[nx][ny] == request.charAt(0)) target.add(new Point(nx, ny));
                }
                
            }
        }
        answer -= target.size();
        while(!target.isEmpty()) {
            Point cur = target.poll();
            arr[cur.x][cur.y] = '0';
        }
    }
    //크레인을 사용해 요청된 종류의 모든 컨테이너를 꺼냅니다.
    public void run2(String request) {
        for(int i = 1; i < N + 1; i++) {
            for(int j = 1; j < M + 1; j++) {
                if(arr[i][j] == request.charAt(0) || arr[i][j] == request.charAt(1)) {
                    arr[i][j] = '0';
                    answer--;
                }
            }
        }
    }
    
    public boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x > N + 1 || y > M + 1;
    }
    
    public void initArr(String[] storage) {
        N = storage.length;
        M = storage[0].length();
        answer = N * M;
        arr= new char[N + 2][M + 2];
        for(int i = 0; i < N + 2; i++) Arrays.fill(arr[i], '0');
        
        for(int i = 1; i < N + 1; i++) {
            for(int j = 1; j < M + 1; j++) {
                arr[i][j] = storage[i - 1].charAt(j - 1);
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


