import java.util.*;

class Solution {
    final static int INF = Integer.MAX_VALUE;
    static int[] visit;
    static HashMap<Integer,List<Integer>> hm = new HashMap<>();
    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer> answer = new ArrayList();
        visit = new int[n + 1];
        
        for(int[] road : roads) {
            if(!hm.containsKey(road[0])) {
                hm.put(road[0], new LinkedList());
                hm.get(road[0]).add(road[1]);
            } else {
                hm.get(road[0]).add(road[1]);
            }
            if(!hm.containsKey(road[1])) {
                hm.put(road[1], new LinkedList());
                hm.get(road[1]).add(road[0]);
            } else {
                hm.get(road[1]).add(road[0]);
            }
        }
        
        dijkstra(destination);
        for(int source : sources)
            answer.add((visit[source] == INF ? -1 : visit[source]));
        return answer.stream().mapToInt(i->i).toArray();
    }

    public static void dijkstra(int dest) {
        Arrays.fill(visit, INF);
        visit[dest] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        if(hm.containsKey(dest))
            queue.add(new Node(dest, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(int next : hm.get(node.end)) {
                if(visit[next] > node.time + 1) {
                    visit[next] = node.time + 1;
                    queue.add(new Node(next, node.time + 1));
                }
            }
        }
    }
}

class Node {
    int end;
    int time;
    public Node(int end, int time) {
        this.end = end;
        this.time = time;
    }
}