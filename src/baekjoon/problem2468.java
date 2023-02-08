package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem2468 {

    static int MAX = 0;
    static int N;
    static int[][] board;
    static int answer = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (MAX < input)
                    MAX = input;
                board[i][j] = input;
            }
        }

        for(int i = MAX; i> 0; i--) {
            int result = calc(i);
            if(answer < result)
                answer = result;
        }
        System.out.println(answer);
    }

    static int calc(int depth) {
        boolean[][] check = new boolean[N][N];
        int[] dx = {-1, 1, 0, 0}; // 상하좌우
        int[] dy = {0, 0, -1, 1};
        Queue<Point> queue = new LinkedList<>();
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > depth) {
                    check[i][j] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (check[i][j]) {
                    queue.add(new Point(i, j));
                    count++;
                    while (!queue.isEmpty()) {
                        Point p = queue.poll();

                        check[p.x][p.y] = false;

                        for(int k = 0; k< 4; k++) {
                            int nx = p.x + dx[k];
                            int ny = p.y + dy[k];

                            if(nx < 0 || nx >= N || ny < 0 || ny >= N || !check[nx][ny])
                                continue;

                            check[nx][ny] = false;
                            queue.add(new Point(nx, ny));
                        }
                    }

                }
            }
        }
            return count;
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
