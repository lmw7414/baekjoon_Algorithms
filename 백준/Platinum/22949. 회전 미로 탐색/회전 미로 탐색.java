
import java.util.*;
import java.io.*;

public class Main {
    static int K;
    static char[][][] board;
    static boolean[][][] visit;
    static int[] end;
    static Queue<Point> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        board = new char[4][4 * K][4 * K];
        visit = new boolean[4][4 * K][4 * K];
        for (int i = 0; i < 4 * K; i++) {
            String str = br.readLine();
            for (int j = 0; j < 4 * K; j++) {
                board[0][i][j] = str.charAt(j);
                if (board[0][i][j] == 'S') {
                    queue.add(new Point(0, i, j, 0));
                    visit[0][i][j] = true;
                } else if (board[0][i][j] == 'E') end = new int[]{i, j};
            }
        }

        for (int k = 0; k < K * K; k++) {
            for (int d = 1; d < 4; d++) { // 회전
                int sx = k / K * 4;
                int sy = k % K * 4;
                rotate(d, sx, sy, sx + 4, sy + 4);
            }
        }
        System.out.println(calc());
    }

    public static int calc() {
        int[] dx = {0, -1, 1, 0, 0};
        int[] dy = {0, 0, 0, -1, 1};
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int d = 0; d < 5; d++) {
                int nx = dx[d] + cur.x;
                int ny = dy[d] + cur.y;
                if (OOB(nx, ny)) continue;
                int nextD;
                // 현재 위치와 이동한 위치의 구역이 다르다면
                if (getDistrict(cur.x, cur.y) != getDistrict(nx, ny)) {
                    if (board[0][nx][ny] == '#') continue; // 벽인 경우
                    if (board[0][nx][ny] == 'E') return cur.time + 1;
                    nextD = 1;
                } else {
                    if (board[cur.d][nx][ny] == '#') continue; // 벽인 경우
                    if (board[cur.d][nx][ny] == 'E') return cur.time + 1;
                    nextD = (cur.d + 1) % 4;
                }
                // 현재 위
                int[] rotated = rotatePoint(nx, ny);
                if (visit[nextD][rotated[0]][rotated[1]]) continue; // 방문체크
                visit[nextD][rotated[0]][rotated[1]] = true;
                queue.add(new Point(nextD, rotated[0], rotated[1], cur.time + 1));
            }
        }
        return -1;
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= 4 * K || y >= 4 * K;
    }

    // 현재 구역의 번호
    public static int getDistrict(int x, int y) {
        return K * (x / 4) + (y / 4);
    }

    // 한 면에 대한 90도 회전
    public static void rotate(int cur, int sx, int sy, int ex, int ey) {
        for (int y = 3; y >= 0; y--) {
            for (int x = 0; x <= 3; x++) {
                board[cur][sx + x][sy + y] = board[cur - 1][sx + 3 - y][sy + x];
            }
        }
    }

    // 90도 회전한 위치 반환
    public static int[] rotatePoint(int x, int y) {
        int k = getDistrict(x, y);
        int sx = k / K * 4;
        int sy = k % K * 4;

        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j <= 3; j++) {
                if (sx + 3 - i == x && sy + j == y)
                    return new int[]{sx + j, sy + i};
            }
        }
        return null;
    }

    static class Point {
        int d;
        int x, y;
        int time;

        public Point(int d, int x, int y, int time) {
            this.d = d;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}