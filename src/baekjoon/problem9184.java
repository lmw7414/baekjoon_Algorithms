package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class problem9184 {

    static int[] arr = new int[3];
    static BufferedReader br;
    static StringBuffer sb;
    static int dp[][][];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuffer();
        dp = new int[51][51][51];
        while (!isEnd()) {
            printResult();
        }
        System.out.print(sb.toString());
    }

    public static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0)
            return 1;
        if (dp != null && dp[a][b][c] != 0) {
            return dp[a][b][c];
        } else if (a > 20 || b > 20 || c > 20)
            return dp[a][b][c] = w(20, 20, 20);
        else if (a < b && b < c)
            return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        else
            return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    }

    public static void printResult() {
        int answer;
        if (arr[0] > 0 && arr[1] > 0 && arr[2] > 0)
            answer = w(arr[0], arr[1], arr[2]);
        else
            answer = 1;
        sb.append("w(" + arr[0] + ", " + arr[1] + ", " + arr[2] + ") = " + answer + "\n");
    }

    public static boolean isEnd() throws IOException {
        String[] str;
        str = br.readLine().split(" ");

        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        if (Arrays.stream(arr).allMatch(value -> value == -1))
            return true;
        return false;
    }
}
