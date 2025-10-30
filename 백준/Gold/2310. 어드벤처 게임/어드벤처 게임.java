
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            List<Integer>[] adjList = new List[N + 1];
            int[] costs = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }
            for (int i = 1; i <= N; i++) {
                String[] str = br.readLine().split(" ");
                char room = str[0].charAt(0);
                int cost = Integer.parseInt(str[1]);
                if (room == 'T') cost *= -1;
                costs[i] = cost;
                int idx = 2;
                while (true) {
                    int to = Integer.parseInt(str[idx++]);
                    if (to == 0) break;
                    if (to == i) continue;
                    adjList[i].add(to);
                }
            }
            boolean[] visit = new boolean[N + 1];
            visit[1] = true;
            if (DFS(N, visit, costs, adjList, 1, 0)) sb.append("Yes\n");
            else sb.append("No\n");
        }
        System.out.print(sb);
    }

    public static boolean DFS(int N, boolean[] visit, int[] costs, List<Integer>[] adjList, int cur, int cost) {
        if (N == cur) return true;
        for (int next : adjList[cur]) {
            if (visit[next]) continue;

            int nextCost = 0;
            if (costs[next] > 0) nextCost = Math.max(cost, costs[next]);
            else nextCost = cost + costs[next];
            if (nextCost < 0) continue;
            visit[next] = true;
            if (DFS(N, visit, costs, adjList, next, nextCost)) return true;
            visit[next] = false;
        }
        return false;
    }
}