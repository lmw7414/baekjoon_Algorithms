import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] arr;
    static int visit;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 플로이드 워샬 - 모든 노드 쌍 간의 최단 경로를 구할 때 사용
        for (int k = 0; k < N; k++) { // 경유
            for (int i = 0; i < N; i++) { // 출발
                for (int j = 0; j < N; j++) { // 도착
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        visit = visit | (1 << K);
        calc(K, 0);
        System.out.println(answer);
    }

    public static void calc(int cur, int cost) {
        if (allVisited(visit)) {
            answer = Math.min(answer, cost);
            return;
        }
        for (int next = 0; next < N; next++) {
            if (cur == next) continue;
            if ((visit & (1 << next)) != 0) continue;
            visit = visit | (1 << next);
            calc(next, cost + arr[cur][next]);
            visit = visit & ~(1 << next);
        }
    }

    public static boolean allVisited(int num) {
        int all = (1 << N) - 1;
        return all == num;
    }
}