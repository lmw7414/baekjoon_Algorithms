import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제 해결 프로세스]
 * 1. 거리 기준으로 이분탐색
 * 2. 최대 거리는 N
 */

public class Main {
    static int N, M, K;
    static int[] arr;
    static Result answer = new Result(null, 0, -1);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) arr[i] = Integer.parseInt(st.nextToken());


        // 거리 기준 이분 탐색
        int left = 1;
        int right = N + 1;

        while (left < right) {
            int mid = (left + right) / 2;
            Result r = calc(mid);
            if (r.cnt >= M) { // 심판이 많거나 같다 -> 간격을 늘릴 필요가 있다.
                left = mid + 1;
                // 간격이 더 커졌다면
                if (answer.dist < mid) answer = r;
            } else { // 심판이 적다 -> 간격을 줄일 필요가 있다.
                right = mid;
            }
        }
        System.out.println(answer.visit);

    }

    public static Result calc(int dist) {
        StringBuilder sb = new StringBuilder();
        int cnt = 1; // 심판의 수
        sb.append(1);
        int previous = arr[0];
        for (int i = 1; i < K; i++) {
            if (cnt < M && arr[i] - previous >= dist) {
                previous = arr[i];
                sb.append(1);
                cnt++;
            } else sb.append(0);
        }
        return new Result(sb.toString(), cnt, dist);
    }

    static class Result {
        String visit;
        int cnt;
        int dist;

        public Result(String visit, int cnt, int dist) {
            this.visit = visit;
            this.cnt = cnt;
            this.dist = dist;
        }
    }

}