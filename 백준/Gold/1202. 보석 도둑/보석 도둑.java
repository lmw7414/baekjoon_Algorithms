import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 가방 오름차순
// 보석 무게 오름차순 가치는 내림차순

public class Main {
    static int N, K;
    static PriorityQueue<Jewel> jewels = new PriorityQueue<>((a1, b1) -> {
        if (a1.weight == b1.weight) return b1.value - a1.value;
        return a1.weight - b1.weight;
    });
    static PriorityQueue<Integer> bags = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(m, v));
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bags.add(Integer.parseInt(st.nextToken()));
        }
        long answer = 0;
        PriorityQueue<Integer> temp = new PriorityQueue<>(Comparator.reverseOrder());
        while (!bags.isEmpty()) {
            int bag = bags.poll();

            while (!jewels.isEmpty()) {
                if (jewels.peek().weight <= bag) {
                    temp.add(jewels.poll().value);
                } else break;
            }
            if (!temp.isEmpty()) answer += temp.poll();
        }
        System.out.println(answer);
    }


    static class Jewel {
        int weight, value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}