import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * 김대전은 소설을 여러장으로 나누어 쓰는데 각 장은 각각 다른 파일에 저장하곤한다.
 * 소설의 모든 장을 쓰고 나서는 각 장이 쓰여진 파일을 합쳐서 최종적으로 소설의 완성본이 들어있는 한 개의 파일을 만든다.
 * 이 과정에서 두 개의 파일을 합쳐서 하나의 임시파일을 만들고, 이 임시 파일이나 원래의 파일을 계속 두개씩 합쳐서
 * 소설의 여러장들이 연속이 되도록 파일을 합쳐나가고, 최종적으로 하나의 파일을 합친다.
 * -> 두 개의 파일을 합칠 때 필요한 비용이 두 파일 크기의 합이라고 가정할 때 최종적인 한개의 파일을 생성하는데 필요한 비용의 총합
 * [문제 해결 프로세스]
 * dp로 접근
 * dp[i][j] -> i에서 j까지의 비용
 */

public class Main {

    static int T;
    static int[][] dp;
    static int[] arr, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            dp = new int[K + 1][K + 1];
            arr = new int[K + 1];
            sum = new int[K + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + arr[i]; //누적합
            }
            // 계산
            for (int i = 1; i <= K; i++) {
                for (int from = 1; from + i <= K; from++) {
                    int to = from + i;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int middle = from; middle < to; middle++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][middle] + dp[middle + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }
            System.out.println(dp[1][K]);
        }
    }
}