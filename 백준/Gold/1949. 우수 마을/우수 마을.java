import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제설명]
 * 우수마을로 선정된 마을 주민 수의 합을 최대로
 * 우수마을끼리는 인접해 있을 수 없음
 * 우수마을로 선정되지 못한 마을은 적어도 하나의 우수마을과 인접해 있어야 함
 * [문제 해결 프로세스]
 * dp[n][0] : n번 마을이 우수 마을이 아닐 때
 * dp[n][1] : n번 마을이 우수 마을일 때
 */


class Main {
    static int N;
    static int[] people;
    static List<Integer>[] adjList;
    static boolean[] visit;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N + 1];
        visit = new boolean[N + 1];
        adjList = new List[N + 1];
        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList[A].add(B);
            adjList[B].add(A);
        }
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int node) {
        dp[node][0] = people[node];
        visit[node] = true;

        for (int next : adjList[node]) {
            if (visit[next]) continue;
            dfs(next);
            dp[node][0] += dp[next][1];  // 자신이 우수마을일 때
            dp[node][1] += Math.max(dp[next][0], dp[next][1]);  // 자신이 우수마을이 아닐 때
        }
    }
}