import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [문제 해결 프로세스]
 * 1. 자연수 N을 입력 받는다.
 * 2. N까지의 소수를 배열에 저장 -> 에라토스테네스의 체
 * 3. 투포인터로 접근하여 계산
 */
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(0);
            System.exit(0);
        }
        boolean[] arr = new boolean[n + 1];// 소수 저장 배열
        Arrays.fill(arr, true);
        arr[0] = arr[1] = false;

        // 에라토스테네스의 체
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (arr[i]) {
                for (int j = i * i; j <= n; j += i)
                    arr[j] = false;
            }
        }

        // 걸러진 소수들만 모아 배열로 저장
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (arr[i]) primes.add(i);
        }

        //투포인터 사용하여 경우의 수 계산
        int answer = 0;
        int left = 0;
        int right = 0;
        int sum = primes.get(right);

        while (left <= right) {
            if (sum == n) {
                answer++;
                if (right == primes.size() - 1)
                    break;
                right++;
                sum += primes.get(right);
            } else if (sum > n) {
                sum -= primes.get(left);
                left++;
            } else if (sum < n) {
                right++;
                if (right == primes.size())
                    break;
                sum += primes.get(right);
            }
        }
        System.out.println(answer);

    }

}