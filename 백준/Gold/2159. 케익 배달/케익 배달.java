import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Point cur;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static long[][] dp;
    static Queue<Point> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        dp = new long[5][N + 1];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
            dp[i][0] = 0;
        }
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        cur = new Point(x, y);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            if (i == 0) {
                for (int d = 0; d < 5; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (OOB(nx, ny)) continue;
                    dp[d][1] = getDist(cur.x, cur.y, nx, ny);
                }

            }
            queue.add(new Point(x, y));
        }


        int idx = 2;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if(queue.isEmpty()) break;
            Point next = queue.peek();

            for (int d = 0; d < 5; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (OOB(nx, ny)) continue;
                if (dp[d][idx - 1] == Long.MAX_VALUE) continue;

                for (int d1 = 0; d1 < 5; d1++) {
                    int nx1 = next.x + dx[d1];
                    int ny1 = next.y + dy[d1];
                    if (OOB(nx1, ny1)) continue;
                    dp[d1][idx] = Math.min(dp[d1][idx], getDist(nx, ny, nx1, ny1) + dp[d][idx - 1]);
                }
            }
            idx++;
        }
        long answer = Long.MAX_VALUE;
        for (int d = 0; d < 5; d++) {
            answer = Math.min(answer, dp[d][N]);
        }
        System.out.println(answer);
    }

    public static long getDist(int cx, int cy, int nx, int ny) {
        return Math.abs(cx - nx) + Math.abs(cy - ny);
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x > 100000 || y > 100000;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}