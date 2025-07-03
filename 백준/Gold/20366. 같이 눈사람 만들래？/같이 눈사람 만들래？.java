import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
두 눈사람의 키 차이가 작을수록 두 눈사람의 사이가 좋음
키 : 눈덩이 두개의 지름
밑에는 지름이 큰 것, 위에는 지름이 작은 것
 */
public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                balls.add(new Ball(i, j, arr[i] + arr[j]));
            }
        }
        balls.sort((a, b) -> a.size - b.size);
        int abs = Integer.MAX_VALUE;
        int right = 0;
        for(int left = 0; left < balls.size() - 1; left++) {
            Ball first = balls.get(left);
            Ball second = right + 1 < balls.size() ? balls.get(++right) : balls.get(right);
            if(check(first, second)) abs = Math.min(abs, Math.abs(first.size - second.size));
            while(right + 1 < balls.size() && second.size == balls.get(right + 1).size) {
                right++;
                second = balls.get(right);
                if(check(first, second)) abs = Math.min(abs, Math.abs(first.size - second.size));
            }
            while(left + 1 < right) {
                left++;
                first = balls.get(left);
                if(check(first, second)) abs = Math.min(abs, Math.abs(first.size - second.size));
            }
            if(right == balls.size() - 1 && left + 1 == right)break;
            left--;
        }

        System.out.println(abs);

    }

    public static boolean check(Ball a, Ball b) {
        return a.x != b.x && a.x != b.y && a.y != b.x && a.y != b.y;
    }

    static class Ball {
        int x, y, size;

        public Ball(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

}