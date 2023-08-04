import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i< N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();   //작은 숫자가 우선순위
        minHeap.add(Integer.MAX_VALUE);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(Integer.MIN_VALUE);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int input = arr[i];
            if (maxHeap.size() > minHeap.size()) {
                if (input > maxHeap.peek() && input < minHeap.peek()) {
                    minHeap.add(input);
                } else if (maxHeap.peek() >= input) {
                    maxHeap.add(input);
                    minHeap.add(maxHeap.poll());
                } else if (maxHeap.peek() < input) {
                    minHeap.add(input);
                }
            } else if (maxHeap.size() == minHeap.size()) {
                if (input > maxHeap.peek() && input <= minHeap.peek()) {
                    maxHeap.add(input);
                } else if (maxHeap.peek() >= input) {
                    maxHeap.add(input);
                } else if (maxHeap.peek() < input) {
                    minHeap.add(input);
                    maxHeap.add(minHeap.poll());
                }
            }
            sb.append(maxHeap.peek() + "\n");

        }
        System.out.print(sb);
    }
}
