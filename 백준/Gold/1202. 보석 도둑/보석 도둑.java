import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 무게순 오름차순 정렬
        // 무게가 같다면 가격이 높은 것
        PriorityQueue<Jewel> pq = new PriorityQueue<>((a1, b1) -> {
            if (a1.M == b1.M) return b1.V - a1.V;
            return a1.M - b1.M;
        });

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            pq.add(new Jewel(M, V));
        }

        int[] bags = new int[K];
        for (int k = 0; k < K; k++) {
            bags[k] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        long answer = 0;
        PriorityQueue<Integer> bucket = new PriorityQueue<>(Comparator.reverseOrder());
        for (int k = 0; k < K; k++) {
            int bag = bags[k];
            while (!pq.isEmpty()) {
                if (pq.peek().M <= bag) {
                    bucket.add(pq.poll().V);
                } else break;
            }
            if(!bucket.isEmpty()) answer += bucket.poll();
        }

        System.out.println(answer);
    }

    static class Jewel {
        int M, V;

        public Jewel(int M, int V) {
            this.M = M;
            this.V = V;
        }
    }
}