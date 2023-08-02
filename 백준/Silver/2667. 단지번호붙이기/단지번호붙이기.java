import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//단지 번호 붙이기
public class Main {

    static int N;
    static int board[][];
    static boolean visitBoard[][];
    static int count = 0;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String buffer;
        N = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        visitBoard = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            buffer = st.nextToken();
            for (int j = 0; j < N; j++) {
                board[i][j] = buffer.charAt(j) - '0';
            }
        }
        isVisited();
        System.out.println(count);
        result.sort(Comparator.naturalOrder());
        for(int a : result)
            System.out.println(a);
        //printArr();
    }


    static void BFS(int x, int y) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        int size = 1;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visitBoard[x][y] = true;
        count++;
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            board[point.x][point.y] = count;

            for (int i = 0; i < 4; i++) {
                x = point.x + dx[i];
                y = point.y + dy[i];

                if (x >= 0 && x < N && y >= 0 && y < N) {
                    if (board[x][y] != 0 && visitBoard[x][y] != true) {
                        board[x][y] = count;
                        size++;
                        queue.add(new Point(x, y));
                        visitBoard[x][y] = true;
                    }
                }
            }
        }
        result.add(size);

    }

    static void isVisited() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visitBoard[i][j] == false && board[i][j] == 1)
                    BFS(i, j);
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


    public static void printArr() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
