import java.util.*;

/**
N 최대 5만개
경로 최대 천만
출입로 최대 5만개
출입로는 들어올때와 나갈때만 같은 지점으로 2번
산봉우리는 한번만

[문제 해결 프로세스]
BFS
- 한 출발지에서 시작 -> 방문할 수 있는 봉우리 방문
- intensity가 작은것 리턴
*/

class Solution {
    static Set<Integer> gate = new HashSet<>(), summit = new HashSet<>();
    static List<Edge>[] adjList;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {50001, 100000000};
        // 1. 그래프 생성
        adjList = new List[n + 1];
        for(int i = 1; i<= n; i++) adjList[i] = new ArrayList<>();
        for(int[] path : paths) {
            adjList[path[0]].add(new Edge(path[1], path[2]));
            adjList[path[1]].add(new Edge(path[0], path[2]));
        }
        // 2. SET 등록
        for(int g : gates) gate.add(g);
        for(int s: summits) summit.add(s);
        
        // 3. 방문체크를 위한 초기화        
        int[] visit = new int[n + 1];
        Arrays.fill(visit, Integer.MAX_VALUE);
        PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> a.v - b.v);
        for(int g : gates) {
            visit[g] = 0;
            queue.add(new Edge(g, 0));
        }
        
        // BFS
        while(!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(visit[cur.u] < cur.v) continue;
            
            for(Edge next : adjList[cur.u]) {
                if(gate.contains(next.u)) continue; // 다른 출입로인 경우
                int intensity = Math.max(cur.v, next.v);
                if(visit[next.u] <= intensity) continue; // 이전에 더 작은 강도로 도달한 적 있음
                visit[next.u] = intensity;
                if(summit.contains(next.u)) continue;
                queue.add(new Edge(next.u, visit[next.u]));
            }
        }
        Arrays.sort(summits);
        for (int s : summits) {
            if (visit[s] < answer[1]) {
                answer[0] = s;
                answer[1] = visit[s];
            }
        }
        return answer;
    }
    
}

class Edge {
    int u;
    int v;
    
    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
}