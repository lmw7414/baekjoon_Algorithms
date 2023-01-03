package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 토마토
// 1 익은 토마토, 0 익지 않은 토마토, -1 토마토가 들어있지 않음
public class problem7576 {

    static int X; // length 세로
    static int Y;  // width 가로
    static int[][] arr;
    static boolean[][] check;

    static Queue<Point> queue = new LinkedList<>();
    static int[][] xyz = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};  // 위, 아래, 왼, 오

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken()); // 5
        X = Integer.parseInt(st.nextToken()); // 3
        arr = new int[X][Y];

        // 토마토가 없는 곳은 미리 true 설정
        check = new boolean[X][Y];

        for (int j = 0; j < X; j++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < Y; k++) {
                arr[j][k] = Integer.parseInt(st.nextToken());
                if (arr[j][k] == -1)
                    check[j][k] = true;
                if (arr[j][k] == 1) {
                    queue.add(new Point(j, k));
                    check[j][k] = true;
                }
            }
        }

        System.out.println(BFS());
    }

    public static int BFS() {
        int sec = 0;
        Queue<Point> tempQueue = new LinkedList<>();

        if (isAllRipe())
            return sec;


        while (!queue.isEmpty()) {

            while (!queue.isEmpty()) {
                tempQueue.add(queue.poll());
            }

            while (!tempQueue.isEmpty()) {
                Point point = tempQueue.poll();
                for (int i = 0; i < xyz.length; i++) {
                    Point p = new Point();
                    p.x = point.x + xyz[i][0];
                    p.y = point.y + xyz[i][1];
                    if (!rangeCheck(p.x, p.y) || nothingCheck(p.x, p.y) || check[p.x][p.y])
                        continue;
                    queue.add(new Point(p.x, p.y));
                    check[p.x][p.y] = true;
                }
            }
            while (!tempQueue.isEmpty()) {
                queue.add(tempQueue.poll());
            }
            sec++;

            if (isAllRipe())
                return sec;
        }
        return -1;
    }

    public static boolean rangeCheck(int x, int y) {
        if ( x >= 0 && x < X && y >= 0 && y < Y)  //범위 안에 있음
            return true;
        return false;
    }

    public static boolean nothingCheck(int x, int y) {
        if (arr[x][y] == -1)
            return true;
        return false;
    }

    public static boolean isAllRipe() {

            for (int j = 0; j < X; j++) {
                for (int k = 0; k < Y; k++) {
                    if (!check[j][k])
                        return false;
                }
            }

        return true;
    }

    public static void printBoard() {
            for (int j = 0; j < X; j++) {
                for (int k = 0; k < Y; k++) {
                    System.out.print(arr[j][k] + " ");
                }
                System.out.println();
            }

    }

    public static class Point {
        int x;
        int y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
