import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
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
                BigInteger bi = new BigInteger(String.valueOf(sum - 2));
                if (bi.isProbablePrime(10)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }
        System.out.print(sb.toString());
    }

}