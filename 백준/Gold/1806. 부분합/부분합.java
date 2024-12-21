import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        int answer = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());

        // 1. 누적합
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num >= S) {
                System.out.println(1);
                System.exit(0);
            }
            arr[i] = arr[i - 1] + num;
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        while (true) {
            if (sum < S) {
                if (right >= N) break;
                right++;
            } else {
                if (left >= N) break;
                left++;
            }
            sum = arr[right] - arr[left];

            if (sum >= S) {
                if (answer > right - left) {
                    answer = right - left;
                }
            }
        }
        if (answer == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(answer);
    }
}