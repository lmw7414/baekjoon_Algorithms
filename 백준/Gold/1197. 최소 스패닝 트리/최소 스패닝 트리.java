
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static PriorityQueue<Edge> edges = new PriorityQueue<>((a1, b1) -> a1.w - b1.w);
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, e, w));
        }
        System.out.println(kruskal(N));
    }
    public static int kruskal(int N){
        int weight = 0;
        while(!edges.isEmpty()) {
            Edge cur = edges.poll();
            int root1 = find(cur.s);
            int root2 = find(cur.e);

            if(root1 != root2) {
                union(root1, root2);
                weight += cur.w;
            }
        }
        return weight;
    }
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[b] = a;
    }
    public static int find (int a){
        if(parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }
    static class Edge {
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}

