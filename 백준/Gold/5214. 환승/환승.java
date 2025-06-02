import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, M;
    static List<Integer>[] graph1, graph2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 10만
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph1 = new List[N + 1];
        graph2 = new List[M + 1];

        for (int i = 1; i <= N; i++) graph1[i] = new ArrayList<>();
        for(int i = 1; i <= M; i++) graph2[i] = new ArrayList<>();

        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();
            for (int k = 0; k < K; k++) list.add(Integer.parseInt(st.nextToken()));

            for (int num : list) {
                graph1[num].add(m); // 역 -> 튜브
                graph2[m].add(num);
            }
        }
        System.out.println(BFS());
    }

    public static int BFS() {
        boolean[] visitStation = new boolean[N + 1];
        boolean[] visitTube = new boolean[M + 1];
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(1, 1, true));
        visitStation[1] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if(cur.isPlatform && cur.platform == N) return cur.cnt;
            if (cur.isPlatform) {
                for (int nextTube : graph1[cur.platform]) {
                    if (visitTube[nextTube]) continue;
                    visitTube[nextTube] = true;
                    queue.add(new Node(nextTube, cur.cnt, false));
                }
            } else {
                for (int nextStation : graph2[cur.platform]) {
                    if (visitStation[nextStation]) continue;
                    if (nextStation == N) return cur.cnt + 1;
                    visitStation[nextStation] = true;
                    queue.add(new Node(nextStation, cur.cnt + 1, true));
                }
            }
        }
        return -1;
    }

    static class Node {
        int platform;
        int cnt;
        boolean isPlatform;

        public Node(int platform, int cnt, boolean isPlatform) {
            this.platform = platform;
            this.cnt = cnt;
            this.isPlatform = isPlatform;
        }
    }
}
