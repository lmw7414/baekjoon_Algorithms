import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> a1.w - b1.w);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        // 발전소 입력
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            int p = Integer.parseInt(st.nextToken());
            pq.add(new Edge(p, 0, 0));
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, w));
        }
        System.out.println(kruskal());
    }

    public static int kruskal() {
        int cost = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            int rootA = find(cur.u);
            int rootB = find(cur.v);

            if (rootA != rootB) {
                union(rootA, rootB);
                cost += cur.w;
            }
        }
        return cost;
    }


    public static void union(int rootA, int rootB) {
        parent[rootB] = rootA;
    }

    public static int find(int root) {
        if (parent[root] == root) return root;
        return parent[root] = find(parent[root]);
    }


    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
