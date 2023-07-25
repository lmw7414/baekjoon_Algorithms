import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int N;
    static Point first;
    static Queue<Point> queue = new LinkedList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        first = new Point(0, 0, 0, 1);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 1) arr[i][j] = 1;
            }
        }
        if(arr[N - 1][N - 1] == 1)
            System.out.println(0);
        else {
            BFS();
            System.out.println(answer);
        }
    }

    public static void BFS() {
        queue.add(first);

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            switch (checkDirection(cur)) {
                case -1:  // 가로
                    moveWidth(cur);
                    break;
                case 0:  // 세로
                    moveCross(cur);
                    break;
                case 1:  // 대각선
                    moveHeight(cur);
                    break;
            }
        }
    }

    //가로
    public static void moveWidth(Point p) {
        // 왼쪽 or 왼쪽 아래
        int nx = p.sx;
        int ny = p.sy + 1;
        check(p, nx, ny);

        nx = p.sx + 1;
        ny = p.sy + 1;
        check(p, nx, ny);

    }

    //세로
    public static void moveHeight(Point p) {
        // 아래, 왼쪽 아래
        int nx = p.sx + 1;
        int ny = p.sy;
        check(p, nx, ny);

        nx = p.sx + 1;
        ny = p.sy + 1;
        check(p, nx, ny);
    }

    //대각선
    public static void moveCross(Point p) {
        // 아래, 왼쪽 아래, 왼쪽
        int nx = p.sx + 1;
        int ny = p.sy;
        check(p, nx, ny);

        nx = p.sx + 1;
        ny = p.sy + 1;
        check(p, nx, ny);

        nx = p.sx;
        ny = p.sy + 1;
        check(p, nx, ny);

    }

    public static void check(Point p, int nx, int ny) {
        if (checkDirection(new Point(p.sx, p.sy, nx, ny)) == 0) {  // 대각선일 경
            if (nx < N && ny < N && arr[nx][ny] == 0 && arr[nx - 1][ny] == 0 && arr[nx][ny - 1] == 0) {
                if (nx == N - 1 && ny == N - 1) answer++;
                else queue.add(new Point(p.sx, p.sy, nx, ny));
            }
        } else {
            if (nx < N && ny < N && arr[nx][ny] == 0) {
                if (nx == N - 1 && ny == N - 1) answer++;
                else queue.add(new Point(p.sx, p.sy, nx, ny));
            }
        }
    }

    // 방향 체크
    public static int checkDirection(Point p) {
        if (p.fx == p.sx) return -1;  // 가로
        else if (p.fy == p.sy) return 1; // 세로
        else return 0; // 대각선
    }

}

class Point {
    int fx;
    int fy;
    int sx;
    int sy;

    public Point(int fx, int fy, int sx, int sy) {
        this.fx = fx;
        this.fy = fy;
        this.sx = sx;
        this.sy = sy;
    }

}
