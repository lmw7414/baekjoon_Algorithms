import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
[문제 설명]
1. 같은 레벨에 있는 노드는 같은 행에 위치
2. 한 열에는 한 노드만 존재
3. 각 열에는 하나의 노드가 반드시 존재
- 각 레벨의 너비 -> 가장 오른쪽 위치한 노드 - 가장 왼쪽에 위치한 노드 + 1
-> 너비가 가장 넓은 레벨과 그 레벨의 너비

[문제 해결 프로세스]
1. 각 노드의 자식노드 개수 구하기
2. 각 노드의 열 구하기
3. 가장 넓은 너비와 해당 행의 깊이 구하기
*/
public class Main {
    static int N;
    static HashMap<Integer, Node> tree = new HashMap<>();
    static int[] parent;
    static int[][] childCnt;
    static int[] dist;
    static int maxDepth = 1;
    static List<Integer>[] nodePerDepth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        childCnt = new int[N + 1][2];
        dist = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++)
            Arrays.fill(childCnt[i], -1);

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree.put(root, new Node(left, right));
            parent[root]++;
            if (left == -1) childCnt[root][0] = 0;
            if (right == -1) childCnt[root][1] = 0;

            if (left != -1) parent[left]++;
            if (right != -1) parent[right]++;
        }

        int root = findRoot();

        countChild(root, 1);
        nodePerDepth = new List[maxDepth + 1]; // 해당 depth의 노드의 열 기록
        for (int i = 1; i <= maxDepth; i++) nodePerDepth[i] = new ArrayList<Integer>();

        fillArr(1, N, root, 1);
        getResult();
    }

    // 부모 찾기 parent[i]의 값이 1이면 해당 인덱스가 루트 노드
    public static int findRoot() {
        for (int i = 1; i <= N; i++) {
            if (parent[i] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int countChild(int root, int depth) {
        maxDepth = Math.max(depth, maxDepth);
        if (tree.get(root).left != -1) childCnt[root][0] = countChild(tree.get(root).left, depth + 1);
        if (tree.get(root).right != -1) childCnt[root][1] = countChild(tree.get(root).right, depth + 1);
        return childCnt[root][0] + childCnt[root][1] + 1;
    }

    public static void fillArr(int start, int end, int root, int depth) {
        nodePerDepth[depth].add(start + childCnt[root][0]);
        if (tree.get(root).left != -1) fillArr(start, start + childCnt[root][0] - 1, tree.get(root).left, depth + 1);
        if (tree.get(root).right != -1) fillArr(start + childCnt[root][0] + 1, end, tree.get(root).right, depth + 1);
    }

    public static void getResult() {
        int idx = 0;
        int maxWidth = 0;
        for (int depth = 1; depth <= maxDepth; depth++) {
            List<Integer> cur = nodePerDepth[depth];
            int width = (cur.get(cur.size() - 1) - cur.get(0)) + 1;
            if (maxWidth < width) {
                idx = depth;
                maxWidth = width;
            }
        }
        System.out.println(idx + " " + maxWidth);
    }

    static class Node {
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}