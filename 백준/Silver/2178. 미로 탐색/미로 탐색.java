
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;

    static int[][] board;
    static boolean[][] visitBoard;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String buffer;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visitBoard = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            buffer = st.nextToken();
            for (int j = 0; j < M; j++) {
                board[i][j] = buffer.charAt(j) - '0';
            }
        }
        BFS(0, 0);

        System.out.println(board[N - 1][M - 1]);
    }

    static void BFS(int ix, int iy) {
        int x;
        int y;
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(ix, iy));
        visitBoard[ix][iy] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                x = point.x + dx[i];
                y = point.y + dy[i];

                if (x >= 0 && x < N && y >= 0 && y < M) {
                    if (board[x][y] != 0 && visitBoard[x][y] != true) {
                        board[x][y] = board[point.x][point.y] + 1;
                        queue.add(new Point(x, y));
                        visitBoard[x][y] = true;
                    }
                }
            }
        }
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
