import java.util.*;
import java.io.*;


/**
 * [문제 해결 프로세스]
 * - 2개 이상의 부분집합 만들기 -> 시간초과
 * - 최대 최소를 정해놓고, 부분집합의 개수를 파악해 계산 -> 최대 최소 구하는 과정에서 O(N^2) -> 시간초과
 * -> 모든 조합의 최댓값 - 모든 조합의 최솟값
 * - 모든 부분집합에 대해 최댓값이 i번째 라면 -> i개
 * - 모든 부분집합에 대해 최솟값이 i번째 라면 -> N - i개
 * - 오름차순 정렬 -> 배열의 시작과 끝으로 스코빌 지수 계산 가능
 */
public class Main {
    static final long MOD = 1_000_000_007;
    static int N;
    static long[] arr;
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            answer += (arr[i] * pow(2, i));
            answer -= (arr[i] * pow(2, N - 1 - i));
            answer %= MOD;
        }

        System.out.println(answer);
    }

    public static long pow(int base, int x) {
        if (x == 0) return 1L;
        long half = pow(base, x / 2);
        if (x % 2 == 0) return half * half % MOD;
        return half * half * base % MOD;
    }

}