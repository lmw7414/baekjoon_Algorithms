
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int T, N, M;
    static int[] A, B;
    static Map<Integer, Integer> hm1, hm2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        String[] str1 = br.readLine().split(" ");
        M = Integer.parseInt(br.readLine());
        String[] str2 = br.readLine().split(" ");
        A = new int[N];
        B = new int[M];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(str1[i]);
        }
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(str2[i]);
        }

        hm1 = new HashMap<>();
        hm2 = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = 0;
            for (int j = i; j < N; j++) {
                num += A[j];
                if (hm1.containsKey(num)) hm1.put(num, hm1.get(num) + 1);
                else hm1.put(num, 1);
            }
        }

        for (int i = 0; i < M; i++) {
            int num = 0;
            for (int j = i; j < M; j++) {
                num += B[j];
                if (hm2.containsKey(num)) hm2.put(num, hm2.get(num) + 1);
                else hm2.put(num, 1);
            }
        }

        long answer = 0;
        for (int a : hm1.keySet()) {
            int rest = T - a;
            if (hm2.containsKey(rest)) answer += (long) hm1.get(a) * hm2.get(rest);
        }
        System.out.println(answer);
    }
}