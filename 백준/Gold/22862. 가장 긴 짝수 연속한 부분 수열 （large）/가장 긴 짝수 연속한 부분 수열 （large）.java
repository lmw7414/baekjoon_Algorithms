import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int len = (arr[0] % 2 == 0) ? 1 : 0;
        int k = (arr[0] % 2 == 0) ? 0 : 1;
        int left = 0;
        int right = 0;
        int max = len;
        while (right < arr.length - 1) {
            if (k < K) {
                if (arr[++right] % 2 == 0) len++;
                else k++;
            } else {
                if (arr[right + 1] % 2 == 0) {
                    len++;
                    right++;
                } else {
                    if (arr[left++] % 2 != 0) {
                        k--;
                    } else {
                        len--;
                    }
                }
            }
            max = Math.max(len, max);
        }
        System.out.println(max);
    }

}