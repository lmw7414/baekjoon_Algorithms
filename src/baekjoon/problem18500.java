package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//미네랄 2
public class problem18500 {

    static int R, C, N;
    static int xCount = 0;
    static int nArr[];  // 던지는 높이 담는 배열
    static int board[][];
    static boolean visited[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][C];


        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String c = st.nextToken();
            for (int j = 0; j < C; j++) {
                if (c.charAt(j) == 'x') {
                    board[i][j] = 1;
                    xCount++;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        nArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            crash(i, nArr[i]);
            BFS();
            moveCluster();

            //System.out.println(i+1 + " 번째 보드 상황");
            //System.out.println();
        }
        printBoard();


    }

    /**
     * 클러스터가 발생했는지 확인하는 메서드
     * 클러스터 발생 시 중력에 의한 위치 변경 메서드
     * 아래로 떨어지면서 다른 미네랄에 걸리면 떨어지는 것을 멈춰야 함
     * <p>
     * 깊이 우선 탐색 메서드 - 스택 활용
     */
    // 높이 변환
    static int changeHeight(int x) {
        return R - x;
    }

    //방향에 따라 부수기
    static void crash(int direction, int height) {  // direction ==1 -> left , direction ==2 -> right
        height = changeHeight(height);
        if (direction % 2 == 0) {
            for (int i = 0; i < C; i++) {
                if (board[height][i] == 1) {
                    board[height][i] = 0;
                    break;
                }
            }
        } else {
            for (int i = C - 1; i >= 0; i--) {
                if (board[height][i] == 1) {
                    board[height][i] = 0;
                    break;
                }
            }
        }
    }

    //클러스터확인 BFS 아래부터 체크 한 후 위에서 다시 한번 체크해서 안되어 있는 것은 클러스터로 간주
    // 아래서 부터 확인할 때
    static void BFS() {
        int dx[] = {1, 0, -1, 0}; // 위쪽, 오른쪽 아래쪽, 왼쪽
        int dy[] = {0, 1, 0, -1};
        visited = new boolean[R][C];

        Queue<Point> queue = new LinkedList();

        for (int i = 0; i < C; i++) {
            if (!visited[R - 1][i] && board[R - 1][i] == 1) {
                queue.add(new Point(R - 1, i));
                visited[R - 1][i] = true;
            }
        }

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int cx = point.x + dx[i];
                int cy = point.y + dy[i];

                if (cx < 0 || cx >= R || cy < 0 || cy >= C)
                    continue;
                if (board[cx][cy] == 0)
                    continue;

                if (!visited[cx][cy]) {
                    queue.add(new Point(cx, cy));
                    visited[cx][cy] = true;
                }
            }
        }
    }

    static void moveCluster() {
        int count;
        int minHeight = Integer.MAX_VALUE;
        ArrayList<Point> cluster = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && board[i][j] == 1) {
                    cluster.add(new Point(i, j));
                }
            }
        }

        for (int i = 0; i < cluster.size(); i++) {
            int x = cluster.get(i).x;
            int y = cluster.get(i).y;


            for (int j = x; j < R; j++) {
                if (visited[j][y] && board[j][y] == 1) {
                    minHeight = Math.min(minHeight, j - x - 1);
                    break;
                }
                if (j == R - 1) {
                    minHeight = Math.min(minHeight, R - x - 1);
                    break;
                }
            }

        }
        if (minHeight != Integer.MAX_VALUE) {
            //System.out.println(minHeight);
            for (int i = cluster.size() - 1; i >= 0; i--) {
                int x = cluster.get(i).x;
                int y = cluster.get(i).y;

                board[x][y] = 0;
                board[x + minHeight][y] = 1;
            }
        }
    }

    //보드 프린트
    static void printBoard() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 0)
                    System.out.printf(".");
                else
                    System.out.printf("x");
            }
            System.out.println();
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
