import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int min = 1;
        int max = arr[N - 1] - arr[0] + 1;
        int result = 0;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (install(mid) >= C) { // 간격을 늘려 줄여야 한다.
                result = mid;
                min = mid + 1;
            } else max = mid - 1;
        }
        System.out.println(result);
    }

    public static int install(int dist) {
        int cnt = 1;
        int place = arr[0];
        for (int i = 1; i < N; i++) {
            if (arr[i] - place >= dist) {
                cnt++;
                place = arr[i];
            }
        }
        return cnt;
    }

}