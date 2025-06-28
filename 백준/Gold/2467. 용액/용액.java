
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int first, second;
    static int[] arr;
    static int ABS = Integer.MAX_VALUE;
    static List<Integer> negative = new ArrayList<>(), positive = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
            if (arr[n] >= 0) positive.add(arr[n]);
            else negative.add(arr[n]);
        }
        for(int n = 0; n < N - 1; n++) {
            int f = arr[n];
            int s = arr[n + 1];
            int abs = Math.abs(f + s);
            if (ABS > abs) {
                first = f;
                second = s;
                ABS = abs;
            }
        }
        if(!negative.isEmpty() && !positive.isEmpty()) {
            for (int n : negative) {
                int num = bs(-n);
                int abs = Math.abs(n + num);
                if (ABS > abs) {
                    first = n;
                    second = num;
                    ABS = abs;
                }
            }
        }
        System.out.println(first + " " + second);
    }

    public static int bs(int key) {
        int left = 0;
        int right = positive.size() - 1;
        int result = left;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (positive.get(mid) <= key) {
                result = mid;
                left = mid + 1;
            } else right = mid - 1;
        }

        if (key == positive.get(result)) return key;
        int abs1 = Math.abs(-key + positive.get(result));
        int abs2 = (result + 1 < positive.size()) ? Math.abs(-key + positive.get(result + 1)) : Integer.MAX_VALUE;
        if (abs1 < abs2) return positive.get(result);
        else return positive.get(result + 1);
    }
}