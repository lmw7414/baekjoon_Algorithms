import java.util.*;
import java.io.*;

public class Main {
    static int M, N;
    static int[][] cnt;
    static Node[][] arr;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new Node[M][N];
        cnt = new int[M][N];
        visit = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = new Node(i, j, Integer.parseInt(st.nextToken()));
            }
        }
        calc(0, 0);
//        printArr();
        System.out.println(cnt[M - 1][N - 1]);
    }


    public static void calc(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        cnt[x][y] = 1;
        pq.add(arr[x][y]);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(visit[cur.x][cur.y]) continue;
//            System.out.printf("%d %d\n", cur.x, cur.y);
//            System.out.printf("%d\n", arr[cur.x][cur.y].val);
            visit[cur.x][cur.y] = true;
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (arr[nx][ny].val >= arr[cur.x][cur.y].val) continue;
                if (visit[nx][ny]) continue;

                //if (cnt[nx][ny] == 0) cnt[nx][ny] = cnt[cur.x][cur.y];
                cnt[nx][ny] += cnt[cur.x][cur.y];
                pq.add(arr[nx][ny]);
            }
        }
    }

    public static void printArr() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(cnt[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static class Node implements Comparable<Node> {
        int x, y, val;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return o.val - this.val;
        }
    }
}