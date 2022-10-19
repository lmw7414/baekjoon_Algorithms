package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 소가 길을 건너간 이유6
public class problem14466 {
    static int N, K, R;

    static int board[][];
    static boolean visited[][];
    static boolean visitedCow[];
    static int parent[];
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, 1, -1};
    static ArrayList<Bridge> bridges = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 목초지 사이즈 N x N
        K = Integer.parseInt(st.nextToken());  // 소
        R = Integer.parseInt(st.nextToken());  // 다리

        board = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        visitedCow = new boolean[K + 1];

        int a, b, c, d;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            bridges.add(new Bridge(a, b, c, d));
            bridges.add(new Bridge(c, d, a, b));
        }

        ArrayList<Point> cows = new ArrayList<>();
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            cows.add(new Point(a, b));
            board[a][b] = i;
        }

        parent = new int[K + 1];
        for (int i = 1; i <= K; i++)
            parent[i] = i;

        int answer[] = new int[K + 1];

        for (int i = 1; i <= cows.size(); i++) {
            for (int j = 0; j < visited.length; j++)
                Arrays.fill(visited[j], false);
            Arrays.fill(visitedCow, false);

            if (parent[i] != i)
                continue;

            visitedCow[i] = true;
            BFS(i, cows.get(i - 1).x, cows.get(i - 1).y);

            for (int j = 1; j <= K; j++) {
                if (!visitedCow[j])
                    count++;
                else
                    parent[j] = i;
            }

            answer[i] = count;
            count = 0;
        }

        int pNode; // 부모 노드
        int result = 0;

        for (int i = 1; i <= K; i++) {
            pNode = find(i);
            result += answer[pNode];
        }
        System.out.println(result / 2);
    }

    static void BFS(int cowN, int x, int y) {

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int cx = point.x + dx[i];
                int cy = point.y + dy[i];

                if (cx < 1 || cy < 1 || cx > N || cy > N)
                    continue;

                if (visited[cx][cy])
                    continue;

                if (isBridge(point.x, point.y, cx, cy))
                    continue;

                if (board[cx][cy] > cowN)
                    visitedCow[board[cx][cy]] = true;

                visited[cx][cy] = true;
                queue.add(new Point(cx, cy));
            }
        }
    }

    static int find(int x) {
        if (parent[x] != x)
            return find(parent[x]);
        else
            return x;
    }

    static boolean isBridge(int a, int b, int c, int d) {
        if (bridges.contains(new Bridge(a, b, c, d)))
            return true;
        return false;
    }

    /*static void DFS(int cowN, int x, int y ) {

        if(board[x][y] > cowN)
            visitedCow[board[x][y]] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if(cx < 1 || cy < 1 || cx > N || cy > N)
                continue;

            if(isBridge(x,y,cx,cy))
                continue;

            if(visited[cx][cy])
                continue;

            visited[x][y] = true;
            DFS(cowN, cx,cy);
        }
    }*/

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Bridge {
        int a;
        int b;
        int c;
        int d;

        public Bridge(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bridge bridge = (Bridge) o;
            return a == bridge.a && b == bridge.b && c == bridge.c && d == bridge.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }
    }
}
