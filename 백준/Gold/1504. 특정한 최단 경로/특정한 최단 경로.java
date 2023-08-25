
import java.util.*;
import java.io.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int E;
    static int V1;
    static int V2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, List<Line>> map = new HashMap<>();
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < E; i++) {
            int v1, v2, weight;
            st = new StringTokenizer(br.readLine());

            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            if (!map.containsKey(v1)) {
                map.put(v1, new ArrayList());
                map.get(v1).add(new Line(v1, v2, weight));
            } else {
                map.get(v1).add(new Line(v1, v2, weight));
            }

            if (!map.containsKey(v2)) {
                map.put(v2, new ArrayList());
                map.get(v2).add(new Line(v2, v1, weight));
            } else {
                map.get(v2).add(new Line(v2, v1, weight));
            }

        }

        st = new StringTokenizer(br.readLine());

        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        int[] f = new int[N + 1];
        int[] s = new int[N + 1];
        int[] t = new int[N + 1];

        Arrays.fill(f, INF);
        Arrays.fill(s, INF);
        Arrays.fill(t, INF);
        dijkstra(map, f, 1);
        dijkstra(map, s, V1);
        dijkstra(map, t, V2);

        int c1;
        int c2;

        if (f[V1] == INF || s[V2] == INF || t[N] == INF) {
            c1 = INF;
        } else {
            c1 = f[V1] + s[V2] + t[N];
        }
        if (f[V2] == INF || t[V1] == INF || s[N] == INF) {
            c2 = INF;
        } else {
            c2 = f[V2] + t[V1] + s[N];
        }

        if (c1 == INF && c2 == INF)
            System.out.println(-1);
        else
            System.out.println(Math.min(c1, c2));
    }


    public static void dijkstra(HashMap<Integer, List<Line>> map, int answer[], int start) {
        PriorityQueue<Line> pq = new PriorityQueue<>((a1, a2) -> a1.weight - a2.weight);
        answer[start] = 0;

        pq.add(new Line(start, start, 0));

        while (!pq.isEmpty()) {
            Line current = pq.poll();
            if(!map.containsKey(current.v2))
                break;
            for (Line v : map.get(current.v2)) {
                if (answer[v.v2] > answer[v.v1] + v.weight) {
                    answer[v.v2] = answer[v.v1] + v.weight;
                    pq.add(v);
                }
            }
        }
    }

    static class Line {
        int v1;
        int v2;
        int weight;

        Line(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

}
