
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
        int N = Integer.parseInt(st.nextToken());
        Long M = Long.parseLong(st.nextToken());
        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long min = 1;  //최소 길이
        long max = arr[N - 1] + 1;

        //upper bound
        while (min < max) {
            long mid = min + ((max - min) / 2);

            if (possibleMaxLen(mid) < M) {
                max = mid;  // 길이를 줄이자
            } else {  // N개보다 많이 만들어지는 경우
                min = mid + 1;
            }
        }

        System.out.println(min - 1);
    }

    public static long possibleMaxLen(long height) {
        long cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] - height > 0)
                cnt += arr[i] - height;
        }

        return cnt;
    }

}