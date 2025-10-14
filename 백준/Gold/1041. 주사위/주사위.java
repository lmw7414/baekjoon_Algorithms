
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr = new int[6];
    static int[] sides3 = new int[3];
    static int[] sides2 = new int[2];
    static int[] sides1 = new int[1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long A = Math.min(arr[0], arr[5]);
        long B = Math.min(arr[1], arr[4]);
        long C = Math.min(arr[2], arr[3]);

        // 제일 작은 면 1개
        long min1 = Math.min(Math.min(A, B), C);
        // 작은면 2개
        long min2 = Math.min(Math.min(A + B, A + C), B + C);
        // 면 3개
        long min3 = A + B + C;

        if (N == 1) {
            int sum = 0;
            int max = 0;
            for (int i = 0; i < 6; i++) {
                sum += arr[i];
                max = Math.max(max, arr[i]);
            }
            System.out.println(sum - max);
            return;
        }
        //면 3개짜리 - 4개, 면 2개짜리 8N - 12(: N-2 * 4 * 2 + 4), 면 1개짜리 (N - 2) * (N - 2) + (4N - 8 ) -> N*N - 4

        long answer = min3 * 4 + min2 * 4;
        if (N > 2) {
            answer += min2 * (8L * N - 16); // 2개짜리 면
            answer += min1 * ((long) (N - 2) * (N - 2) * 5 + (N - 2) * 4L); // 1개짜리 면
        }

        System.out.println(answer);
    }


}