import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제설명]
 * 네모 안의 숫자는 순서를 나타내고, 동그라미 안의 숫자는 노드 번호를 나타냄
 * 강의 근원인 노드의 순서는 1
 * 나머지 노드는 그 노드로 들어오는 강의 순서 중 가장 큰 값을 i라고 했을 때, 들어오는 모든 강 중에서 Strahler 순서가 i인 강이 1개이면 순서는 i, 2개 이상이면 i + 1
 * <p>
 * [입력]
 * T : 테스트 케이스
 * K : 테스트 케이스 번호, M : 노드의 수 P : 간선의 수
 * 간선의 정보 A, B (1 <=A,B<=M)
 * <p>
 * [문제 해결 프로세스]
 * 위상정렬로 접근
 */


class Main {
    static int K, M, P;
    static int[][] answer;  // 정답 저장할 배열
    static int[] in;  // 들어오는 노드의 수
    static List<Integer>[] adjList;  // 인접리스트
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken()); // 노드의 수
            P = Integer.parseInt(st.nextToken()); // 간선의 수
            answer = new int[M + 1][2];
            in = new int[M + 1];
            adjList = new List[M + 1];

            // 인접리스트 초기화
            for (int i = 1; i <= M; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());
                int A, B;
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                adjList[A].add(B);
                in[B]++;
            }
            sb.append(K + " " + calc() + "\n");
        }
        System.out.print(sb);
    }

    public static int calc() {
        int lastIdx = 0;
        Queue<Integer> pq = new ArrayDeque<>();
        for (int i = 1; i <= M; i++) {
            if (in[i] == 0) {
                pq.add(i);
                answer[i][0] = 1;
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            for (int next : adjList[cur]) {
                if (answer[cur][0] > answer[next][0]) {
                    answer[next][0] = answer[cur][0];
                    answer[next][1] = 1;
                } else if (answer[cur][0] == answer[next][0]) {
                    answer[next][1]++;
                }

                if (--in[next] == 0) {
                    if (answer[next][1] > 1) {
                        answer[next][0]++;
                        answer[next][1] = 0;
                    }
                    pq.add(next);
                }
            }
            lastIdx = cur;
        }
        return answer[lastIdx][0];
    }
}