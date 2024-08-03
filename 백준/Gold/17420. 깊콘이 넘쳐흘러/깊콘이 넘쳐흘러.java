import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * N개의 기프티콘
 * 최대 10만개
 * 남은 기한 최대 10억개
 * 사용할 계획 최대 10억 일 뒤
 * 하루에 여러 기프티콘 사용 및 연장 가능
 * 기프티콘을 사용할 날짜에 해당 기프티콘은 사용기한이 가장 임박해야함
 * [문제 해결 프로세스]
 * 1. 사용일 기준 오름차순 정렬
 * 2. 사용 시점이 되었을 때 해당 기프티콘은 자신이 가진 기프티콘 중 가장 임박해야함
 * 3. 사용하려는 기프티콘 날보다 작다면 커질 때까지 모두 연장
 * 사용날짜 기준으로 정렬
 * 같은 사용일에서
 */
public class Main {
    static int N;
    static long answer = 0;
    static long[][] arr; // [][0] 남은 기한 | [][1] 사용할 일 차

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N][2];
        StringTokenizer st;
        for (int a = 0; a < 2; a++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i][a] = Long.parseLong(st.nextToken());
            }
        }

        Arrays.sort(arr, (a1, b1) -> {
            if (a1[1] == b1[1]) return Math.toIntExact(a1[0] - b1[0]);  // 사용일이 같으면 남은 기한을 기준 오름차순
            return Math.toIntExact(a1[1] - b1[1]);  // 사용일 기준 오름차순
        });

        long closeDay = arr[0][1];
        long previousMaxDay = 0;  // 이전의 날에서 최대 값
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (closeDay != arr[i][1]) {
                start = i;
                closeDay = arr[i][1];
                break;
            }
        }
        previousMaxDay = calc(0, start, previousMaxDay);

        for (int i = start; i < arr.length; i++) {
            if (closeDay != arr[i][1]) {
                previousMaxDay = calc(start, i, previousMaxDay);
                start = i;
                closeDay = arr[i][1];
            }
        }
        calc(start, arr.length, previousMaxDay);
        System.out.println(answer);
    }

    // 최대 기프티콘 남은 날 반환
    public static long calc(int start, int end, long previous) {
        long max = 0;
        for (int i = start; i < end; i++) {
            while (arr[i][1] > arr[i][0] || arr[i][0] < previous) {
                arr[i][0] += 30;
                answer++;
            }
            max = Math.max(max, arr[i][0]);
        }
        return max;
    }

}