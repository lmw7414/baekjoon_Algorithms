import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [문제 해결 프로세스]
 * 1. 가방 무게를 오름차순으로 정렬
 * 2. 보석 무게를 오름차순으로 정렬(같은 경우 가치가 높은 것이 우선)
 */
public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // jewel 무게순 오름차순 정렬
        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, value);
        }
        Arrays.sort(jewels);

        // 가방 오름차순 정렬
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && jewels[j].weight <= bags[i]) {
                pq.add(jewels[j++].value);
            }
            if (!pq.isEmpty()) answer += pq.poll();
        }
        System.out.println(answer);
    }

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
        @Override
        public int compareTo(Jewel o) {
            if (this.weight == o.weight) return o.value - this.value; // 무게가 같을 때 내림차순
            return this.weight - o.weight; // 오름차순
        }
    }

}