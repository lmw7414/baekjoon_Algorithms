
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            Node[] nodes = new Node[N + 1];
            List<Integer>[] adjList = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }
            for (int i = 1; i <= N; i++) {
                String[] str = br.readLine().split(" ");
                char room = str[0].charAt(0);
                int cost = Integer.parseInt(str[1]);
                int idx = 2;
                while (true) {
                    int to = Integer.parseInt(str[idx++]);
                    if (to == 0) break;
                    if (to == i) continue;
                    adjList[i].add(to);
                }
                nodes[i] = new Node(i, room, cost);
            }
            if (BFS(N, nodes, adjList)) sb.append("Yes\n");
            else sb.append("No\n");
        }
        System.out.print(sb);
    }

    public static boolean BFS(int N, Node[] nodes, List<Integer>[] adjList) {
        int[][] visit = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(visit[i], -1);
        if (nodes[1].room == 'T') return false;
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> b.balance - a.balance);
        pq.add(new State(1, 0));
        visit[1][1] = 0;

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            for (int next : adjList[cur.roomId]) {
                char type = nodes[next].room;
                int cost = nodes[next].cost;
                int nextCost = 0;
                if (type == 'E') { // 빈방
                    nextCost = cur.balance;
                } else if (type == 'L') { // 레프리콘
                    nextCost = Math.max(cost, cur.balance);
                } else { // 트롤
                    if (cur.balance < cost) continue;
                    nextCost = cur.balance - cost;
                }
                if (nodes[next].id == N) return true;
                if (visit[cur.roomId][next] >= nextCost) continue; // 이전에 방문한 적 있음
                visit[cur.roomId][next] = nextCost;
                pq.add(new State(next, nextCost));
            }
        }
        return false;
    }

    static class Node {
        int id;
        char room;
        int cost;

        public Node(int id, char room, int cost) {
            this.id = id;
            this.room = room;
            this.cost = cost;
        }
    }

    static class State {
        int roomId;
        int balance;

        public State(int roomId, int balance) {
            this.roomId = roomId;
            this.balance = balance;
        }
    }
}