import java.util.*;
class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[][] board = new int[park.length][park[0].length()];
        int sx = 0;
        int sy = 0;
        int[] start = new int[2];
        for(int i = 0; i < park.length; i++) {
            for(int j = 0; j< park[i].length(); j++) {
                if(park[i].charAt(j) == 'X') {
                    board[i][j] = -1;
                } else if(park[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        for(int i = 0; i< routes.length; i++) {
            char c = routes[i].charAt(0);
            int m = routes[i].charAt(2) - '0';
            start = move(board, sx, sy, c, m);
            sx = start[0];
            sy = start[1];
        }

        return new int[] {sx, sy};
    }
    public static int[] move(int[][] board, int sx, int sy, char c, int inx) {
        int nx = sx;
        int ny = sy;

        if(c == 'N') {
            nx = sx - inx;
        } else if (c == 'S') {
            nx = sx + inx;
        } else if (c == 'E') {
            ny = sy + inx;
        } else if (c == 'W'){
            ny = sy -inx;
        }
        if(nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length)
            return new int[]{sx, sy};
        
        if(nx == sx && ny != sy) {
            if(ny > sy) {
                for(int j = sy+1; j <= ny; j++) {
                    if(board[sx][j]== -1)
                        return new int[]{sx, sy};
                }
            } else {
                for(int j = sy-1; j >= ny; j--) {
                    if(board[sx][j]== -1)
                        return new int[]{sx, sy};
                }
            }
        } else if(nx != sx && ny == sy) {
            if(nx > sx) {
                for(int j = sx+1; j <= nx; j++) {
                    if(board[j][sy]== -1)
                        return new int[]{sx, sy};
                }
            } else {
                for(int j = sx-1; j >= nx; j--) {
                    if(board[j][sy]== -1)
                        return new int[]{sx, sy};
                }
            }
        }

        return new int[] {nx, ny};
    }
}