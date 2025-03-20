
import java.util.*;
import java.io.*;

/*
1. 위상정렬
2. 큐 활용
 */

public class Main {
    static int N;
    static List<Integer>[] bp, bp2;
    static int[] inDegree, answer, time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1. init Variable
        N = Integer.parseInt(br.readLine());
        bp = new List[N + 1];
        bp2 = new List[N + 1];
        inDegree = new int[N + 1];
        answer = new int[N + 1];
        time = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            bp[i] = new ArrayList<>();
            bp2[i] = new ArrayList<>();
        }

        for(int n = 0; n < N; n++) {
            String[] str = br.readLine().split(" ");
            time[n + 1] = Integer.parseInt(str[0]);
            for(int i = 1; i < str.length - 1; i++) {
                int priority = Integer.parseInt(str[i]);
                bp[n + 1].add(priority);
                bp2[priority].add(n + 1);
                inDegree[n + 1]++;
            }
        }
//        for(int i = 1; i <= N; i++) {
//            System.out.print(i + ": ");
//            for(int next : bp[i]) {
//                System.out.print(next + ", ");
//            }
//            System.out.println();
//        }
//
//        for(int i = 1; i <= N; i++) {
//            System.out.print(inDegree[i] + " ");
//        }
//        System.out.println();

        calc();
        for(int i = 1; i <= N; i++) System.out.println(answer[i]);

    }

    public static void calc() {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(inDegree[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            // 1. 먼저 지어진 건물 중 가장 늦게 지어진 건물의 시간 계산
            int max = 0;
            for(int next : bp[cur]) max = Math.max(max, answer[next]);

            // 2. + 해당 건물 짓는데 걸리는 시간 -> answer[queue.poll()]
            answer[cur] = max + time[cur];

            for(int next : bp2[cur]) {
                if(--inDegree[next] == 0) queue.add(next);             // 3. 지어졌으므로 해당 건물을 가지고 있는 다른 건물 indegree--, 0이면 queue에 넣기
            }
        }
    }
}