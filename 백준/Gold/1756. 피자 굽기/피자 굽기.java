import java.util.*;
import java.io.*;

public class Main {
    static int D, N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int min = Integer.MAX_VALUE;
        arr = new int[D + 1];
        for (int i = 1; i <= D; i++) {
            int num = Integer.parseInt(st.nextToken());
            min = Math.min(num, min);
            arr[i] = min;
        }
        st = new StringTokenizer(br.readLine());

        int right = D;
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(st.nextToken());
            for (; right > 0; right--) {
                if (arr[right] >= num) {
                    arr[right] = 0;
                    break;
                }
            }
        }
        System.out.println(right);
    }
}