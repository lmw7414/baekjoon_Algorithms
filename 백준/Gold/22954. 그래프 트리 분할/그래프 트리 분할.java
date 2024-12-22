import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 해결 프로세스
 * -1 인 경우
 * - 이미 트리가 3개 이상인 경우
 * - N이 2이하인 경우
 * 1. 주어진 그래프를 MST로 만들기
 * 2. MST에서 간선 하나 제거
 * 3. N1 == N2인지 확인
 */

public class Main {
    static int N, M;
    static int[] parent;
    static boolean[] visit;
    static List<Integer>[] temp, adjList;
    static Map<Edge, Integer> hm = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if (N <= 2) quit();

        temp = new List[N + 1];
        adjList = new List[N + 1];

        for(int n = 1; n <= N; n++) {
            temp[n] = new ArrayList<>();
            adjList[n] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            hm.put(new Edge(from, to), i);
            hm.put(new Edge(to, from), i);
            temp[from].add(to);
            temp[to].add(from);
        }
        initArr();

        // MST 만들기
        List<Integer> roots = new ArrayList<>();
        for (int n = 1; n <= N; n++) {
            if (visit[n]) continue;
            roots.add(n);
            DFS(n, n);
        }

        if (roots.size() == 1){
            // 2개로 나누기 -> 간선 한개 제거
            for(int n = 1;  n<= N; n++) {
                if(adjList[n].size() == 1) { // 말단 노드 간선 제거
                    int to = adjList[n].get(0);
                    adjList[n].remove(0);
                    adjList[to].remove(Integer.valueOf(n));

                    sb.append(1).append(" ").append(N-1).append("\n");
                    printAnswer(n);
                    printAnswer(to);
                    break;
                }
            }
        }
        else if (roots.size() == 2) {
            // N1 == N2 -> quit
            int n1 = 0;
            int n2 = 0;
            for(int i = 1; i <= N; i++) {
                if (roots.get(0) == find(i)) n1++;
                else n2++;
            }
            if(n1 == n2) quit();
            sb.append(n1).append(" ").append(n2).append("\n");
            for(int root : roots) printAnswer(root);
        }
        else quit();
        System.out.print(sb);
    }

    public static void DFS(int prev, int cur) {
        if (visit[cur]) return;
        visit[cur] = true;
        if(prev != cur) {
            adjList[prev].add(cur);
            adjList[cur].add(prev);
            parent[cur] = prev;
        }
        for (int next : temp[cur]) {
            DFS(cur, next);
        }
    }

    // 1. 트리에 속한 정점의 번호
    public static void printAnswer(int root) {
        Set<Integer> visit = new HashSet<>();
        Set<Edge> edges = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        visit.add(root);
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : adjList[cur]) {
                if(visit.contains(next)) continue;
                visit.add(next);
                edges.add(new Edge(cur, next));
                queue.add(next);
            }
        }
        for(int node : visit) sb.append(node).append(" ");
        sb.append("\n");
//        if (edges.isEmpty()) return;
        for(Edge edge : edges) sb.append(hm.get(edge)).append(" ");
        sb.append("\n");
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void initArr() {
        parent = new int[N + 1];
        visit = new boolean[N + 1];
        for (int n = 1; n <= N; n++) {
            parent[n] = n;
        }
    }

    public static void quit() {
        System.out.println(-1);
        System.exit(0);
    }

    static class Edge {
        int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}