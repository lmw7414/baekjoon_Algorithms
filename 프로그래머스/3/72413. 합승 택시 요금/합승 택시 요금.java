/*
1. S -> A -> B or S -> B -> A 로 가는 비용 중 최소 금액
*/
import java.util.*;

class Solution {
    static List<Edge>[] adjList;
    static int INF = 100000000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        initVar(n, fares);
        // S -> A
        int[] A = dijkstra(n, a);
        int[] B = dijkstra(n, b);
        int[] S = dijkstra(n, s);
        
        int answer = INF;
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, A[i] + B[i] + S[i]);
        }
        
        return answer;
    }
    
    public static int[] dijkstra(int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> a1.e - b1.e);
        pq.add(new Edge(start, 0));
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(cur.e > dist[cur.v]) continue;
            for(Edge next : adjList[cur.v]) {
                if(dist[cur.v] + next.e >= dist[next.v]) continue;
                dist[next.v] = dist[cur.v] + next.e;
                pq.add(next);
            }
        }
        return dist;
    }
    
    public static void initVar(int n, int[][] fares) {
        adjList = new List[n + 1];
        for(int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int[] fare : fares) {
            adjList[fare[0]].add(new Edge(fare[1], fare[2]));
            adjList[fare[1]].add(new Edge(fare[0], fare[2]));
        }
        
    }
    
    static class Edge {
        int v, e;
        public Edge(int v, int e) {
            this.v = v;
            this.e = e;
        }
    }
}