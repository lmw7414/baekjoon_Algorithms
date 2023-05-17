
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();
        visited = new boolean[N + 1];
        answer = new int[N + 1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        dfs(1);

        for (int i = 2; i <= N; i++)
            System.out.println(answer[i]);

    }

    public static void dfs(int root) {
        visited[root] = true;
        for (int node : graph[root]) {
            if (!visited[node]) {
                answer[node] = root;
                dfs(node);
            }

        }
    }
}

