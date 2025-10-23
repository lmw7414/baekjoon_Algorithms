
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] arr;
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[2][100001];
        for(int i = 0; i<= 100000; i++) {
            arr[0][i] = INF;
            arr[1][i] = i;
        }
        bfs(N, K);
        System.out.println(arr[0][K]);

        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int idx = K;
        while(arr[1][idx] != idx) {
            stack.push(arr[1][idx]);
            idx = arr[1][idx];
        }
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void bfs(int N, int K) {
        arr[0][N] = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            int time = arr[0][cur];

            for(int d = 0; d < 3; d++) {
                int next = cur;
                if(d == 0) next -= 1;
                else if(d == 1) next += 1;
                else next *= 2;

                if(next > 100000 || next < 0) continue;
                if(arr[0][next] <= time + 1) continue;
                arr[0][next] = time + 1;
                arr[1][next] = cur;
//                System.out.println(next + "위치 도달: " + arr[0][next]);
                if(next == K) return;
                queue.add(next);
            }
        }
    }


}