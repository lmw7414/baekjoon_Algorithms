import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int total = 0;
    static PriorityQueue<Line> pq = new PriorityQueue<>((a,b)-> a.v - b.v);
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for(int i =1; i <= N; i++) parent[i] = i;

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char a = str.charAt(j);
                if(a != '0') {
                    if ('a' <= a && a <= 'z') {
                        if(i == j + 1) total += a - 96;
                        else {
                            pq.add(new Line(i, j+1, a - 96));
                            total += a - 96;
                        }
                    } else {
                        if(i == j + 1) total += a - 38;
                        else {
                            pq.add(new Line(i, j+1, a - 38));
                            total += a - 38;
                        }
                    }
                }
            }
        }
        int result = kruskal();
        if(result == -1) System.out.println(-1);
        else System.out.println(total - result);
    }

    // mst 반환
    public static int kruskal() {
        int result = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            Line line = pq.poll();
            if(union(line.s, line.e)) {
                result += line.v;
                cnt++;
            }
            if(cnt == N - 1) break;
        }
        if(cnt != N-1) return -1;
        return result;
    }
    public static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;
        if(rootA > rootB) parent[rootB] = rootA;
        else parent[rootA] = rootB;
        return true;
    }

    public static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static class Line {
        int s;
        int e;
        int v;
        public Line(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }
}