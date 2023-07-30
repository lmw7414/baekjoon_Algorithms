import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 누적 합[답 참고]
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 길이
        int M = Integer.parseInt(st.nextToken());  // 높이
        int[] down = new int[M + 2];
        int[] up = new int[M + 2];
        for (int i = 1; i <= N / 2; i++) {
            int d = Integer.parseInt(br.readLine());
            int u = M - Integer.parseInt(br.readLine()) + 1;
            down[d]++;
            up[u]++;
        }
        for (int i = 1; i <= M; i++)
            down[i] += down[i - 1];
        for (int i = M; i >= 1; i--)
            up[i] += up[i + 1];

        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for (int i = 1; i <= M; i++) {
            int conflict = (down[M] - down[i - 1]) + (up[1] - up[i + 1]);
            if (min > conflict) {
                min = conflict;
                cnt = 1;
            } else if (min == conflict) cnt++;
        }

        System.out.println(min + " " + cnt);
    }
}
