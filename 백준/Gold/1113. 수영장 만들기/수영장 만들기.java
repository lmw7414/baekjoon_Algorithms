import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
높이는 1~9까지
1부터 9까지 높이를 높여가며 접근

 */

public class Main {
    static int N, M;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }
        int answer = 0;
        for (int i = 2; i <= 9; i++) {
            int result = calc(i);
            //if(result == 0) break;
            answer += result;
            //System.out.println(result);
            //printArr();
        }
        System.out.println(answer);
    }

    public static void printArr() {
        System.out.println("======== ========");
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("======== ========");
    }

    public static int calc(int height) {
        int result = 0;
        //1. 벽 체크
        boolean[][] visit = new boolean[N][M];
        checkEdge(visit, height);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j]) continue;
                Queue<Point> water = new ArrayDeque<>();
                boolean[][] copy = copyArr(visit);
                if (!isOkWaterSpace(copy, water, i, j)) {// 물 채울 수 있음
                    result += water.size();
                    while (!water.isEmpty()) {
                        Point cur = water.poll();
                        visit[cur.x][cur.y] = true;
                        arr[cur.x][cur.y] = height;
                    }
                } else {
                    while (!water.isEmpty()) {
                        Point cur = water.poll();
                        visit[cur.x][cur.y] = true;
                    }
                }
            }
        }
        return result;
    }

    public static boolean isOkWaterSpace(boolean[][] visit, Queue<Point> water, int startX, int startY) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(startX, startY));
        water.add(new Point(startX, startY));
        visit[startX][startY] = true;
        boolean flag = false; // 벽과 연결되어진 부분이 있다면 해당부분은 불가능하므로 물이 채워지지 않음
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (isEdge(cur.x, cur.y)) flag = true; // 해당 영역이 물이 빠져나가는 곳과 연결이 되어 있는지

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (OOB(nx, ny)) continue;
                if (visit[nx][ny]) continue;
                water.add(new Point(nx, ny));
                queue.add(new Point(nx, ny));
                visit[nx][ny] = true;
            }
        }

        return flag;
    }

    public static void checkEdge(boolean[][] visit, int height) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] >= height) {
                    visit[i][j] = true;
                }
            }
        }
    }

    public static boolean[][] copyArr(boolean[][] arr) {
        boolean[][] copy = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }


    public static boolean isEdge(int x, int y) {
        return x == 0 || y == 0 || x == N - 1 || y == M - 1;
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