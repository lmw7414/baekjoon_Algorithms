
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static HashMap<Integer, List<Integer>> hm;
    static HashMap<Integer, List<Integer>> tree;
    static int[] subTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N, R, Q;
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        hm = new HashMap<>();
        tree = new HashMap<>();
        subTree = new int[N + 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u, v;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            if (hm.containsKey(u)) {
                hm.get(u).add(v);
            } else {
                hm.put(u, new LinkedList<>());
                hm.get(u).add(v);
            }

            if (hm.containsKey(v)) {
                hm.get(v).add(u);
            } else {
                hm.put(v, new LinkedList<>());
                hm.get(v).add(u);
            }
        }
        makeTree(N, R);
        findSubtree(N, R);

        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(subTree[q] + "\n");
        }
        System.out.println(sb);
    }

    public static void makeTree(int N, int R) {
        Queue<Integer> queue = new LinkedList<>();
        // init parent arr

        boolean[] visit = new boolean[N + 1];
        queue.add(R);
        visit[R] = true;
        tree.put(R, new LinkedList<>());
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : hm.get(current)) {
                if (!visit[next]) {
                    tree.get(current).add(next);
                    if(!tree.containsKey(next)) {
                        tree.put(next, new LinkedList<>());
                    }
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    public static void findSubtree(int N, int R) {
        subTree[R] = 1;

        for(int child : tree.get(R)) {
            findSubtree(N, child);
            subTree[R] += subTree[child];
        }
    }


}