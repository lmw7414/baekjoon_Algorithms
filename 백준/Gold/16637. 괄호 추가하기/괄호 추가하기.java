import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 정답 보고 풀이
public class Main {
    static int answer = Integer.MIN_VALUE;
    static int N;
    static char[] cArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cArr = new char[N];
        cArr = br.readLine().toCharArray();
        dfs(2, cArr[0] - '0');
        System.out.println(answer);
    }

    public static void dfs(int idx, int sum) {
        if(idx >= N) {
            answer = Math.max(sum, answer);
            return;
        }
        // 괄호가 쳐져있지 않은 경우
        dfs(idx + 2, calc(sum, cArr[idx] - '0', cArr[idx - 1]));

        if(idx + 2 < N) {
            int right = calc(cArr[idx] - '0' , cArr[idx + 2] - '0', cArr[idx + 1]);
            int left = calc(sum, right, cArr[idx - 1]);
            dfs(idx + 4, left);
        }
    }

    static int calc(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }
}