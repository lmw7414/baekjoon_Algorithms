
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/*
규칙
1 -> N -> 2 -> N-1 -> 3 -> N-2 ... 가 가능하면 성공
 */

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        set.add(N);
        sb.append(N).append(" ");

        int left = 1, right = N;
        boolean flag = true;
        int step;
        while (left < right) {
            if (flag) step = nextStep(left++, right);
            else step = nextStep(right--, left);
            
            flag = !flag;
            if (set.contains(step)) {
                System.out.println(-1);
                return;
            }
            sb.append(step).append(" ");
            set.add(step);
        }

        System.out.print(sb);
    }

    public static int nextStep(int cur, int next) {
        if (cur <= next) return next - cur;
        else return nextStep(cur, N) + nextStep(1, next) + 1;
    }

}
