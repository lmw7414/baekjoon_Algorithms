
import java.util.*;
import java.io.*;

/**
 * 1. visit 하나로 적용
 * 2. 방문 가능하고, 불 켜진곳 1
 * 3. 불만 켜진 곳 2
 * 4. 불 키면서 이동 가능해진 불만 켜진 곳 -> 1로 변경
 * 5. 1로 바뀐 곳 카운트
 */
public class Main {
    static int N, M;
    static int[][] visit; // 소가 이동할 수 있는 곳
    static List<Point>[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new List[N + 1][N + 1];
        visit = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = new ArrayList<>();
            }
        }
        int x, y, a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            arr[x][y].add(new Point(a, b));
        }
        calc();
        int answer = 0;
//        for(int i = 1; i <= N; i++) {
//            for(int j = 1; j <= N; j++) {
//                if(visit[i][j] == 1 || visit[i][j] == 2) System.out.print("1");
//                else System.out.print("0");
//                System.out.print(visit[i][j]);
//            }
//            System.out.println();
//        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visit[i][j] == 1 || visit[i][j] == 2) answer++;
            }
        }
        System.out.println(answer);
    }

    /**
     * 1. visit 하나로 적용
     * 2. 방문 가능하고, 불 켜진곳 1
     * 3. 불만 켜진 곳 2
     * 4. 불 키면서 이동 가능해진 불만 켜진 곳 -> 1로 변경
     * 5. 1로 바뀐 곳 카운트
     */
    public static void calc() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(1, 1));
        visit[1][1] = 1;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            // 불 켜기
            for (Point next : arr[cur.x][cur.y]) {
                visit[next.x][next.y] = 2;

                for (int d = 0; d < 4; d++) {
                    int nx = next.x + dx[d];
                    int ny = next.y + dy[d];
                    if (OOB(nx, ny)) continue;
                    if (visit[nx][ny] == 1) {
                        // 주변에 연결되는 방이 있는지 추가 확인
                        Queue<Point> q = new ArrayDeque<>();
                        q.add(next);
                        queue.add(next);
                        visit[next.x][next.y] = 1;

                        while (!q.isEmpty()) {
                            Point c = q.poll();
                            for (int t = 0; t < 4; t++) {
                                int tx = c.x + dx[t];
                                int ty = c.y + dy[t];
                                if (OOB(tx, ty)) continue;
                                if (visit[tx][ty] == 2) {
                                    visit[tx][ty] = 1;
                                    q.add(new Point(tx, ty));
                                    queue.add(new Point(tx, ty));
                                }
                            }
                        }
                        break;
                    }
                }
            }
            arr[cur.x][cur.y].clear();
        }
    }

    public static boolean OOB(int x, int y) {
        return x < 1 || y < 1 || x > N || y > N;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

}