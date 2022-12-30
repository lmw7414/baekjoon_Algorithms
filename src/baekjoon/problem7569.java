package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 토마토
// 1 익은 토마토, 0 익지 않은 토마토, -1 토마토가 들어있지 않음
public class problem7569 {

    static int X; // length 세로
    static int Y;  // width 가로
    static int Z; // height 높이
    static int[][][] arr;
    static boolean[][][] check;

    static Queue<Point> queue = new LinkedList<>();
    static int[][] xyz = {{-1, 0, 0}, {1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, -1}, {0, 0, 1}};  // 위, 아래, 앞, 뒤, 왼, 오

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken()); // 5
        X = Integer.parseInt(st.nextToken()); // 3
        Z = Integer.parseInt(st.nextToken()); // 2
        arr = new int[Z][X][Y];

        // 토마토가 없는 곳은 미리 true 설정
        check = new boolean[Z][X][Y];

        for (int i = 0; i < Z; i++) {
            for (int j = 0; j < X; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < Y; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                    if (arr[i][j][k] == -1)
                        check[i][j][k] = true;
                    if (arr[i][j][k] == 1) {
                        queue.add(new Point(i, j, k));
                        check[i][j][k] = true;
                    }
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
                    p.z = point.z + xyz[i][0];
                    p.x = point.x + xyz[i][1];
                    p.y = point.y + xyz[i][2];
                    if (!rangeCheck(p.x, p.y, p.z) || nothingCheck(p.x, p.y, p.z) || check[p.z][p.x][p.y])
                        continue;
                    queue.add(new Point(p.z, p.x, p.y));
                    check[p.z][p.x][p.y] = true;
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

    public static boolean rangeCheck(int x, int y, int z) {
        if (z >= 0 && z < Z && x >= 0 && x < X && y >= 0 && y < Y)  //범위 안에 있음
            return true;
        return false;
    }

    public static boolean nothingCheck(int x, int y, int z) {
        if (arr[z][x][y] == -1)
            return true;
        return false;
    }

    public static boolean isAllRipe() {
        for (int i = 0; i < Z; i++) {
            for (int j = 0; j < X; j++) {
                for (int k = 0; k < Y; k++) {
                    if (!check[i][j][k])
                        return false;
                }
            }
        }
        return true;
    }

    public static void printBoard() {
        for (int i = 0; i < Z; i++) {
            for (int j = 0; j < X; j++) {
                for (int k = 0; k < Y; k++) {
                    System.out.print(arr[i][j][k] + " ");
                }
                System.out.println();
            }
        }
    }

    public static class Point {
        int z;  //높이
        int x;
        int y;

        public Point() {
        }

        public Point(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}
