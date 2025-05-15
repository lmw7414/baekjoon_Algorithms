
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int total;
    static Point[] edges;
    static List<Line> width = new ArrayList<>(), height = new ArrayList<>(); // 가로 세로
    static PriorityQueue<Line> holes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edges = new Point[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < N - 1; i++) {
            if (i % 2 == 0) height.add(new Line(edges[i], edges[i + 1]));
            else width.add(new Line(edges[i], edges[i + 1]));
        }
        getTotal();

        K = Integer.parseInt(br.readLine());
        holes = new PriorityQueue<>((a1, b1) -> a1.start.y - b1.start.y);
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            holes.add(new Line(
                    new Point(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())),
                    new Point(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())))
            );
        }
        int[] maxHeight = new int[width.size()];
        while (!holes.isEmpty()) {
            Line hole = holes.poll();
            int idx = getIdx(hole);
            // 기준
            total -= (hole.end.x - hole.start.x) * (hole.start.y - maxHeight[idx]);
            maxHeight[idx] = hole.start.y;
            // 왼쪽
            for(int i = idx - 1; i >= 0; i--) {
                Line next = width.get(i);
                int max = Math.min(maxHeight[i + 1], next.start.y);
                if(maxHeight[i] == next.start.y) break; // 물이 없는 곳이 있으면 break;
                total -= (next.end.x - next.start.x) * (max - maxHeight[i]);
                maxHeight[i] = Math.min(max, next.start.y);
            }
            // 오른쪽
            for(int i = idx + 1; i < width.size(); i++) {
                Line next = width.get(i);
                int max = Math.min(maxHeight[i - 1], next.start.y);
                if(maxHeight[i] == next.start.y) break; // 물이 없는 곳이 있으면 break;
                total -= (next.end.x - next.start.x) * (max - maxHeight[i]);
                maxHeight[i] = Math.min(max, next.start.y);
            }

        }
        System.out.println(total);
    }


    public static int getIdx(Line hole) {
        for (int i = 0; i < width.size(); i++) {
            Line cur = width.get(i);
            if (cur.start.x == hole.start.x && cur.start.y == hole.start.y && cur.end.x == hole.end.x && cur.end.y == hole.end.y)
                return i;
        }
        return -1;
    }


    public static void getTotal() {
        for (Line under : width) {
            total += (under.end.x - under.start.x) * under.start.y;
        }
    }


    static class Line {
        Point start, end;

        public Line(Point start, Point end) {
            if (start.x == end.x) { // 세로선
                if (start.y < end.y) {
                    this.start = start;
                    this.end = end;
                } else {
                    this.start = end;
                    this.end = start;
                }
            } else { // 가로선
                if (start.x < end.x) {
                    this.start = start;
                    this.end = end;
                } else {
                    this.start = end;
                    this.end = start;
                }
            }
        }
    }

    static class Point {
        int x, y; // 0 ~ 40000

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
