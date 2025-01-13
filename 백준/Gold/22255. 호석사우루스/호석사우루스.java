import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static Point start, end;
    static int[][] arr;
    static int[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        visit = new int[3][N + 1][M + 1];
        st = new StringTokenizer(br.readLine());
        int sx, sy, ex, ey;
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());


        start = new Point(sx, sy);
        end = new Point(ex, ey);

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m <= M; m++) {
                arr[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(calc(start.x, start.y));

    }

    public static int calc(int sx, int sy) {
        PriorityQueue<Dino> queue = new PriorityQueue<>((a1, b1) -> a1.cost - b1.cost);
        for (int n = 0; n < 3; n++) {
            for (int i = 1; i <= N; i++) {
                for(int j = 1; j<= M; j++){
                    visit[n][i][j] = Integer.MAX_VALUE;
                }
            }
        }
        visit[0][sx][sy] = 0;
        queue.add(new Dino(sx, sy, 1, 0));

        while (!queue.isEmpty()) {
            Dino cur = queue.poll();
            if (cur.point.x == end.x && cur.point.y == end.y) {
                return cur.cost;
            }
            List<Point> nextMove = move(cur.cnt, cur.point.x, cur.point.y);
            for (Point next : nextMove) {
                int nextCost = cur.cost + arr[next.x][next.y];
                if (visit[cur.cnt][next.x][next.y] <= nextCost) continue;
                visit[cur.cnt][next.x][next.y] = nextCost;
                queue.add(new Dino(next.x, next.y, (cur.cnt + 1) % 3, nextCost));
            }
        }
        return -1;
    }

    public static List<Point> move(int cnt, int x, int y) {
        // 상 우 하 좌
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        List<Point> dir = new ArrayList<>();
        int nx, ny;
        if (cnt == 0) {
            for (int d = 0; d < 4; d++) {
                nx = x + dx[d];
                ny = y + dy[d];
                if (OOB(nx, ny)) continue;
                if (arr[nx][ny] == -1) continue;
                dir.add(new Point(nx, ny));
            }
        } else if (cnt == 1) {
            for (int d = 0; d < 4; d += 2) {
                nx = x + dx[d];
                ny = y + dy[d];
                if (OOB(nx, ny)) continue;
                if (arr[nx][ny] == -1) continue;
                dir.add(new Point(nx, ny));
            }
        } else if (cnt == 2) {
            for (int d = 1; d < 4; d += 2) {
                nx = x + dx[d];
                ny = y + dy[d];
                if (OOB(nx, ny)) continue;
                if (arr[nx][ny] == -1) continue;
                dir.add(new Point(nx, ny));
            }
        }
        return dir;
    }

    public static boolean OOB(int x, int y) {
        return x <= 0 || y <= 0 || x > N || y > M;
    }


    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class Dino {
        int cnt;
        Point point;
        int cost;

        public Dino(int x, int y, int cnt, int cost) {
            point = new Point(x, y);
            this.cnt = cnt;
            this.cost = cost;
        }
    }

}