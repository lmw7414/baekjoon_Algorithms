import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static long[] prefix, arr;
    static Map<Integer, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            int num = Integer.parseInt(st.nextToken());
            if (hm.containsKey(num)) hm.replace(num, hm.get(num) + 1);
            else hm.put(num, 1);
        }

        prefix = new long[N + 1];
        arr = new long[N];
        //arr[0] = K;
        for (int key : hm.keySet()) {
            for (int i = 0; i < N; i+= key) {
                arr[i] += (long) hm.get(key);
            }
        }

        for (int i = 0; i < N; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            sb.append(prefix[R + 1] - prefix[L]).append("\n");
        }
        System.out.print(sb);

    }
}