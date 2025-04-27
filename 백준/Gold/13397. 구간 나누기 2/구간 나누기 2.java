
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int min = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        int answer = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (calc(mid)) {
                answer = mid;
                max = mid - 1;
            } else min = mid + 1;
        }
        System.out.println(answer);

    }

    public static boolean calc(int limit) {
        int min = arr[0];
        int max = arr[0];
        int sector = 1;
        for (int i = 1; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if (max - min > limit) { // 주어진 범위를 초과
                sector++;
                min = arr[i];
                max = arr[i];
            }
        }
        return sector <= M;
    }

}
