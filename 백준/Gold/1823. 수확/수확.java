import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(calc(0, N - 1, 1));
    }

    public static int calc(int left, int right, int depth) {
        if (left > right) return 0;
        if (dp[left][right] != 0) return dp[left][right];
        return dp[left][right] = Math.max(calc(left + 1, right, depth + 1) + arr[left] * depth, calc(left, right - 1, depth + 1) + arr[right] * depth);
    }

}