import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제 해결 프로세스]
 * 1. 영역 전개 -> 가장자리 얼음을 큐에 넣기
 * 2. 백조끼리 만나는지 확인
 * 3. 큐에 넣은 얼음을 제거, 다음에 녹을 얼음을 큐에 넣기
 * 4. 백조끼리 만날 때까지 반복
 */
public class Main {

    static int R, C;
    static char[][] map;
    static int[][] territory;
    static boolean[][] visit;
    static List<Point> swans = new ArrayList<>();
    static Queue<Ice> remove = new ArrayDeque<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        territory = new int[R][C];
        visit = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = str.charAt(j);
                if (c == 'L') {
                    swans.add(new Point(i, j));
                    map[i][j] = '.';
                } else {
                    map[i][j] = c;
                }
            }
        }

        int id = setTerritory();
        parent = new int[id];
        for (int i = 1; i < id; i++) parent[i] = i;

        int day = 0;
        while (!isSameTerritory(swans.get(0), swans.get(1))) {
            day++;
            remove = destroy();
        }
        System.out.println(day);
    }

    // 외곽의 면을 큐에 넣기(최초 1번 실행)
    public static int setTerritory() {
        int id = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') continue;
                if (visit[i][j]) continue; // 방문 체크

                Queue<Point> queue = new ArrayDeque<>();
                territory[i][j] = id;
                visit[i][j] = true;
                queue.add(new Point(i, j));

                while (!queue.isEmpty()) {
                    Point cur = queue.poll();

                    for (int d = 0; d < 4; d++) {
                        int nx = cur.x + dx[d];
                        int ny = cur.y + dy[d];

                        if (OOB(nx, ny)) continue; // 배열 벗어남
                        if (visit[nx][ny]) continue;
                        visit[nx][ny] = true;

                        if (map[nx][ny] == '.') {
                            territory[nx][ny] = id;
                            queue.add(new Point(nx, ny));
                        } else { // 'X'인 경우 -> 다음 날 녹는 빙판이므로 큐에 추가
                            remove.add(new Ice(nx, ny, id));
                        }
                    }
                }
                id++;
            }
        }
        return id;
    }

    // 빙판을 녹이면서 다음에 녹을 빙판 큐 반환
    public static Queue<Ice> destroy() {
        Queue<Ice> next = new ArrayDeque<>();
        while (!remove.isEmpty()) {
            Ice ice = remove.poll();

            // 빙판 제거
            territory[ice.point.x][ice.point.y] = find(ice.parent);
            map[ice.point.x][ice.point.y] = '.';

            // 사방 탐색 -> 'X'인 경우 다음 제거 대상, '.'인 경우 union 대상
            for (int d = 0; d < 4; d++) {
                int nx = ice.point.x + dx[d];
                int ny = ice.point.y + dy[d];
                if (OOB(nx, ny)) continue;
                if (visit[nx][ny] && map[nx][ny] == 'X') continue; // 여기 체크
                if (map[nx][ny] == 'X') {
                    next.add(new Ice(nx, ny, find(ice.parent)));
                    visit[nx][ny] = true;
                } else {
                    if(find(territory[ice.point.x][ice.point.y]) != find(territory[nx][ny])) {
                        union(territory[ice.point.x][ice.point.y], territory[nx][ny]);
                    }
                }
            }
        }
        return next;
    }

    public static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
            return true;
        } else return false;
    }

    public static int find(int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    public static boolean isSameTerritory(Point start, Point end) {
        return find(territory[start.x][start.y]) == find(territory[end.x][end.y]);
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= R || y >= C;
    }

    static class Ice {
        Point point;
        int parent;

        public Ice(int x, int y, int parent) {
            point = new Point(x, y);
            this.parent = parent;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}