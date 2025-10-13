
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 건물의 개수 최대 1000
        M = Integer.parseInt(st.nextToken()); // 도로의 개수

        adjList = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int decline = Integer.parseInt(st.nextToken());
            adjList[from].add(new Edge(to, decline));
            adjList[to].add(new Edge(from, decline));
        }
        // 최소값
        int min = prim(1);
        // 최대값
        int max = prim(0);
        System.out.println(max - min);
    }

    // priority(경사): 뽑아야 하는 우선순위 0일 경우: 오르막길, 1일 경우: 내리막길
    public static int prim(int priority) {
        boolean[] visit = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> {
            if (priority == 0) return a.decline - b.decline;
            else return b.decline - a.decline;
        });
        visit[0] = true;
        pq.addAll(adjList[0]);

        int cnt = 0;
        int declineCnt = 0;
        while (!pq.isEmpty()) {
            if (cnt >= N) break;
            Edge cur = pq.poll();
            if (visit[cur.next]) continue;
            visit[cur.next] = true;
            cnt++;
            if (cur.decline == 0) declineCnt++;
            pq.addAll(adjList[cur.next]);
        }
        return declineCnt * declineCnt;
    }


    static class Edge {
        int next;
        int decline;

        public Edge(int next, int decline) {
            this.next = next;
            this.decline = decline;
        }
    }


}