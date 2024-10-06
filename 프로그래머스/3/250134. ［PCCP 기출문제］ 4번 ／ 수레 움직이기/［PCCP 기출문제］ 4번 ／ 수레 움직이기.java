class Solution {
    static int N, M, answer;
    static int[][] arr;
    static boolean[][] visitR, visitB;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Point red, blue;
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        arr = maze;
        answer = Integer.MAX_VALUE;
        visitR = new boolean[N][M];
        visitB = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(maze[i][j] == 1) {
                    visitR[i][j] = true;
                    red = new Point(i, j);
                } else if(maze[i][j] == 2) {
                    visitB[i][j] = true;
                    blue = new Point(i, j);
                }
            }
        }
        
        DFS(red, blue, 0);
        if(answer == Integer.MAX_VALUE) return 0;
        else return answer;
    }
    
    public void DFS(Point r, Point b, int result) {
        if(isRedEnd(r) && isBlueEnd(b)) {
            answer = Math.min(answer, result);
            return;
        }
        Point nRed, nBlue;
        for(int d1 = 0; d1 < 4; d1++) {
            nRed = moveR(r, d1);
            if(nRed == null) continue;
            //if(nRed.x == b.x && nRed.y == b.y) continue; // 같은 위치
            visitR[nRed.x][nRed.y] = true;
            for(int d2 = 0; d2 < 4; d2++) {
                nBlue = moveB(b, d2);
                if(nBlue == null) continue;
                if(nRed.x == nBlue.x && nRed.y == nBlue.y) continue; // 같은 위치
                if(nRed.x == b.x && nRed.y == b.y && nBlue.x == r.x && nBlue.y == r.y) continue;
                visitB[nBlue.x][nBlue.y] = true;
                DFS(nRed, nBlue, result + 1);
                visitB[nBlue.x][nBlue.y] = false;
            }
            visitR[nRed.x][nRed.y] = false;
        }
    }
    
    public Point moveR(Point r, int d) {
        if(isRedEnd(r)) return r;
        int nx = r.x + dx[d];
        int ny = r.y + dy[d];
        if(OOB(nx, ny) || visitR[nx][ny] || arr[nx][ny] == 5) return null;
        return new Point(nx, ny);
    }
    
    public Point moveB(Point b, int d) {
        if(isBlueEnd(b)) return b;
        int nx = b.x + dx[d];
        int ny = b.y + dy[d];
        if(OOB(nx, ny) || visitB[nx][ny] || arr[nx][ny] == 5) return null;
        return new Point(nx, ny);
    }
    
    public boolean isRedEnd(Point r) {
        return arr[r.x][r.y] == 3;
    }
    public boolean isBlueEnd(Point b) {
        return arr[b.x][b.y] == 4;
    }
    
    // true이면 경계 밖
    public boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
    
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}