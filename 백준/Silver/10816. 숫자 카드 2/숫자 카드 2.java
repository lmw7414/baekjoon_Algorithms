
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tc; i++) {
            int key = Integer.parseInt(st.nextToken());
            int start = lowerBound(key);
            int end = upperBound(key);

            sb.append(end - start).append(" ");
        }
        System.out.println(sb);
    }

    private static int lowerBound(int key) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + ((right - left) / 2);

            if (key <= arr[mid]) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static int upperBound(int key) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + ((right - left) / 2);

            if (key < arr[mid]) right = mid;
            else left = mid + 1;
        }
        return left;
    }

}