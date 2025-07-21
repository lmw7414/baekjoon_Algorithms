
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;
        PriorityQueue<Line> pq = new PriorityQueue<>((a, b) -> {
           if(a.x1 == b.x1) return a.x2 - b.x2;
           return a.x1 - b.x1;
        });
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Line(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        while(!pq.isEmpty()) {
            Line cur = pq.poll();
            if(pq.isEmpty()) break;
            Line next = pq.peek();
            if(cur.x1 <= next.x1 && next.x1 <= cur.x2) {
                union(cur.id, next.id);
            }
        }
        StringBuilder sb= new StringBuilder();
        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b =Integer.parseInt(st.nextToken());
            if(find(a) == find(b)) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        System.out.print(sb);

    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;
        parent[rootB] = rootA;
    }

    public static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static class Line {
        int id, x1, x2, y;
        public Line(int id, int x1, int x2, int y) {
            this.id = id;
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;;
        }
    }

}