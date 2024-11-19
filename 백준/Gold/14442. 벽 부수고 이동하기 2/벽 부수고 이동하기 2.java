import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] arr;
    static boolean[][][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j - 1) - '0';
            }
        }
        System.out.println(BFS(1, 1, 0));
    }

    public static int BFS(int x, int y, int k) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y, k, 1});
        visit[x][y][k] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == N && cur[1] == M) return cur[3];

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx <= 0 || ny <= 0 || nx > N || ny > M) continue;
                if (visit[nx][ny][cur[2]]) continue;  // 이미 방문했으면
                if (arr[nx][ny] == 1) { // 부수고 가거나, 다른 길로 가거나
                    if (cur[2] < K) {
                        visit[nx][ny][cur[2]] = true;
                        queue.add(new int[]{nx, ny, cur[2] + 1, cur[3] + 1});
                    }
                } else {
                    visit[nx][ny][cur[2]] = true;
                    queue.add(new int[]{nx, ny, cur[2], cur[3] + 1});
                }
            }
        }
        return -1;
    }

}