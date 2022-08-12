package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//불!!
//test data 링크
//http://acm.student.cs.uwaterloo.ca/~acm00/090613/data/
public class problem4179 {

    static int R;
    static int C;
    static int board[][];
    static boolean visit[][];
    static Point human;
    static Queue<Point> fireQueue = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s;
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        visit = new boolean[R][C];
        for(int i =0; i< R; i++) {
            st = new StringTokenizer(br.readLine());
            s = st.nextToken();
            for(int j = 0; j< C; j++) {
                if(s.charAt(j) == '#')  //벽
                    board[i][j] = -1;
                else if(s.charAt(j) == '.')  //지나갈 수 있는 미로공간
                    board[i][j] = 0;
                else if(s.charAt(j) == 'J') {  // 지훈이의 미로에서의 초기 위치
                    board[i][j] = 1;
                    human = new Point(i, j);
                }
                else if(s.charAt(j) == 'F') {  // 불이 난 공간
                    board[i][j] = 2;
                    fireQueue.add(new Point(i,j));
                }
            }

        }
        int min = BFS();
        if(min == Integer.MAX_VALUE)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(min);
    }

    static class Point {
        int x;
        int y;
        int count;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            count = 1;
        }
    }

    static int BFS() {
        Queue<Point> queue = new LinkedList<>();
        int dx[] = {-1, 0, 1 ,0};
        int dy[] = {0, 1, 0, -1};
        int min = Integer.MAX_VALUE;
        Point point;
        int level = 1;
        queue.add(human);
        visit[human.x][human.y] = true;
        //moveFire();
        while(!queue.isEmpty()) {

            point = queue.poll();
            board[point.x][point.y] = 1;

            if(level == point.count) {  //불 이동
                level++;
                moveFire();
                //printBoard();
            }
            if(point.x == 0 || point.x == R-1 || point.y == 0 || point.y == C-1) {
                if (min > point.count) {
                    min = point.count;
                    break;
                }
            }
            else {
                //System.out.println("before x: " + point.x + ", before y: " + point.y);
                for (int i = 0; i < 4; i++) {
                    if (board[point.x + dx[i]][point.y + dy[i]] == 2) {
                        continue;
                    }
                    else if (board[point.x + dx[i]][point.y + dy[i]] == 0) {
                        if (!visit[point.x + dx[i]][point.y + dy[i]]) {

                            Point p = new Point(point.x + dx[i], point.y + dy[i]);
                            p.count = point.count + 1;
                            queue.add(p);
                            //System.out.println("x: " + p.x + ", y: " + p.y);
                            visit[p.x][p.y] = true;
                        }
                    }
                }
            }
        }
        return min;
    }

    static void moveFire() {

        Queue<Point> secondFire = new LinkedList<>();
        Point firePoint;
        int dx[] = {-1, 0, 1 ,0};
        int dy[] = {0, 1, 0, -1};
        while(!fireQueue.isEmpty())
            secondFire.add(fireQueue.poll());

        while(!secondFire.isEmpty()){
            firePoint = secondFire.poll();
            for (int k = 0; k < 4; k++) {
                if (firePoint.x + dx[k] < 0 || firePoint.x + dx[k] >= R || firePoint.y + dy[k] < 0 || firePoint.y + dy[k] >= C)
                    continue;
                if (board[firePoint.x + dx[k]][firePoint.y + dy[k]] != -1 && board[firePoint.x + dx[k]][firePoint.y + dy[k]] != 2) {
                    board[firePoint.x + dx[k]][firePoint.y + dy[k]] = 2;
                    Point fire = new Point(firePoint.x + dx[k], firePoint.y + dy[k]);
                    //fire.count = firePoint.count +1;
                    fireQueue.add(fire);
                }
            }
        }
    }

    static void printBoard() {
        System.out.println();
        System.out.println();
        for(int i = 0; i< R; i++) {
            for(int j =0; j<C; j++) {
                if(board[i][j] == -1)
                    System.out.print("# ");

                else if(board[i][j] ==0)
                    System.out.print(". ");

                else if(board[i][j] ==1)
                    System.out.print("J ");

                else if(board[i][j] ==2)
                    System.out.print("F ");
            }
            System.out.println();
        }
    }
}
