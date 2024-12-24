
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, A, B, C;
    static List<Node>[] adjList;
    static int pride = Integer.MAX_VALUE; // 수치심
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];

        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if(weight > C) continue;

            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));
        }

        BS();
        if(pride != Integer.MAX_VALUE) System.out.println(pride);
        else System.out.println(-1);
    }

    public static void BS() {
        int left = 1;
        int right = 21;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(dijkstra(mid)) { // -> 수치심을 낮춰야함
                pride = mid;
                right = mid - 1;
            } else { // -> 수치심을 높여야함
                left = mid + 1;
            }
        }
    }

    // cost : 내가 생각하는 최소한의 수치심
    public static boolean dijkstra(int minPride) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>((a1, b1) -> a1.w - b1.w);
        dist[A] = 0;
        pq.add(new Node(A, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.v == B) return dist[cur.v] <= C;
            if(dist[cur.v] < cur.w) continue;

            for(Node next : adjList[cur.v]) {
                if(next.w > minPride) continue;
                int total = dist[cur.v] + next.w;
                if(total > C) continue;
                if(dist[next.v] < total) continue;
                dist[next.v] = total;
                pq.add(new Node(next.v, total));
            }
        }
        return false;
    }

    static class Node {
        int v, w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}