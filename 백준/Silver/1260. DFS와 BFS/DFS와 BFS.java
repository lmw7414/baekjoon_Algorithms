import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, START;
    static List<Integer>[] adjList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        START = Integer.parseInt(st.nextToken());

        adjList = new List[N + 1]; // 노드번호 1번부터 시작

        for (int i = 1; i <= N; i++)
            adjList[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            adjList[s].add(e);
            adjList[e].add(s);
        }
        for(int i = 1; i< adjList.length; i++)
            Collections.sort(adjList[i]);

        DFS(START);
        sb.append("\n");
        BFS(START);
        System.out.print(sb);
    }

    public static void DFS(int start) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visit = new boolean[N + 1];

        stack.push(start);
        visit[start] = true;
        sb.append(start).append(" ");
        while (!stack.isEmpty()) {
            int cur = stack.peek();
            boolean flag = false;
            for(int next : adjList[cur]) {
                if(visit[next]) continue;
                visit[next] = true;
                stack.push(next);
                sb.append(next).append(" ");
                flag = true;
                break;
            }
            if(!flag) stack.pop();
        }
    }

    public static void BFS(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 1];
        visit[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(" ");
            for(int next : adjList[cur]) {
                if(visit[next]) continue;
                visit[next] = true;
                queue.add(next);
            }
        }
    }

}