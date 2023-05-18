
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long[] segment;
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int depth = 1;
        while ((int) Math.pow(2, depth - 1) <= N)
            depth++;

        segment = new long[(int) Math.pow(2, depth)];
        Arrays.fill(segment, 1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            segment[(int) Math.pow(2, depth - 1) + i] = Long.parseLong(st.nextToken());
        }
        init((int) Math.pow(2, depth) - 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {  // b의 값을 c로 변경
                update(depth, b, c);
            } else if (a == 2) { // b부터 c까지 구간 곱
                System.out.println(calc(depth, b, c));

            }
        }
    }

    public static void init(int lastIdx) {
        int root = lastIdx / 2;
        for (int i = root; i > 0; i--) {
            long left = segment[i*2] % MOD;
            long right = segment[i * 2 + 1] % MOD;
            segment[i] = left * right;
            segment[i] %= MOD;
        }
    }

    public static void update(int depth, int idx, int val) {
        idx = (int) Math.pow(2, depth - 1) + idx - 1;
        segment[idx] = val;

        int root = idx / 2;

        for (int i = 1; i < depth; i++) {
            segment[root] = ((segment[root * 2] % MOD) * (segment[root * 2 + 1] % MOD)) % MOD;
            segment[root] %= MOD;
            root /= 2;
        }
    }

    public static long calc(int depth, int start, int end) {
        long answer = 1;
        start = (int) Math.pow(2, depth - 1) + start - 1;
        end = (int) Math.pow(2, depth - 1) + end - 1;

        while(start <= end) {
            if(start % 2 == 1) {
                answer = ((answer % MOD) * (segment[start++] % MOD)) % MOD;
                answer %= MOD;
            }
            if(end % 2 == 0){
                answer = ((answer % MOD) * (segment[end--] % MOD)) % MOD;
                answer %= MOD;
            }
            start /= 2;
            end /= 2;
        }
        return answer % MOD;
    }
}


