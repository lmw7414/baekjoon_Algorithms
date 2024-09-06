import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N; // 최대 4백만
    static int MAX = 4000001;
    static int answer = 0;
    static boolean[] arr = new boolean[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prime();
        if (N == 1) {
            System.out.println(0);
            System.exit(0);
        }

        int left = 2;
        int right = 2;
        int cur = 2;

        while (left != MAX) {
            if (cur == N) {
                answer++;
                right = nextIdx(right);
                cur += right;
            } else if (cur > N) {
                cur -= left;
                left = nextIdx(left);
            } else {
                right = nextIdx(right);
                cur += right;
            }
        }
        System.out.println(answer);
    }

    public static void prime() {
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (arr[i]) continue;
            for (int j = i * i; j <= N; j += i) {
                arr[j] = true;
            }
        }
    }

    public static int nextIdx(int curIdx) {
        for (int i = curIdx + 1; i <= N; i++) {
            if (!arr[i]) return i;
        }
        return MAX;
    }
}