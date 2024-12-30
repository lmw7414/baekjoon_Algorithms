import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr= new int[N + 1];
        dp = new long[N + 1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[x] : 현재까지 중에서 최대의 에너지
        int left = 0;
        int right = 1;
        long sum = 0;
        for(;right <= N; right++) {
            sum += arr[right];
            if(sum >= K) {
                while (sum >= K) {
                    dp[right] = Math.max(dp[right], Math.max(dp[right - 1], dp[left] + sum - K));
                    sum -= arr[++left];
                }
            } else {
                dp[right] = dp[right-1];
            }
        }

//        for(long i : dp) System.out.print(i + " ");
//        System.out.println();
        System.out.println(dp[N]);


    }
}