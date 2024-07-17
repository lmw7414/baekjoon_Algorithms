import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [문제 설명]
 * 성냥 개수별 만들 수 있는 숫자
 * 7 -> 8
 * 6 -> 9, 6, 0
 * 5 -> 5, 3, 2
 * 4 -> 4
 * 3 -> 7
 * 2 -> 1
 * 성냥을 모두 사용해서 만들 수 있는 가장 작은 수와 큰 수를 찾아라
 * [조건]
 * 제한시간 1초
 * 성냥의 최대 개수 n은 100개
 * [문제 해결 프로세스]
 * 작은 수 -> 합이 k가 이루어지는 수 내에서 최소값 계산(i - j, j)
 * 큰 수 -> 2와 3을 이용해서 큰 수를 만들면 됨
 */
public class Main {
    static int N;
    static long[] small = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(small, Long.MAX_VALUE);
        small[2] = 1;
        small[3] = 7;
        small[4] = 4;
        small[5] = 2;
        small[6] = 6;
        small[7] = 8;
        small[8] = 10;

        int[] value = {1, 7, 4, 2, 0, 8};
        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                small[i] = Math.min(small[i], Long.parseLong(String.valueOf(small[i - j]) + String.valueOf(value[j - 2])));
            }
        }

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int k = Integer.parseInt(br.readLine());
            System.out.print(small[k] + " ");
            System.out.println(getBigNumber(k));
        }
    }

    public static String getBigNumber(int k) {
        StringBuilder sb = new StringBuilder();
        while (k != 0) {
            if (k % 2 == 1) {
                sb.append("7");
                k -= 3;
            } else {
                sb.append("1");
                k -= 2;
            }
        }
        return sb.toString();
    }

}