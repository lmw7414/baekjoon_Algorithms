import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제 해결 프로세스]
 * 자신과 반 등수의 차이가 K를 넘으면 친구가 아니다.
 * 좋은 친구는 이름의 길이가 같아야 한다.
 * N명의 학생들의 이름이 성적순으로 주어졌을 때 좋은 친구가 몇쌍이나 있는지 구하는 프로그램을 작성하시오
 * 좋은 친구는 등수의 차이가 K보다 작거나 같으면서 이름의 길이가 같은 친구이다.
 */

public class Main {
    static int N, K;
    static int[] arr, order;
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[21];
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        order = new int[N+1];
        int orderIdx = 0;
        int lastIdx = 0;
        // 첫 배열 세팅 ~K 까지
        for (; lastIdx <= K; lastIdx++) {
            String str = br.readLine();
            //System.out.println(str);
            int inputSize = str.length();
            order[orderIdx++] = inputSize;
            arr[inputSize]++;
            if (arr[inputSize] > 1) {
                answer += arr[inputSize] - 1;
            }
        }
        int startIdx = 0;
        for (; lastIdx < N; lastIdx++) {
            arr[order[startIdx++]]--;
            int inputSize = br.readLine().length();
            order[orderIdx++] = inputSize;
            arr[inputSize]++;
            if (arr[inputSize] > 1) {
                answer += arr[inputSize] - 1;
            }
        }
        System.out.println(answer);
    }

}