import java.io.*;
import java.util.*;

/*
[입력]
n | 최소: 2 최대: 십만

[주의사항]
- 프로젝트 팀원 수에는 제한이 없음
- 한 팀만 있을 수도 있음(모든 학생이 동일한 팀인 경우)
- 모든 사람이 한 사람을 선택하여 팀원 모집(혼자 팀하고 싶은 경우 자기 자신 선택 가능)
[문제 해결 프로세스]
1. 루프가 있어야 팀이 될 수 있음
    - 4 -> 7 -> 6 -> 4 ...
 */

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            int N = Integer.parseInt(br.readLine());
            parent = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                parent[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(calc(N)).append("\n");
        }
        System.out.print(sb);
    }

    public static int calc(int N) {
        boolean[] visit = new boolean[N + 1];
        int cnt = 0;

        // 팀 찾기
        for (int i = 1; i <= N; i++) {
            if (visit[i] || visit[parent[i]]) continue;
            cnt += findTeam(visit, i, parent[i]);
        }
        //System.out.println(N - cnt);
        return N - cnt;
    }

    // root: idx, cur: parent[root]
    // root == parent[root] -> 솔로팀 | 함수 들어가자마자 정해짐
    // root == parent[cur] -> 팀 생성 | find를 반복하다 찾아짐
    // cur == parent[cur] -> 팀 생성 불가
    public static int findTeam(boolean[] visit, int root, int cur) {
        Queue<Integer> queue = new ArrayDeque<>();
        visit[root] = true;
        if (root == parent[root]) return 1;
        int cnt = 1;
        queue.add(root);
        while (root != cur) {
            if (cur == parent[cur]) return 0;
            if (visit[cur]) {
                while (true) {
                    if (queue.isEmpty()) return 0;
                    if (queue.poll() == cur) return cnt;
                    else cnt--;
                }
            }
            visit[cur] = true;
            queue.add(cur);
            cnt++;
            cur = parent[cur];
        }
        return cnt;
    }

}