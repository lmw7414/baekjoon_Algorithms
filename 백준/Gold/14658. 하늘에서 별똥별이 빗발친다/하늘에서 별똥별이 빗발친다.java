import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, L, K;
    static List<int[]> star;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        star = new ArrayList<>();

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            star.add(new int[]{x, y});
        }

        for (int[] p : star) {
            int cnt = 0;
            for(int[] s : star) {
                cnt = cntStar(p[0], s[1], p[0] + L, s[1] + L);
                answer = Math.max(answer, cnt);
            }
        }

        System.out.println(K - answer);
    }

    public static int cntStar(int sx, int sy, int ex, int ey) {
        int result = 0;
        sx = Math.max(0, sx);
        sy = Math.max(0, sy);
        ex = Math.min(N, ex);
        ey = Math.min(M, ey);
        for (int[] p : star) {
            if (isInside(sx, sy, ex, ey, p[0], p[1])) result++;
        }
        return result;
    }

    public static boolean isInside(int sx, int sy, int ex, int ey, int targetX, int targetY) {
        return sx <= targetX && targetX <= ex && sy <= targetY && targetY <= ey;
    }

}