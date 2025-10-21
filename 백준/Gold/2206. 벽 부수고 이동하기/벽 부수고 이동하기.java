
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j - 1) - '0';
            }
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        if(N == 1 && M == 1) return 1;
        boolean[][][] visit = new boolean[2][N + 1][M + 1];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(1, 1, 0, 1));
        visit[0][1][1] = true;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (OOB(nx, ny)) continue;
                if (visit[cur.crush][nx][ny]) continue;
                if (nx == N && ny == M) {
                    return cur.cnt + 1;
                }

                if (arr[nx][ny] == 1) { // 벽인 경우
                    if (cur.crush == 1) continue; // 이미 벽을 부순 전적이 있음
                    //벽 부수기
                    visit[cur.crush + 1][nx][ny] = true;
                    queue.add(new Node(nx, ny, cur.crush + 1, cur.cnt + 1));
                } else { // 그렇지 않은 경우
                    visit[cur.crush][nx][ny] = true;
                    queue.add(new Node(nx, ny, cur.crush, cur.cnt + 1));
                }
            }

        }

        return -1;
    }

    public static boolean OOB(int x, int y) {
        return x < 1 || y < 1 || x > N || y > M;
    }

    static class Node {
        int x, y;
        int crush;
        int cnt;

        public Node(int x, int y, int crush, int cnt) {
            this.x = x;
            this.y = y;
            this.crush = crush;
            this.cnt = cnt;
        }
    }

}