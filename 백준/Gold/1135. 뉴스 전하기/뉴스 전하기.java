
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
1. DFS로 자식 노드 부터 탐색
2. 자식노드에 도달하기까지의 시간을 리턴
3. 부모노드는 자식노드까지 걸린 시간을 내림차순 정렬하여 시간이 오래 걸리는 노드부터 먼저 탐색하도록 시간 부여
ex)
1 2 3 4 5 ... 초
3 3 3 2 1 ...
4 5 6 6 6 -> 해당 노드부터 아래로 최대 걸리는 시간은 6초
 */

public class Main {
    static int N;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }
        tree = new List[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num != -1) tree[num].add(i); // 자식 노드
        }

        System.out.println(dfs(0));

    }

    public static int dfs(int curIdx) {
        if (tree[curIdx].isEmpty()) {
            return 0;
        }

        List<Integer> childTime = new ArrayList<>();
        for (int child : tree[curIdx]) {
            childTime.add(dfs(child));
        }
        // 1. 내림 차순 정렬
        Collections.sort(childTime, Collections.reverseOrder());
        // 2. max 시간 계산
        int max = 0;
        for (int time = 1; time <= tree[curIdx].size(); time++) {
            max = Math.max(max, time + childTime.get(time - 1));
        }
        return max;
    }
}