import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 외판원 문제
 * 시작점은 어느 도시가 되든 비용이 동일하다는 것을 알고가자
 * 1 -> 2 -> 4 -> 3 -> 1
 * 2 -> 4 -> 3 -> 1 -> 2
 * [문제 해결 프로세스]
 * 비트마스크를 통해 방문했던 경로 표시
 */
public class Main {
    static int N;
    static double INF = 987654321;
    static Point[] points;
    static double[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new double[N][(1 << N) - 1];
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1.0);

        System.out.println(dfs(0, 1));
    }

    public static double dfs(int now, int visited) {
        if (visited == (1 << N) - 1) {
            return calcDist(points[now], points[0]);
        }
        if (dp[now][visited] != -1.0) return dp[now][visited];
        dp[now][visited] = INF;
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) == 1) continue;
            int nextVisit = visited | (1 << i);
            dp[now][visited] = Math.min(dp[now][visited], dfs(i, nextVisit) + calcDist(points[now], points[i]));
        }
        return dp[now][visited];
    }

    public static double calcDist(Point A, Point B) {
        return Math.sqrt(Math.pow(B.x - A.x, 2) + Math.pow(B.y - A.y, 2));
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