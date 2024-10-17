import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Ball[] balls;
    static int[] answer, colors;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = new Ball[N];
        colors = new int[N + 1];
        answer = new int[N + 1];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i + 1, color, size);
        }

        Arrays.sort(balls, Comparator.comparingInt(a -> a.size));

        // 현재까지의 누적합 - 같은 색
        int sum = 0;
        int idx = 0;
        for (int i = 0; i < N; i++) {
            Ball ball = balls[i];
            while (balls[idx].size < ball.size) {
                sum += balls[idx].size;
                colors[balls[idx].color] += balls[idx].size;
                idx++;
            }
            answer[ball.id] = sum - colors[ball.color];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.print(sb.toString());
    }

    static class Ball {
        int id;
        int color;
        int size;

        public Ball(int id, int color, int size) {
            this.id = id;
            this.color = color;
            this.size = size;
        }
    }
}