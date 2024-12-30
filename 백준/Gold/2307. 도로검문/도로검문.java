import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Edge>[] adjList;
    static List<Integer>[] way;
    static int INF = 98765432;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new List[N + 1];
        way = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            way[i] = new ArrayList<>();
            way[i].add(i);
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[a].add(new Edge(b, w));
            adjList[b].add(new Edge(a, w));
        }

        int[] dist = dijkstra();
        int minDist = dist[N];
        if(minDist == INF) {
            System.out.println(-1);
            System.exit(0);
        }
        int answer = 0;
        for(int i = 0; i < way[N].size() - 1; i++) {
            int a = way[N].get(i);
            int b = way[N].get(i + 1);
            int[] dist2 = dijkstra2(a, b);
            if(dist2[N] == INF) {
                System.out.println(-1);
                System.exit(0);
            }
            answer = Math.max(answer, dist2[N] - minDist);
        }

        System.out.println(answer);
    }
    //step1 1-> N까지 최단시간 계산(검문 없을 경우)
    public static int[] dijkstra() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> a1.w - b1.w);
        pq.add(new Edge(1, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(dist[cur.v] < cur.w) continue;

            for(Edge next : adjList[cur.v]) {
                int weight = dist[cur.v] + next.w;
                if(dist[next.v] < weight) continue;
                dist[next.v] = weight;
                pq.add(new Edge(next.v, weight));

                // 경로 추적
                way[next.v].clear();
                way[next.v].add(next.v);
                way[next.v].addAll(way[cur.v]);
            }
        }
        return dist;
    }
    //step2
    public static int[] dijkstra2(int a, int b) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> a1.w - b1.w);
        pq.add(new Edge(1, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(dist[cur.v] < cur.w) continue;

            for(Edge next : adjList[cur.v]) {
                if(cur.v == a && next.v == b) continue;
                if(cur.v == b && next.v == a) continue;
                int weight = dist[cur.v] + next.w;
                if(dist[next.v] < weight) continue;
                dist[next.v] = weight;
                pq.add(new Edge(next.v, weight));
            }
        }
        return dist;
    }

    static class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}