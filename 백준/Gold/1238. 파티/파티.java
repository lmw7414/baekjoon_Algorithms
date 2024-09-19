import java.util.*;
import java.io.*;

public class Main {
    static int N, M, X;
    static int INF = 987654321;
    static List<Edge>[] adjList1, adjList2; // 수열의 길이를 저장하기 위함
    static int[] goParty, toHome;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        adjList1 = new List[N + 1];
        adjList2 = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList1[i] = new ArrayList<>();
            adjList2[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList1[start].add(new Edge(end, weight));
            adjList2[end].add(new Edge(start, weight));
        }

        goParty = dijkstra(adjList1, X);
        toHome = dijkstra(adjList2, X);

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (i == X) continue;
            max = Math.max(max, goParty[i] + toHome[i]);
        }
        System.out.println(max);
    }

    public static int[] dijkstra(List<Edge>[] adjList, int start) {
        int[] dist = initDistArr(start);
        PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> a1.w - b1.w);
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.w > dist[cur.u]) continue;
            for (Edge edge : adjList[cur.u]) {
                if (dist[edge.u] <= dist[cur.u] + edge.w) continue;
                dist[edge.u] = dist[cur.u] + edge.w;
                pq.add(new Edge(edge.u, dist[cur.u] + edge.w));
            }
        }
        return dist;
    }

    public static int[] initDistArr(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        return dist;
    }

    static class Edge {
        int u, w;

        public Edge(int u, int w) {
            this.u = u;
            this.w = w;
        }
    }
}