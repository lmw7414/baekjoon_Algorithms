import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        arr = new long[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long min = 1;  //최소 길이
        long max = arr[K - 1] + 1;
        //upper bound
        while (min < max) {
            long mid = min + ((max - min) / 2);

            if (possibleMaxCnt(mid) < N) {  // N개보다 적게 만들어지는 경우
                max = mid;  // 길이를 줄이자
            } else {  // N개보다 많이 만들어지는 경우
                min = mid + 1;
            }
        }

        System.out.println(min - 1);
    }

    public static int possibleMaxCnt(long length) {
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            cnt += arr[i] / length;
        }
        return cnt;
    }

}