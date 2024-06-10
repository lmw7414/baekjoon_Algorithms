import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 외판원 문제
 * 시작점은 어느 도시가 되든 비용이 동일하다는 것을 알고가자
 * 1 -> 2 -> 4 -> 3 -> 1
 * 2 -> 4 -> 3 -> 1 -> 2
 */
public class Main {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N];
        // 배열 초기화
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited[0] = true;
        DFS(0, 0, 0);
        System.out.println(answer);
    }

    public static void DFS(int cur, int depth, int cost) {
        if (depth == N - 1) {
            if (arr[cur][0] != 0)
                answer = Math.min(answer, cost + arr[cur][0]);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            if (i == cur) continue;
            if(arr[cur][i] == 0) continue;
            visited[i] = true;
            DFS(i, depth + 1, cost + arr[cur][i]);
            visited[i] = false;
        }

    }

}