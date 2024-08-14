import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [조건]
 * N 최대 10만
 * h, o의 값은 최대 -1억 ~ 1억
 * D 최대 2억
 * <p>
 * [문제 해결 프로세스]
 * 1. 정렬 필요
 * 2. O(n^2)는 불가능
 */

public class Main {
    static int N, D;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(start, end);
            arr[i][1] = Math.max(start, end);
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for (int i = 0; i < N; i++) {
            pq.add(arr[i][0]); // 시작 점 저장
            while (!pq.isEmpty() && pq.peek() < arr[i][1] - D) pq.poll();
            answer = Math.max(answer, pq.size());
        }
        System.out.println(answer);
    }
}