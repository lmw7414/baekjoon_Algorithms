
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));

        }
        if (arr.size() == 1) {
            System.out.println(arr.get(0));
            return;
        }

        int[][] dp = new int[2][N];

        dp[0][0] = arr.get(0);
        dp[1][0] = 0;
        int answer = dp[0][0];
        for (int i = 1; i < N; i++) {
            dp[0][i] = Math.max(arr.get(i), dp[0][i - 1] + arr.get(i));
            dp[1][i] = Math.max(dp[1][i - 1] + arr.get(i), dp[0][i - 1]);
            answer = Math.max(answer, Math.max(dp[0][i], dp[1][i]));
        }

        System.out.println(answer);
    }

}