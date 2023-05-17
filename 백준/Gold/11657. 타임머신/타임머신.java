
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static ArrayList<Edge> edges;

    static final int INF = 999_999_999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.add(new Edge(s, e, w));
        }
        bellmanFord(N, M, 1);
    }

    static void bellmanFord(int N, int M, int start) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Edge cur = edges.get(j);
                if (dist[cur.s] != INF && dist[cur.e] > dist[cur.s] + cur.w) {
                    dist[cur.e] = dist[cur.s] + cur.w;
                }
            }
        }
        boolean flag = false;
        for (int j = 0; j < M; j++) {
            Edge cur = edges.get(j);
            if (dist[cur.s] != INF && dist[cur.e] > dist[cur.s] + cur.w) {
                flag = true;
                break;
            }
        }
        // 음수 간선이 없으면
        if (!flag) {
            for (int i = 2; i <= N; i++) {
                if (dist[i] != INF)
                    System.out.println(dist[i]);
                else
                    System.out.println(-1);
            }

        } else {  // 음수 간선이 있으면
            System.out.println(-1);
        }
    }
}

class Edge {
    int s;
    int e;
    int w;

    public Edge(int s, int e, int w) {
        this.s = s;
        this.e = e;
        this.w = w;
    }
}