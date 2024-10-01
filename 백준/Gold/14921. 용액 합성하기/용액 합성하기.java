import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] arr;
    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        String[] strs = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }

        int left = 0;
        int right = arr.length - 1;
        int absMax = 200_000_001; // 최대 2억
        while (left != right) {
            int val = arr[left] + arr[right];
            int abs = Math.abs(val);
            if (abs < absMax) {
                absMax = abs;
                result = val;
                if (result == 0) break;
            }
            if (val > 0) right--;
            else left++;
        }
        System.out.println(result);

    }

}