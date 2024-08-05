import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int answer;
    static int N, M, K;
    static int[][] dp;
    static int[][] arr;
    static List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[M + 1][N + 1];
        arr = new int[N + 1][N + 1];
        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            if (start > end) continue;
            if (arr[start][end] > value) continue;
            arr[start][end] = value;
            adj[start].add(new Node(end, value));
        }

        calc(1);
        for (int i = 2; i <= M; i++) answer = Math.max(answer, dp[i][N]);
        System.out.println(answer);
    }

    public static void calc(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (depth >= M) break;

            for (int s = 0; s < size; s++) {
                int cur = queue.poll();
                for (Node next : adj[cur]) {
                    if (dp[depth + 1][next.dest] < dp[depth][cur] + next.val) {  // 진행
                        dp[depth + 1][next.dest] = dp[depth][cur] + next.val;
                        queue.add(next.dest);
                    }
                }
            }
            // 다음 depth 증가
            depth++;
        }

    }

    static class Node {
        int dest;
        int val;

        public Node(int next, int val) {
            this.dest = next;
            this.val = val;
        }
    }
}