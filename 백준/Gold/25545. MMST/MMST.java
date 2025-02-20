import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 최소 신장 트리 만들기
 * 2. 최대 신장 트리의 비용 계산
 */
public class Main {
    static int N, M;
    static Edge[] edges;
    static Set<Integer> minST, maxST;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if(M == N - 1) {
            System.out.println("NO");
            return;
        }

        edges = new Edge[M];
        minST = new HashSet<>();
        maxST = new HashSet<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[m] = new Edge(m + 1, u, v, w);
        }
        Arrays.sort(edges, (a, b) -> a.w - b.w);
        setMST(minST, true);
        setMST(maxST, false);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (minST.contains(edges[i].id)) continue;
            List<Integer> answer = setMMST(edges[i]);
            if (answer != null) {
                boolean flag = false;
                for (int n : answer) {
                    if (!maxST.contains(n)) {
                        flag = true;
                    }
                }
                if (!flag) continue;
                System.out.println("YES");
                for (int n : answer) sb.append(n).append(" ");
                System.out.print(sb);
                return;
            }
        }
        System.out.println("NO");
    }

    public static List<Integer> setMMST(Edge target) {
        List<Integer> list = new ArrayList<>();
        list.add(target.id);
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        union(parent, target.u, target.v);

        for (int i = 0; i < M; i++) {
            Edge cur = edges[i];
            if (target.id == cur.id) continue;
            if (find(parent, cur.u) != find(parent, cur.v)) {
                union(parent, cur.u, cur.v);
                list.add(cur.id);
            }
            if (list.size() == N - 1) return list;
        }
        return null;
    }

    public static void setMST(Set<Integer> set, boolean flag) {
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
        if (flag) {
            for (int i = 0; i < M; i++) {
                Edge cur = edges[i];
                if (find(parent, cur.u) != find(parent, cur.v)) {
                    union(parent, cur.u, cur.v);
                    set.add(cur.id);
                }
                if (set.size() == N - 1) break;
            }
        } else {
            for (int i = M - 1; i >= 0; i--) {
                Edge cur = edges[i];
                if (find(parent, cur.u) != find(parent, cur.v)) {
                    union(parent, cur.u, cur.v);
                    set.add(cur.id);
                }
                if (set.size() == N - 1) break;
            }
        }
    }

    public static void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootB != rootA) {
            parent[rootB] = rootA;
        }
    }

    public static int find(int[] parent, int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent, parent[a]);
    }

    static class Edge {
        int id;
        int u, v, w;

        public Edge(int id, int u, int v, int w) {
            this.id = id;
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}