
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] arr;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (N == K) {
            System.out.println(0);
            return;
        }
        arr = new boolean[100001];
        visit = new boolean[100001];
        bfs(N, K);
    }

    public static void bfs(int N, int K) {
        Queue<Integer> queue = new ArrayDeque<>();
        int time = 0;
        queue.add(N);
        fill(queue, N, K); // 0초에 도달 가능한 지점
        if (arr[K]) {
            System.out.println(time);
            return;
        }
        Queue<Integer> temp;
        while (true) {
            time++;
            temp = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur + 1 <= K && !arr[cur + 1]) {
                    temp.add(cur + 1);
                    arr[cur + 1] = true;
                }
                if (cur - 1 >= 0 && !arr[cur - 1]) {
                    temp.add(cur - 1);
                    arr[cur - 1] = true;
                }
                fill(temp, cur + 1, K);
                fill(temp, cur - 1, K);
            }
            if (arr[K]) {
                System.out.println(time);
                return;
            }
            queue = temp;
        }
    }
    public static void fill(Queue<Integer> queue, int start, int K) {
        if (start > K || start <= 0) return;
        if (visit[start]) return;
        visit[start] = true;
        int move = start;
        while (move <= 100000) {
            if (!arr[move]) {
                arr[move] = true;
                queue.add(move);
            }
            if (K < move) break;
            move *= 2;
        }
    }

}