package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem1012 {
    static int N;
    static StringBuffer sb = new StringBuffer();
    static int[][] board;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            board = new int[col][row];
            check = new boolean[col][row];
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                board[y][x] = 1;

                // 계산 후 출력
            }
            sb.append(bfs(row, col) + "\n");
        }
        System.out.print(sb.toString());

    }

    static int bfs(int row, int col) {
        int count = 0;

        int[][] direct = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 상하우좌

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (board[i][j] == 1 && !check[i][j]) {
                    count++;
                    Queue<Point> queue = new LinkedList();
                    queue.add(new Point(i, j));
                    while (!queue.isEmpty()) {
                        Point p = queue.poll();
                        if (!check[p.x][p.y]) {
                            check[p.x][p.y] = true;
                            for (int k = 0; k < 4; k++) {
                                int dx = p.x + direct[k][0];
                                int dy = p.y + direct[k][1];

                                if (dx < 0 || dx >= col || dy < 0 || dy >= row || board[dx][dy] == 0)
                                    continue;
                                if (!check[dx][dy] && board[dx][dy] == 1) {
                                    queue.add(new Point(dx, dy));
                                }
                            }
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
