
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, P;
    static int[] S, ANSWER;
    static char[][] visit;
    static Queue<Point>[] queues;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        visit = new char[N][M];
        S = new int[P + 1];
        ANSWER = new int[P + 1];
        queues = new Queue[P + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            queues[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                visit[i][j] = c;
                if (Character.isDigit(c)) {
                    int target = c - '0';
                    queues[target].add(new Point(i, j));
                    ANSWER[target]++;
                }
            }
        }

        while (!allIsEmpty()) {
            for (int i = 1; i <= P; i++) {
                if (queues[i].isEmpty()) continue;
                queues[i] = expandTerritory(i, queues[i], S[i]);
            }
        }

        for (int i = 1; i <= P; i++) {
            System.out.print(ANSWER[i] + " ");
        }


    }


    public static Queue<Point> expandTerritory(int target, Queue<Point> player, int s) {
        char t = String.valueOf(target).charAt(0);
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Point> queue = new ArrayDeque<>();

        int depth = s;
        while (!player.isEmpty()) {
            if (depth == 0) break;
            int size = player.size();

            for (; size > 0; size--) {
                Point cur = player.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if (OOB(nx, ny)) continue;

                    if (visit[nx][ny] == '.') {
                        visit[nx][ny] = t;
                        player.add(new Point(nx, ny));
                        queue.add(new Point(nx, ny)); // 새로운 곳에 추가
                        ANSWER[target]++;

                    }
                }
            }
            depth--;
        }
        return queue;
    }

    public static boolean allIsEmpty() {
        for (int i = 1; i <= P; i++) {
            if (!queues[i].isEmpty()) return false;
        }
        return true;
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