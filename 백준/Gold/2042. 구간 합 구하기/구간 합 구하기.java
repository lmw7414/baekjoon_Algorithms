
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] segment;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int depth = findDepth(N);
        segment = new long[(int) Math.pow(2, depth + 1)];
        int startIdx = (int) Math.pow(2, depth);
        int endIdx = startIdx + N - 1;
        for (int i = startIdx; i <= endIdx; i++) {
            st = new StringTokenizer(br.readLine());
            segment[i] = Long.parseLong(st.nextToken());
        }
        init();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {  // b의 값을 c로 변경
                change(b, c, depth);
            } else if (a == 2) { // b부터 c까지 구간 합
                System.out.println(prefixSum(b, (int)c, depth));
            }
        }

    }

    public static long prefixSum(int startIdx, int endIdx, int depth) {
        long answer = 0;
        startIdx = (int) Math.pow(2, depth) + startIdx - 1;
        endIdx = (int) Math.pow(2, depth) + endIdx - 1;
        while (startIdx <= endIdx) {

            if (startIdx % 2 == 1)
                answer += segment[startIdx];
            if (endIdx % 2 == 0)
                answer += segment[endIdx];

            startIdx = (startIdx + 1) / 2;
            endIdx = (endIdx - 1) / 2;

            if(startIdx == endIdx) {
                return answer + segment[startIdx];
            }


        }
        return answer;
    }

    public static void change(int idx, long val, int depth) {
        idx = (int) Math.pow(2, depth) + idx - 1;
        segment[idx] = val;

        for (int i = 0; i < depth; i++) {
            idx /= 2;
            int left = idx * 2;
            int right = idx * 2 + 1;
            segment[idx] = segment[left] + segment[right];
        }
    }

    public static void init() {
        int idx = segment.length - 1;
        for (int i = idx; i/2 > 0; i --) {
            segment[i / 2] += segment[i];
        }
    }

    public static int findDepth(long N) {
        int k = 0;
        while (true) {
            if (Math.pow(2, k) >= N)
                return k;
            k++;
        }
    }
}


