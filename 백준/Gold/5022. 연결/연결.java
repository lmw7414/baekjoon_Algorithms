import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Point[] points = new Point[4];
    static boolean[][] aVisit, bVisit; // 경로 체크하기 위함
    static int INF = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        init();
        int aLen1 = BFS(points[0], points[1], aVisit, bVisit);
        int bLen1 = BFS(points[2], points[3], bVisit, aVisit);


        init();
        int bLen2 = BFS(points[2], points[3], bVisit, aVisit);
        int aLen2 = BFS(points[0], points[1], aVisit, bVisit);


        if ((aLen1 == INF || bLen1 == INF) && (aLen2 == INF || bLen2 == INF)) System.out.println("IMPOSSIBLE");
        else System.out.println(Math.min(aLen1 + bLen1, aLen2 + bLen2));
    }


    public static int BFS(Point start, Point end, boolean[][] target, boolean[][] other) {
        int[][] visit = new int[N + 1][M + 1];
        int len = -1;
        for (int i = 0; i <= N; i++) Arrays.fill(visit[i], Integer.MAX_VALUE);
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        visit[start.x][start.y] = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.x == end.x && cur.y == end.y) {
                len = visit[cur.x][cur.y];
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx > N || ny > M) continue;
                if (visit[nx][ny] <= visit[cur.x][cur.y] + 1) continue;
                if (other[nx][ny]) continue;

                visit[nx][ny] = visit[cur.x][cur.y] + 1;
                queue.add(new Point(nx, ny));
            }
        }
        if (len == -1) return INF;
        // target 배열 경로 채우기
        fillArr(visit, target, start, end);
        return len;
    }

    public static void fillArr(int[][] visit, boolean[][] target, Point start, Point end) {
        Point cur = end;
        while (cur.x != start.x || cur.y != start.y) {
            boolean flag = false;
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx > N || ny > M) continue;
                if (visit[cur.x][cur.y] - 1 == visit[nx][ny]) {
                    flag = true;
                    target[nx][ny] = true;
                    cur = new Point(nx, ny);
                }
            }
            if (!flag) break;
        }
    }

    public static void printMap(boolean[][] v) {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (v[i][j]) System.out.print(1);
                else System.out.print(0);
            }
            System.out.println();
        }
    }

    public static void init() {
        aVisit = new boolean[N + 1][M + 1];
        bVisit = new boolean[N + 1][M + 1];
        aVisit[points[0].x][points[0].y] = true;
        aVisit[points[1].x][points[1].y] = true;
        bVisit[points[2].x][points[2].y] = true;
        bVisit[points[3].x][points[3].y] = true;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}