import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제설명]
 * 특정한 최단 거리 구하기
 * 임의의 주어진 두 정점은 반드시 통과해야한다.
 * - case1 : 1 -> A -> B -> N
 * - case2 : 1 -> B -> A -> N
 * 1부터 특정 정점을 거친 후 N까지 가는 길이 없을 경우 -1
 * <p>
 * [문제 해결 프로세스]
 * 1번 부터 특정 정점까지 가야하는 최단 경로를 계산해야 한다. -> 다익스트라
 * 다익스트라는 하나의 정점으로 부터 모든 정점까지의 최단 거리를 알 수 있다.
 * 두가지 경우의 수를 고려해야함. 위의 case 2개
 * 1. 1 부터 A까지 최단 거리 계산
 * 2. A 부터 B까지 최단 거리 계산
 * 3. B 부터 N까지 최단 거리 계산
 * 4. 위에서 1 부터 특정 정점을 거쳐 N까지 가는 최단 거리를 변수에 저장해 놓는다.
 * <p>
 * 5. 1 부터 B까지 최단 거리 계산
 * 6. B 부터 A까지 최단 거리 계산
 * 7. A 부터 N까지 최단 거리 계산
 * 8. 위에서 1 부터 특정 정점을 거쳐 N까지 가는 최단 거리를 변수에 저장해 놓는다.
 * <p>
 * [다시 정리]
 * 1. 1번에서 갈 수 있는 모든 정점의 최단거리 구하기
 * 2. A에서 갈 수 있는 모든 정점의 최단 거리 구하기
 * 3. B에서 갈 수 있는 모든 정점의 최단 거리 구하기
 * -> 총 다익스트라 메서드를 3번 호출해야함
 */
public class Main {
    static int N, E;
    static List<Edge>[] adjList;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[s].add(new Edge(s, e, w));
            adjList[e].add(new Edge(e, s, w));
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());  // 특정 정점1
        int B = Integer.parseInt(st.nextToken());  // 특정 정점2


        // 1. 1번에서 갈 수 있는 모든 정점의 최단거리 구하기
        int[] first = dijkstra(1);
        // 2. A에서 갈 수 있는 모든 정점의 최단 거리 구하기
        int[] second = dijkstra(A);
        // 3. B에서 갈 수 있는 모든 정점의 최단 거리 구하기
        int[] third = dijkstra(B);

        int case1 = 0;
        int case2 = 0;
        // 1 -> A -> B -> N
        if (first[A] == INF || second[B] == INF || third[N] == INF) case1 = INF;
        else case1 = first[A] + second[B] + third[N];
        // 1 -> B -> A -> N
        if (first[B] == INF || second[A] == INF || third[N] == INF) case2 = INF;
        else case2 = first[B] + third[A] + second[N];

        if (case1 == INF && case2 == INF) System.out.println(-1);
        else System.out.println(Math.min(case1, case2));
    }

    public static int[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> a1.w - b1.w);
        int[] distances = new int[N + 1];
        Arrays.fill(distances, INF);

        distances[start] = 0;
        pq.add(new Edge(start, start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            for (Edge edge : adjList[cur.e]) {
                if (distances[edge.e] > distances[edge.s] + edge.w) {
                    distances[edge.e] = distances[edge.s] + edge.w;
                    pq.add(edge);
                }
            }
        }


        return distances;
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