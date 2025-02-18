import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 작은 수 부터 ~ 0까지
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 큰수 부터

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) maxHeap.add(num);
            else minHeap.add(num);
        }

        int answer = 0;
        while (true) {
            if (maxHeap.isEmpty()) break;
            int a = maxHeap.poll();
            if (maxHeap.isEmpty()) {
                answer += a;
                break;
            } else {
                int b = maxHeap.poll();
                answer += Math.max(a * b, a + b);
            }
        }

        while (true) {
            if (minHeap.isEmpty()) break;
            int a = minHeap.poll();
            if (minHeap.isEmpty()) {
                answer += a;
                break;
            } else {
                answer += a * minHeap.poll();
            }
        }

        System.out.println(answer);
    }


}