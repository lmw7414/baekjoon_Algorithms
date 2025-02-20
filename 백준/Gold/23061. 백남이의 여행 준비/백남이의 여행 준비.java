import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Jewel> arr;
    static int[] bags;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        bags = new int[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(v == 0) continue;
            arr.add(new Jewel(w, v));
        }
        int maxSize = 0;
        for (int i = 0; i < M; i++) {
            bags[i] = Integer.parseInt(br.readLine());
            maxSize = Math.max(maxSize, bags[i]);
        }

        dp = new int[maxSize + 1];

        for (Jewel jewel : arr) {
            for (int total = maxSize; total >= jewel.w; total--) {
                dp[total] = Math.max(dp[total - jewel.w] + jewel.v, dp[total]);
            }
        }
        double max = -1;
        int answer = -1;
        for (int i = 1; i <= M; i++) {
            double value = (double) dp[bags[i - 1]] / bags[i - 1];
            if (max < value) {
                max = value;
                answer = i;
            }
        }
        System.out.println(answer);
    }

    static class Jewel {
        int w, v;

        public Jewel(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}