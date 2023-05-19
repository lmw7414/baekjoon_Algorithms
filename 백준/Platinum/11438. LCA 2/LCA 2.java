
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int kMax;
    static int[] depth;
    static int[][] parent = new int[21][1000001];
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        depth = new int[N + 1];
        for (int i = 1; i <= N; i++)
            tree[i] = new ArrayList<>();

        int temp = 1;
        kMax = 0;
        while (temp <= N) {
            temp <<= 1;
            kMax++;
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        initTree(N, 1);
        for (int k = 1; k <= kMax; k++) {
            for (int n = 1; n <= N; n++) {
                parent[k][n] = parent[k - 1][parent[k - 1][n]];
            }
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int answer = fastLCA(a, b);
            System.out.println(answer);
        }
    }

    private static int fastLCA(int a, int b) {
        // b의 depth가 더 깊게
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        // a, b 깊이 맞추기
        for (int k = kMax; k >= 0; k--) {
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                b = parent[k][b];

            }
        }
        // 동시에 올라가면서 조상 찾기
        for (int k = kMax; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        int LCA = a;
        if (a != b) {
            LCA = parent[0][LCA];
        }
        return LCA;
    }

    private static void initTree(int N, int root) {
        boolean[] visit = new boolean[N + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        visit[root] = true;
        parent[0][root] = root;
        depth[root] = 0;
        while (!queue.isEmpty()) {
            int p = queue.poll();

            for (int leaf : tree[p]) {
                if (!visit[leaf]) {
                    visit[leaf] = true;
                    queue.add(leaf);
                    parent[0][leaf] = p;
                    depth[leaf] = depth[p] + 1;
                }
            }
        }
    }

}
