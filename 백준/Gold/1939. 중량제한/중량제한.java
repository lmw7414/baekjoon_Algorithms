import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int INF = 1087654321;
    static List<Edge>[] adjList;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new List[N + 1];
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[s].add(new Edge(e, v));
            adjList[e].add(new Edge(s, v));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dijkstra(start);
        System.out.println(dist[end]);
    }

    static public void dijkstra(int start) {
        dist[start] = INF;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> b1.w - a1.w);
        pq.add(new Edge(start, INF));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(cur.w < dist[cur.u]) continue;
            for (Edge edge : adjList[cur.u]) {
                int weight = Math.min(cur.w, edge.w);
                if (weight > dist[edge.u]) {
                    dist[edge.u] = weight;
                    pq.add(new Edge(edge.u, weight));
                }
            }
        }
    }

    static class Edge {
        int u, w;

        public Edge(int u, int w) {
            this.u = u;
            this.w = w;
        }
    }
}