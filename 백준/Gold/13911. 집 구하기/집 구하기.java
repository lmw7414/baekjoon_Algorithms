import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
- 집과 맥도날드 사이의 거리가 x 이하
- 집과 스타벅스 사이의 거리가 y 이하
- 최단거리의 합이 최소인 집
 */

public class Main {
    static int V, E, M, X, S, Y;
    static Set<Integer> macdonald = new HashSet<>(), starbucks = new HashSet<>();
    static List<Edge>[] adjList;
    public static int INF = 987654321;
    public static int answer = INF;
    public static void main(String[] args) throws IOException {
        input();
        int[] md = dijkstra(macdonald);
        int[] sb = dijkstra(starbucks);
        for(int i = 1; i <= V; i++) {
            if(macdonald.contains(i) || starbucks.contains(i)) continue;
            if(md[i] > X || sb[i] > Y) continue;
            answer = Math.min(answer, md[i] + sb[i]);
        }
        if(answer >= INF) System.out.println(-1);
        else System.out.println(answer);
    }

    public static int[] dijkstra(Set<Integer> set) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> a1.e - b1.e);
        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);
        for(int start : set) {
            dist[start] = 0;
            pq.add(new Edge(start, 0));
        }

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(cur.e > dist[cur.v]) continue;

            for(Edge next : adjList[cur.v]) {
                int distance = dist[cur.v] + next.e;
                if(distance >= dist[next.v]) continue;
                dist[next.v] = distance;
                pq.add(next);
            }
        }
        return dist;
    }


    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 최대 1만
        E = Integer.parseInt(st.nextToken()); // 최대 30만
        adjList = new List[V + 1];
        for(int i = 1; i <= V; i++) adjList[i] = new ArrayList<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new Edge(v, w));
            adjList[v].add(new Edge(u, w));
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            macdonald.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < S; i++) {
            starbucks.add(Integer.parseInt(st.nextToken()));
        }
    }

    static class Edge {
        int v, e;
        public Edge(int v, int e) {
            this.v = v;
            this.e = e;
        }
    }
}
