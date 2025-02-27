import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        if (D <= T) { // 점프하는 시간이 걷는 것보다 느린 경우
            System.out.println(getDist(X, Y));
            return;
        }
        double dist = getDist(X, Y); // 도착지까지의 거리
        double time = 0;

        while (Double.compare(dist, 0.0) != 0) {

            // 원이 겹친다면
            if (dist <= 2 * D) {
                // 점프할 때 걸리는 시간 = 2 * T
                double t = Math.min((double)2 * T, Math.min(dist, T + Math.abs(dist - D)));  // 점프 + 걷기 or 걷기
                time += t;
                break;
            } else { // 원이 겹치지 않는다면
                time += T;
                dist -= D;
            }
        }
        System.out.println(time);
    }

    public static double getDist(int x, int y) {
        return Math.sqrt((x * x) + (y * y));
    }
}