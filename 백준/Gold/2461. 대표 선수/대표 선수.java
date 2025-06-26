
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M; // 최대 1000
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        // init
        int max = 0;
        for(int i = 0; i < N; i++) {
            Node node = new Node(i, arr[i][0], 0);
            max = Math.max(max, node.val);
            pq.add(node);
        }

        int answer = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            Node min = pq.poll();
            answer = Math.min(answer, max - min.val);
            if(min.idx + 1 >= M) break;
            Node next = new Node(min.n, arr[min.n][min.idx + 1], min.idx + 1);
            max = Math.max(max, next.val);
            pq.add(next);
        }
        System.out.println(answer);
    }

    static class Node {
        int n, val, idx;
        public Node(int n, int val, int idx) {
            this.n = n;
            this.val = val;
            this.idx = idx;
        }
    }
}