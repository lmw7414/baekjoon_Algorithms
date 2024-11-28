import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [입력]
 * N 최대 10만
 * M 최대 70만
 * [문제 해결 프로세스]
 * 1. 다익스트라로 접근
 * 2. 횡단보도의 주기를 알고 있어야 함.
 */

public class Main {
    static int N, M;
    static List<Node>[] adjList;
    static long INF = Long.MAX_VALUE;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //1. init
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
        dist = new long[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(new Node(end, i));
            adjList[end].add(new Node(start, i));
        }
        dijkstra();
        System.out.println(dist[N]);
    }

    public static void dijkstra() {
        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a1, b1) -> Math.toIntExact(a1.time - b1.time));
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.time > dist[cur.dest]) continue;

            for (Node cw : adjList[cur.dest]) {
                // 다음으로 이동할 곳의 도착 시간이 이미 크면 continue;
                long t = M * (cur.time / M) + cw.time;
                long nextTime = t < cur.time ? t + M : t; // 횡단보도 켜지는 시간
                if (nextTime + 1 < dist[cw.dest]) {
                    dist[cw.dest] = nextTime + 1;
                    pq.add(new Node(cw.dest, nextTime + 1));
                }
            }
        }
    }

    static class Node {
        int dest;
        long time;

        public Node(int dest, long time) {
            this.dest = dest;
            this.time = time;
        }
    }
}