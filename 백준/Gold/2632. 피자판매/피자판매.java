import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int N, A, B;
    static int[] arr1, arr2;
    static Map<Integer, Integer> hm1, hm2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        A = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);
        arr1 = new int[A];
        arr2 = new int[B];
        hm1 = new HashMap<>();
        hm2 = new HashMap<>();
        hm1.put(0, 1);
        hm2.put(0, 1);
        int total = 0;
        for (int i = 0; i < A; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
            total += arr1[i];
        }

        hm1.put(total, 1);
        total = 0;
        for (int i = 0; i < B; i++) {
            arr2[i] = Integer.parseInt(br.readLine());
            total += arr2[i];
        }
        hm2.put(total, 1);
        makeHm(hm1, arr1, A);
        makeHm(hm2, arr2, B);

        int answer = 0;
        for (int a = 0; a <= N; a++) {
            int b = N - a;
            if (hm1.containsKey(a) && hm2.containsKey(b)) answer += hm1.get(a) * hm2.get(b);
        }
        System.out.println(answer);
    }

    public static void makeHm(Map<Integer, Integer> hm, int[] arr, int size) {
        for (int i = 0; i < size; i++) {
            int total = arr[i];
            if (hm.containsKey(total)) hm.put(total, hm.get(total) + 1);
            else hm.put(total, 1);

            for (int j = 1; j < size - 1; j++) {
                total += arr[(i + j) % size];
                if (total > N) break;
                if (hm.containsKey(total)) hm.put(total, hm.get(total) + 1);
                else hm.put(total, 1);
            }
        }
    }
}