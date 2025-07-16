
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(BFS());
    }

    public static int BFS() {
        int[][] visit = new int[N][N];
        int[] dx = {-1 ,1 ,0, 0};
        int[] dy = {0, 0, -1, 1};
        visit[0][0] = 0;
        for(int i = 0; i < N; i++) Arrays.fill(visit[i], Integer.MAX_VALUE);
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.abs - b.abs);
        pq.add(new Point(0, 0, 0));

        while (!pq.isEmpty()) {
            Point cur =pq.poll();
            if(cur.x == N - 1 && cur.y == N - 1) return cur.abs;
            
            for(int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if(OOB(nx, ny)) continue;
                int nABS = Math.abs(arr[cur.x][cur.y] - arr[nx][ny]);
                if(visit[nx][ny] <= nABS) continue;
                visit[nx][ny] = nABS;
                pq.add(new Point(nx, ny, Math.max(nABS, cur.abs)));

            }
        }
        return Integer.MAX_VALUE;
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    static class Point {
        int x, y, abs;
        public Point(int x, int y, int abs) {
            this.x = x;
            this.y = y;
            this.abs = abs;
        }
    }
}