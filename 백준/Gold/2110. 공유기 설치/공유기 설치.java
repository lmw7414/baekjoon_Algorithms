import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int min = 1;  //최소 거리
        int max = arr[N - 1] - arr[0] + 1;  // 최대 거리 + 1
        //upper bound
        while (min < max) {
            int mid = (min + max) / 2;

            if (possibleInstallment(mid) < C) {  // 공유기 설치가 남는 경우
                max = mid;  // 거리를 줄여야 함
            } else {  // 공유기가 부족한 경우 -> 거리를 늘려야 함
                min = mid + 1;
            }
        }

        System.out.println(min - 1);
    }

    public static int possibleInstallment(int distance) {
        int cnt = 1;
        int recentHouse = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int curX = arr[i];

            if (curX - recentHouse >= distance) {
                cnt++;
                recentHouse = curX;
            }
        }
        return cnt;
    }

}
