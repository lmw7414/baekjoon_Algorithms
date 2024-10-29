import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int P, Q;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        arr = new int[Q + 2];
        dp = new int[Q + 2][Q + 2];
        for (int i = 0; i <= Q + 1; i++) Arrays.fill(dp[i], -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= Q; i++) arr[i] = Integer.parseInt(st.nextToken());
        arr[Q + 1] = P + 1;
        Arrays.sort(arr);

        int answer = calc(0, Q + 1);
        System.out.println(answer);
    }

    public static int calc(int left, int right) {
        if (left + 1 >= right) return 0;
        if (dp[left][right] != -1) return dp[left][right];

        dp[left][right] = Integer.MAX_VALUE;

        for (int idx = left + 1; idx < right; idx++) {
            dp[left][right] = Math.min(dp[left][right], calc(left, idx) + calc(idx, right) + (arr[right] - arr[left] - 2));
        }
        return dp[left][right];
    }

}