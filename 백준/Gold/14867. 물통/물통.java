
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
용량이 다른 두개의 물통
물통 이외에는 물의 양을 정확히 잴 수 있는 방법이 없음
Fx 물통 x에 물을 가득 채움
Ex 물통 x에 물을 모두 버림
Mxy x의 물을 y에 부음
  - 물통 x에 남아있는 물의 양이 물통 y에 남아 있는 빈 공간보다 적거나 같다면 물통 x의 물을 y에 부음
  - 그렇지 않다면, 다 붓고 나서 x에 남김
 */

public class Main {
    static int A, B, C, D;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        if(C == 0 && D == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        set.add(makeKey(0, 0));
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0,0,0));
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            for(int d = 0; d < 6; d++) {
                Point next = new Point();
                if(d == 0) {// a 채우기
                    next.x = A;
                    next.y = cur.y;
                    next.cnt = cur.cnt + 1;
                }
                else if(d == 1) { // b 채우기
                    next.x = cur.x;
                    next.y = B;
                    next.cnt = cur.cnt + 1;
                }
                else if(d == 2) {// a 비우기
                    next.x = 0;
                    next.y = cur.y;
                    next.cnt = cur.cnt + 1;
                }
                else if(d == 3) { // b 비우기
                    next.x = cur.x;
                    next.y = 0;
                    next.cnt = cur.cnt + 1;
                }
                else if(d == 4) { // a -> b
                    next.x = 0;
                    next.y += cur.y + cur.x;
                    if(next.y >= B) {
                        next.x = next.y - B;
                        next.y = B;
                    }
                    next.cnt = cur.cnt + 1;
                }
                else { // b -> a
                    next.y = 0;
                    next.x += cur.x + cur.y;
                    if(next.x >= A) {
                        next.y = next.x - A;
                        next.x = A;
                    }
                    next.cnt = cur.cnt + 1;
                }
                String key = makeKey(next.x, next.y);
                if(set.contains(key)) continue;
                set.add(key);
                if(next.x == C && next.y == D) return next.cnt;
                queue.add(next);
            }
        }
        return -1;
    }

    public static String makeKey(int a, int b) {
        return a + "|" + b;
    }

    static class Point {
        int x, y;
        int cnt;
        public Point() {}
        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

}