import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static char[][] arr;
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visit = new boolean[K + 1][N][M];

        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            for (int m = 0; m < M; m++) {
                arr[n][m] = str.charAt(m);
            }
        }

        calc();

    }

    public static void calc() {
        int[] dx = {-1, 1, 0, 0, 0};
        int[] dy = {0, 0, -1, 1, 0};
        Queue<Point> queue = new ArrayDeque<>();
        visit[0][0][0] = true;
        queue.add(new Point(0, 0, 0, true, 1));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                System.out.println(cur.cost);
                return;
            }
            boolean nextDay = !cur.day;

            for (int d = 0; d < 5; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (OOB(nx, ny)) continue;


                if (cur.day) { // 낮인 경우 | 벽 부수기 가능
                    if (d == 4) continue; // 낮이면 머무를 필요 없음
                    if (arr[nx][ny] == '1' && cur.k < K) {
                        if (visit[cur.k + 1][nx][ny]) continue;
                        visit[cur.k + 1][nx][ny] = true;
                        queue.add(new Point(nx, ny, cur.k + 1, nextDay, cur.cost + 1));
                    }
                    if (arr[nx][ny] == '1') continue;
                    if (visit[cur.k][nx][ny]) continue;
                    visit[cur.k][nx][ny] = true;
                    queue.add(new Point(nx, ny, cur.k, nextDay, cur.cost + 1));

                } else { // 밤인 경우
                    if (d == 4) {
                        queue.add(new Point(nx, ny, cur.k, nextDay, cur.cost + 1));
                        continue;
                    }
                    if (arr[nx][ny] == '1') continue;
                    if (visit[cur.k][nx][ny]) continue;
                    visit[cur.k][nx][ny] = true;
                    queue.add(new Point(nx, ny, cur.k, nextDay, cur.cost + 1));
                }

            }

        }
        System.out.println(-1);
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }


    static class Point {
        int x, y, k;
        boolean day;
        int cost;

        public Point(int x, int y, int k, boolean day, int cost) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.day = day;
            this.cost = cost;
        }
    }

}