import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static int N, M;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Point> cheese = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        fillAir(0, 0, cheese);
        while (!cheese.isEmpty()) {
            meltCheese();
            answer++;
        }
        System.out.println(answer);
    }

    // 공기 채우기(방문처리)
    public static void fillAir(int x, int y, Queue<Point> cheese) {
        Queue<Point> air = new ArrayDeque<>();
        visit[x][y] = true;
        air.add(new Point(x, y));

        while (!air.isEmpty()) {
            Point cur = air.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (OOB(nx, ny)) continue;
                if (visit[nx][ny]) continue;
                visit[nx][ny] = true;

                if (arr[nx][ny] == 1) {
                    cheese.add(new Point(nx, ny));
                    continue;
                }
                air.add(new Point(nx, ny));
            }
        }
    }

    public static void meltCheese() {
        Queue<Point> temp = new ArrayDeque<>();
        Queue<Point> melting = new ArrayDeque<>();
        while (!cheese.isEmpty()) {
            Point cur = cheese.poll();
            if (isAirSide(cur.x, cur.y)) { // 치즈 면적 2 이상
                melting.add(cur);
                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if (!visit[nx][ny] && arr[nx][ny] == 1) {
                        temp.add(new Point(nx, ny));
                        visit[nx][ny] = true;
                    } else if (!visit[nx][ny] && arr[nx][ny] == 0) melting.add(new Point(nx, ny));
                }
            } else temp.add(cur);
        }
        while (!melting.isEmpty()) {
            Point cur = melting.poll();
            if (arr[cur.x][cur.y] == 0 || !visit[cur.x][cur.y]) {
                fillAir(cur.x, cur.y, temp);
                continue;
            }
            arr[cur.x][cur.y] = 0;
            visit[cur.x][cur.y] = true;
        }
        cheese = temp;
    }

    public static boolean isAirSide(int x, int y) {

        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (visit[nx][ny] && arr[nx][ny] == 0) cnt++;
        }

        return cnt >= 2;
    }

    public static boolean OOB(int x, int y) {
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