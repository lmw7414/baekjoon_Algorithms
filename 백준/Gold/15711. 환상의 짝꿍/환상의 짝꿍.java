import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] arr = new boolean[2000001]; // 2 * 10^12의 제곱근

    public static void main(String[] args) throws Exception {
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken()); // A, B의 범위는 최소 1부터 2조
            long B = Long.parseLong(st.nextToken());
            long sum = A + B;
            if (sum <= 3) {
                sb.append("NO").append("\n");
                continue;
            }
            if (sum % 2 == 0) { // 합이 짝수 인 경우
                sb.append("YES").append("\n");
            } else {
                if (isPrime(sum - 2)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    public static void init() {
        for (int i = 2; i < arr.length; i++) {
            if (arr[i]) continue;
            for (int j = i * 2; j < arr.length; j += i) {
                arr[j] = true;
            }
        }
    }

    public static boolean isPrime(long num) {
        if (num <= arr.length) return !arr[(int) num];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i]) continue;  // 소수가 아닌 경우
            if (num % i == 0) return false;
        }
        return true;
    }
}