
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        // 두개의 기준값 찾기
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // 두 숫자간 간격 구하기
                int x = j - i - 1; // 두개의 숫자 사이의 빈 칸 개수
                int term = -1;
                if (x == 0) { // 연이은 숫자
                    term = arr[j] - arr[i];
                } else if ((arr[j] - arr[i]) % (x + 1) == 0) {
                    term = (arr[j] - arr[i]) / (x + 1);
                } else continue;
                int val = check(i, term);
                answer = Math.min(val, answer);
            }
        }
        System.out.println(answer);

    }

    public static int check(int std, int term) {
        int[] temp = new int[N];
        temp[std] = arr[std];

        // std 왼쪽으로 채우기
        for (int t = std - 1; t >= 0; t--) {
            temp[t] = temp[t + 1] - term;
        }
        // std 오른쪽으로 채우기
        for (int t = std + 1; t < N; t++) {
            temp[t] = temp[t - 1] + term;
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (cnt >= answer) return Integer.MAX_VALUE;
            if (arr[i] != temp[i]) cnt++;
        }
        return cnt;
    }
}