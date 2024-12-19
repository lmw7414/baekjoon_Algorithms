import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static long[] fox;
    static long[][] wolf;
    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fox = new long[N + 1];
        wolf = new long[2][N + 1];
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
        Arrays.fill(fox, Long.MAX_VALUE);
        Arrays.fill(wolf[0], Long.MAX_VALUE);
        Arrays.fill(wolf[1], Long.MAX_VALUE);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken()) * 2;
            adjList[u].add(new Node(v, w));
            adjList[v].add(new Node(u, w));
        }

        foxDijkstra(1);
        wolfDijkstra(1);

        int answer = 0;
        for (int i = 2; i <= N; i++) {
            if (fox[i] < Math.min(wolf[0][i],wolf[1][i])) answer++;
        }
        System.out.println(answer);
    }

    public static void foxDijkstra(int start) {
        fox[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a1, b1) -> Math.toIntExact(a1.w - b1.w));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (fox[cur.v] < cur.w) continue;

            for (Node next : adjList[cur.v]) {
                long weight = fox[cur.v] + next.w;
                if (fox[next.v] <= weight) continue;
                fox[next.v] = weight;
                pq.add(new Node(next.v, weight));
            }
        }
    }

    public static void wolfDijkstra(int start) {
        wolf[0][start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a1, b1) -> Math.toIntExact(a1.w - b1.w));
        pq.add(new Node(1, 0, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (wolf[cur.status][cur.v] < cur.w) continue;

            for (Node next : adjList[cur.v]) {
                int status = 1 - cur.status;
                long weight = 0;
                if (cur.status == 0) weight = wolf[cur.status][cur.v] + (next.w / 2);
                else weight = wolf[cur.status][cur.v] + (next.w * 2);

                if (wolf[status][next.v] <= weight) continue;
                wolf[status][next.v] = weight;
                pq.add(new Node(next.v, weight, status));
            }
        }
    }

    static class Node {
        int v;
        long w;
        int status;
        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }
        public Node(int v, long w, int status) {
            this.v = v;
            this.w = w;
            this.status = status;
        }
    }
}