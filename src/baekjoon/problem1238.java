package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem1238 {

    static int INF = Integer.MAX_VALUE;
    static int N;
    static int M;
    static int X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        HashMap<Integer, List<Line>> map = new HashMap<>();
        for(int i = 0; i < M; i++) {
            int v1, v2, weight;

            st = new StringTokenizer(br.readLine());

            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            if(!map.containsKey(v1)) {
                map.put(v1, new ArrayList<>());
                map.get(v1).add(new Line(v1, v2, weight));
            } else {
                map.get(v1).add(new Line(v1, v2, weight));
            }
        }
        int[] toParty = new int[N + 1];
        int[] toHome = new int[N + 1];
        int[] answer = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            if(i == X)
                continue;
            toParty[i] = dijkstra(map, i, X);
        }

        for(int i = 1; i <= N; i++) {
            if(i == X)
                continue;
            toHome[i] = dijkstra(map, X, i);
        }

        for(int i = 1; i <= N; i++) {
            answer[i] = toParty[i] + toHome[i];
        }
        System.out.println(Arrays.stream(answer).max().getAsInt());


    }

    static int dijkstra(HashMap<Integer, List<Line>> map, int start, int dest) {
        int[] answer = new int[N+1];
        PriorityQueue<Line> pq = new PriorityQueue<>((a1, a2) -> a1.weight - a2.weight);

        Arrays.fill(answer, INF);
        answer[start] = 0;
        pq.add(new Line(start, start, 0));

        while(!pq.isEmpty()) {
            Line cur = pq.poll();

            for(Line v : map.get(cur.v2)) {
                if(answer[v.v2] > answer[v.v1] + v.weight) {
                    answer[v.v2] = answer[v.v1] + v.weight;
                    pq.add(v);
                }
            }

        }
        return answer[dest];
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
