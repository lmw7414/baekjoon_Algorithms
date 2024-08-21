import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
[문제 해결 프로세스]
1. 트리 세팅
2. 선택된 노드의 부모 찾기
3. 선택된 노드 중 depth가 깊은 노드를 먼저 찾기
4. depth를 올려 동일한 노드인지 확인
5. 다르다면 같이 올리면서 공통된 부모가 나올때까지 올리기
*/
public class Main {
    static StringBuilder sb = new StringBuilder();
    static long N;
    static List<Long> dp = new ArrayList<>();
    static int K, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        if (K != 1) init();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            long left = Long.parseLong(st.nextToken());
            long right = Long.parseLong(st.nextToken());

            if (K == 1) sb.append(Math.abs(right - left)).append("\n");
            else sb.append(LCA(left, right)).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void init() {
        dp.add(1L);
        long last = 1L;
        int depth = 1;
        while (last < N) {
            last += (long) Math.pow(K, depth++);
            dp.add(last);
        }
    }

    public static int findDepth(long num) {
        if (num == 1) return 0;
        for (int idx = dp.size() - 1; idx >= 0; idx--) {
            if (dp.get(idx) < num) {
                return idx + 1;
            }
        }
        return -1;
    }

    /*
    항상 left <= right
    */
    public static int LCA(long left, long right) {
        int dist = 0;
        if (left > right) {
            long temp = left;
            left = right;
            right = temp;
        }
        int leftDepth = findDepth(left);
        int rightDepth = findDepth(right);

        // right 깊이 조정
        while (leftDepth < rightDepth) {
            right = findParent(rightDepth, right);
            rightDepth--;
            dist++;
        }
        while (left != right) {
            if (left == right) return dist;
            left = findParent(leftDepth--, left);
            right = findParent(rightDepth--, right);
            dist += 2;
        }
        return dist;
    }

    /* 한 depth 위의 노드 전달
     * depth : 현재 노드의 depth
     * num : 현재 노드의 값
     */
    public static long findParent(int depth, long num) {
        if (dp.get(depth - 1) == 1) return 1;
        long start = dp.get(depth - 1) + 1;
        long order = (num - start) / K;
        return dp.get(depth - 2) + order + 1;
    }
}