import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static final int INF = 100_000_00;
    static List<Edge>[] edgeList;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeList = new List[N + 1];  // 1번 노드부터 시작

        // 리스트 초기화
        for(int i = 1; i<= N; i++)
            edgeList[i] = new ArrayList<>();

        for(int i = 0; i< N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 양방향으로 저장
            edgeList[s].add(new Edge(e, w));
            edgeList[e].add(new Edge(s, w));
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            sb.append(findWay(start, dest)).append("\n");
        }
        System.out.print(sb);
    }

    public static int findWay(int start, int dest) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        distance[start] = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(Edge next : edgeList[cur]) {
                if(distance[next.next] > distance[cur] + next.w) {
                    distance[next.next] = distance[cur] + next.w;
                    queue.add(next.next);
                }
            }
        }
        return distance[dest];
    }
    static class Edge {
        int next;  // 연결된 노드
        int w;  // 가중치(거리)
        public Edge(int e, int w) {
            this.next = e;
            this.w = w;
        }
    }
}