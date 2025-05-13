import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static List<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }

        List<Integer> LIS = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (LIS.isEmpty() || LIS.get(LIS.size() - 1) < arr.get(i)) {
                LIS.add(arr.get(i));
            } else {
                int idx = getIdx(LIS, arr.get(i));
                LIS.set(idx, arr.get(i));
            }
        }
        System.out.println(N - LIS.size());
    }

    public static int getIdx(List<Integer> LIS, int key) {
        int left = 0;
        int right = LIS.size() - 1;
        int idx = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (LIS.get(mid) <= key) {
                idx = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
        return idx + 1;
    }

}
