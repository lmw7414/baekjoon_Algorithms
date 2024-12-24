import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, A, B, C;
    static List<Node>[] adjList;
    static int pride = Integer.MAX_VALUE; // 수치심
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visit = new boolean[N + 1];
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));
        }

        calc(A, 0, 0);
        if(pride != Integer.MAX_VALUE) System.out.println(pride);
        else System.out.println(-1);
    }


    // 최대 비용이 최소가 되도록
    public static void calc(int cur, int maxCost, int curCost) {
        if (curCost > C) return;
        if (cur == B) {
            pride = Math.min(pride, maxCost);
            return;
        }
        visit[cur] = true;
        for (Node next : adjList[cur]) {
            if (visit[next.v]) continue;
            calc(next.v, Math.max(maxCost, next.w), curCost + next.w);
        }
    }

    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}