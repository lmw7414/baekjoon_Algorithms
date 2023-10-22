import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제 해결 프로세스]
 * 위상정렬로 접근.
 * 입력을 받을 때 자신 기준 들어오는 노드의 개수를 카운트하는 배열 추가
 * 건물이 다 지어졌을 경우 다음 건물에 위의 배열 인덱스에 -1
 * 0이 되면 해당 건물 짓기 시작
 * 각 정답 배열을 가지고 해당 인덱스에는 누적 시간 저장하기
 */

public class Main {
    static int N, K, Dest;
    static int[] Buildings;  // 건설시간을 담을 배열
    static int[] comes; // 자신으로 들어오는 in-degrees
    static int[] result; // 누적 시간을 저장할 배열
    static List<Integer>[] adjList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트 개수
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            Buildings = new int[N + 1];
            adjList = new List[N + 1];
            comes = new int[N + 1];
            result = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                Buildings[i] = Integer.parseInt(st.nextToken());
                result[i] = Buildings[i];
                adjList[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adjList[X].add(Y);
                comes[Y]++;
            }

            st = new StringTokenizer(br.readLine());
            Dest = Integer.parseInt(st.nextToken());
            calc();
            sb.append(result[Dest]).append("\n");
        }
        System.out.print(sb);
    }

    public static void calc() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (comes[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : adjList[cur]) {
                comes[next]--;
                result[next] = Math.max(result[next], Buildings[next] + result[cur]);
                if (comes[next] == 0) queue.add(next);
            }
        }
    }

}