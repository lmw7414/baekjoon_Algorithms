
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] parent;
    static Set<Integer> power = new HashSet<>();
    static Set<Integer> answer = new HashSet<>();
    static PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> a1.w - b1.w);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        // 발전소 입력
        st = new StringTokenizer(br.readLine());
        for(int k = 0; k < K; k++) {
            int p = Integer.parseInt(st.nextToken());
            power.add(p);
        }

        for(int m = 0; m < M; m++) {
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

        while(!pq.isEmpty()) {
            if(answer.size() == N) return cost;
            Edge cur = pq.poll();

            int rootA = find(cur.u);
            int rootB = find(cur.v);
            if(power.contains(rootA) && power.contains(rootB)) continue;

            if(rootA != rootB) {
                union(rootA, rootB);
                answer.add(cur.u);
                answer.add(cur.v);
                cost += cur.w;
            }
        }
        return cost;
    }


    public static void union(int rootA, int rootB) {
        if(power.contains(rootA)) parent[rootB] = rootA;
        else if(power.contains(rootB)) parent[rootA] = rootB;
        else parent[rootB] = rootA;
    }

    public static int find(int root) {
        if(parent[root] == root) return root;
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
