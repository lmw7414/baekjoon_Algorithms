
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M;
    static int[] arr;
    static Map<Integer, Set<Integer>> out = new HashMap<>(), in = new HashMap<>();
    static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 최대 십만
        M = Integer.parseInt(st.nextToken()); // 최대 백만
        arr = new int[N + 1];
        degree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            out.put(i, new HashSet<>());
            in.put(i, new HashSet<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            out.get(s).add(e);
            in.get(e).add(s);
            degree[s]++;
        }
        calc();
        if (isCorrect()) {
            for (int i = 1; i <= N; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            System.out.println(-1);
        }

    }

    public static void calc() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int card = 1;
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) pq.add(i);
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            arr[cur] = card++;

            for (int next : in.get(cur)) {
                if (--degree[next] == 0) pq.add(next);
            }
        }
    }

    public static boolean isCorrect() {
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (arr[i] > arr[j]) {
                    if (!out.get(i).contains(j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}
