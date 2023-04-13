import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0}; //상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited;
    static int[][] nBoard;
    static int sizeX;
    static int sizeY;
    static Point R;
    static Point G;
    static int min = Integer.MAX_VALUE;
    public int solution(String[] board) {
        int answer = 0;
        sizeX = board.length;
        sizeY = board[0].length();
        visited = new int[sizeX][sizeY];
        for(int i = 0; i< sizeX; i++) {
            Arrays.fill(visited[i], -1);
        }
        
        nBoard = new int[sizeX][sizeY];
        
        for(int i = 0; i < sizeX; i++) {
            for(int j = 0; j < sizeY; j++) {
                if(board[i].charAt(j) == 'D') {
                    nBoard[i][j] = -1;
                } else if(board[i].charAt(j) == 'R') {
                    nBoard[i][j] = 1;
                    R = new Point(i, j);
                } else if(board[i].charAt(j) == 'G') {
                    nBoard[i][j] = 2;
                    G = new Point(i, j);
                }
            }
        }
        dfs();
        
        return visited[G.x][G.y];
    }
    
    public static void dfs() {
        Stack<Point> stack = new Stack();
        stack.push(R);
        visited[R.x][R.y] = 0;
        while(!stack.isEmpty()) {
            Point cur = stack.pop();
            
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= sizeX || ny >= sizeY || nBoard[nx][ny] == -1)
                    continue;
                Point next =  move(i, cur);
                if(visited[next.x][next.y] == -1) {
                    visited[next.x][next.y] = visited[cur.x][cur.y] + 1;
                    stack.push(next);
                }
                else if(visited[next.x][next.y] > visited[cur.x][cur.y] + 1) {
                    visited[next.x][next.y] = visited[cur.x][cur.y] + 1;
                    stack.push(next);
                }
            }
        }
    }
    
    public static Point move(int dir, Point cur) {   //dir 0~3 상하좌우
        Point next = new Point(cur.x + dx[dir], cur.y + dy[dir]);
        if(next.x < 0 || next.y < 0 || next.x >= sizeX || next.y >= sizeY)
            return cur;
        if(nBoard[next.x][next.y] == -1)
            return cur;
        else
            return move(dir, next);
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

