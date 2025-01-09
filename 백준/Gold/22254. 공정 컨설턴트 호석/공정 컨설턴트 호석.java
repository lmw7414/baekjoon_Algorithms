import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [문제 해결 프로세스]
 */
public class Main {

    static int N, X;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());


        int left = 1;
        int right = 100001;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (calc(mid) <= X) { // 생산장비 줄이기
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    public static int calc(int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) pq.add(0);

        int time = 0;
        for (int i = 0; i < N; i++) {
            int cur = pq.poll();
            cur += arr[i];
            pq.add(cur);
            time = Math.max(cur, time);
        }
        return time;
    }


}