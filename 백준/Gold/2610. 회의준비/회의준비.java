
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. 서로 알고 있는 사람은 반드시 같은 위원회에 속해야 함
2. 효율적인 회의 진행을 위해 위원회의 수는 최대가 되어야 함
3. 참석자가 자신의 의견을 전달하기 위해서는 아는 사람을 통해 전달해야 함. -> 따라서 최소의 전달로 대표자에게 까지 전달해야 함
4. 거치는 사람의 수를 의사전달 시간
-> 모든 참석자들의 의사전달 시간 중 최댓값이  최소가 되도록 대표를 정하는 프로그램을 작성해야함
 */

public class Main {
    static int N, M;
    static int K;
    static List<Integer>[] adjList;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 회의에 참석하는 사람의 수
        M = Integer.parseInt(br.readLine()); // 관계의 수
        adjList = new List[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }
        PriorityQueue<Integer> answer = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (visit[i]) continue;
            K++;
            Set<Integer> group = new HashSet<>();
            BFS(group, i);
            answer.add(getAnswer(group));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(K).append("\n");
        while (!answer.isEmpty()) {
            sb.append(answer.poll()).append("\n");
        }
        System.out.print(sb);
    }

    public static void BFS(Set<Integer> group, int start) {
        visit[start] = true;
        group.add(start);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : adjList[cur]) {
                if (visit[next]) continue;
                visit[next] = true;
                queue.add(next);
                group.add(next);
            }
        }
    }

    public static int getAnswer(Set<Integer> group) {
        int result = -1;
        int max = Integer.MAX_VALUE;
        for (int idx : group) {
            int depth = getDepth(idx);
            if (max > depth) {
                result = idx;
                max = depth;
            }
        }
        return result;
    }

    public static int getDepth(int start) {
        Set<Integer> visit = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visit.add(start);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int next : adjList[cur]) {
                    if (visit.contains(next)) continue;
                    queue.add(next);
                    visit.add(next);
                }
            }
            depth++;
        }
        return depth;
    }

}
