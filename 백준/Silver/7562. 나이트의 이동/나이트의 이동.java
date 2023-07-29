
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] visit;
    static final int INF = 100_000_00;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            int N = Integer.parseInt(br.readLine());
            initVisit(N);
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            BFS(N, startX, startY);
            System.out.println(visit[endX][endY]);
        }
    }

    public static void BFS(int N, int startX, int startY) {
        int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
        int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};
        Queue<int[]> queue = new ArrayDeque<>();
        visit[startX][startY] = 0;
        queue.add(new int[] {startX, startY});
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            for (int i = 0; i < 8; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if(visit[nx][ny] != INF && visit[nx][ny] < visit[curX][curY] + 1) continue;
                if (visit[nx][ny] > visit[curX][curY] + 1) {
                    visit[nx][ny] = visit[curX][curY] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    public static void initVisit(int N) {
        visit = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(visit[i], INF);
    }

}
