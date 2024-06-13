import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제 해결 프로세스]
 * 위상정렬로 접근
 */

public class Main {
    static int N, M;
    static Queue<Integer>[] graph;
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new Queue[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new PriorityQueue<>((a, b) -> a - b);
        }
        inDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            inDegree[b]++;
        }
        calc();
        System.out.println(sb.toString());
    }

    public static void calc() {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur + " ");

            for (int next : graph[cur]) {
                if (--inDegree[next] == 0) queue.add(next);
            }
        }
    }


}