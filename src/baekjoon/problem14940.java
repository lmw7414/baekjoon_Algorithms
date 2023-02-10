package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem14940 {
    static int N;
    static int M;
    static int[][] board;
    static int[][] answer;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Point start = new Point();
        board = new int[N][M];
        answer = new int[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 2)
                    start = new Point(i, j, 0);
                board[i][j] = input;
            }
        }
        initAnswer();
        BFS(start);
        printAnswer();
    }

    public static void BFS(Point start) {
        Queue<Point> queue = new LinkedList<>();

        int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        check[start.x][start.y] = true;
        queue.offer(start);
        answer[start.x][start.y] = start.c;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dxy[i][0];
                int ny = current.y + dxy[i][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || board[nx][ny] == 0 || check[nx][ny])
                    continue;
                answer[nx][ny] = current.c + 1;
                check[nx][ny] = true;
                queue.offer(new Point(nx, ny, current.c + 1));
            }
        }
    }

    public static void printAnswer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void initAnswer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    answer[i][j] = 0;
                } else {
                    answer[i][j] = -1;
                }
            }
        }
    }

    static class Point {
        int x;
        int y;
        int c;

        Point() {
        }

        Point(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
