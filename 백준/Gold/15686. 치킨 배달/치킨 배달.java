import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static List<Point> home = new ArrayList<>();
    static List<Point> chicken = new ArrayList<>();
    static Point[] temp;  // 최소의 치킨 집만 남긴 치킨집 배열
    static boolean[] visit;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());

                if (input == 1) home.add(new Point(i, j));
                else if (input == 2) chicken.add(new Point(i, j));
            }
        }
        temp = new Point[M];
        visit = new boolean[chicken.size()];
        DFS(M, 0, 0);
        System.out.println(answer);
    }

    // 치킨 집 중 가장 가까운 치킨 집 선택해서 거리 반환
    public static int findClosestDist(Point home, Point[] chickenList) {
        int distance = Integer.MAX_VALUE;
        for (Point chicken : chickenList) {
            int cd = calcDistance(home, chicken);
            if (distance > cd) {
                distance = cd;
            }
        }
        return distance;
    }

    public static void DFS(int M, int depth, int idx) {
        if (depth == M) {
            int sum = 0;
            for (Point curHome : home)
                sum += findClosestDist(curHome, temp);
            if (answer > sum)
                answer = sum;
        } else {
            for (int i = idx; i < chicken.size(); i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    temp[depth] = chicken.get(i);
                    DFS(M, depth + 1, i + 1);  // 조합
                    visit[i] = false;
                }
            }
        }
    }

    public static int calcDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}