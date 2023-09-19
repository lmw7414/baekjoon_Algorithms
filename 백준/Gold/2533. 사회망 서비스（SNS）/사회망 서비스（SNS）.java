import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * 사회망에서 사람들의 친구 관계는 그래프로 표현할 수 있다.
 * 그래프에서 사람은 정점으로 표현, 엣지는 두 정점으로 표현되는 사람이 서로 친구관계임을 표현
 * 얼리어답터 : 새로운 아이디어를 먼저 받아들인 사람
 * 얼리어답터가 아닌 사람들은 자신의 모든 친구들이 얼리어답터일 때만 이 아이디어를 받아들인다.
 * 친구 관계 그래프가 트리인 경우만 고려
 * 친구 관계 트리가 주어졌을 때, 모든 개인이 새로운 아이디어를 수용하기 위하여 필요한 최소 얼리 어답터의 수를 구하는 프로그램을 작성해라
 * <p>
 * [문제 해결 프로세스]
 * 1번이 루트노드가 아닐 수 있다.
 * 노드 하나를 기준으로 놓고, 해당 노드가 얼리어답터인지, 아닌지 2가지 경우에 대해 생각해봐야함
 * 얼리어답터가 아닐경우 -> 주변 친구들은 모두 얼리어답터야 함
 * 얼리어답터일 경우 -> 주변친구들이 얼리어답터일 수도, 아닐수도 있음
 */


class Main {
    static int N;
    static List<Integer>[] adjList;
    static boolean[] visit;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjList = new List[N + 1];
        visit = new boolean[N + 1];
        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++)
            adjList[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList[A].add(B);
            adjList[B].add(A);
        }
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int node) {
        dp[node][0] = 1;
        visit[node] = true;
        for (int next : adjList[node]) {
            if (visit[next]) continue;
            dfs(next);
            dp[node][1] += dp[next][0];
            dp[node][0] += Math.min(dp[next][0], dp[next][1]);
        }
    }
}