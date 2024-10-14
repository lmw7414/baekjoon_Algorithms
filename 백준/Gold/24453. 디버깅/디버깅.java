import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, Y;
    static int[] arr;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        int errIdx = 0;
        for (int i = 0; i < M; i++) {
            if (arr[i] > 0 && arr[i] <= X) {
                queue.add(arr[i]);
                errIdx = i;
            } else break;
        }
        if (queue.size() >= Y) answer = Math.max(answer, M - queue.size());

        int left = 2;
        int right = X + 1;
        for (; right <= N; right++) {
            if (!queue.isEmpty() && queue.peek() < left) queue.poll();
            if (errIdx + 1 < arr.length && arr[errIdx + 1] <= right) queue.add(arr[++errIdx]);
            left++;
            answer = Math.max(answer, M - Math.max(queue.size(), Y));
        }
        System.out.println(answer);
    }
}