
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M, A, B;
    static long C;
    static List<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 최대 10만
        M = Integer.parseInt(st.nextToken()); // 최대 50만
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken()); // 최대 10^14
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        int left = Integer.MAX_VALUE; // 최소 수치심
        int right = 0; // 최대 수치심
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[s].add(new Edge(e, v));
            adjList[e].add(new Edge(s, v));
            left = Math.min(left, v);
            right = Math.max(right, v);
        }

        int cost = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (dijkstra(mid)) {
                cost = mid;
                right = mid - 1;
            } else left = mid + 1;
        }

        if (cost == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(cost);
    }

    public static boolean dijkstra(int limit) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[A] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a1, b1) -> Math.toIntExact(a1.cost - b1.cost));
        pq.add(new Node(A, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > dist[cur.node]) continue;

            for (Edge next : adjList[cur.node]) {
                if (next.v > limit) continue;
                long newCost = cur.cost + next.v;
                if (newCost >= dist[next.e]) continue; // 최소 비용이 아니라면
                if (newCost > C) continue; // 현재 돈으로 갈 수 없다면
                dist[next.e] = newCost;
                pq.add(new Node(next.e, newCost));
            }
        }
        return dist[B] != Long.MAX_VALUE;
    }

    static class Edge {
        int e, v;

        public Edge(int e, int v) {
            this.e = e;
            this.v = v;
        }
    }

    static class Node {
        int node;
        long cost;
        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }

}
