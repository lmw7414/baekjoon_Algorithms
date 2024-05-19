
import java.util.Scanner;

public class Main {

    static int N;
    static int answer = 0;
    static int[] time;
    static int[] cost;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        N = scan.nextInt();
        time = new int[N];
        cost = new int[N];
        for (int i = 0; i < N; i++) {
            time[i] = scan.nextInt();
            cost[i] = scan.nextInt();
        }
        dfs(0,0);
        System.out.println(answer);
    }

        static void dfs(int index, int value) {
            if (index >= N) {
                answer = Math.max(answer, value);
                return;
            }

            // 해당 index를 포함
            if (index + time[index] <= N) {
                dfs(index + time[index], value + cost[index]);
            } else {
                dfs(index + time[index], value); // n을 넘어가면 value 합치지 않음
            }

            dfs(index + 1, value); // 해당 index 미포함
        }
}
