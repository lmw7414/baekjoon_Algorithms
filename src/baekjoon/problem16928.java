package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem16928 {
    static int N; // 사다리
    static int M;  // 뱀
    static int[] board = new int[101];
    static int[] check = new int[101];

    // static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        initBoard();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            board[a1] = a2;
        }

        System.out.println(BFS(1));
    }

    public static int BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for(int i = 1; i <= 6; i++) {
                int next = cur + i;

                if(next > 100)
                    continue;

                if(check[board[next]] == 0) {
                    queue.add(board[next]);
                    check[board[next]] = check[cur] + 1;
                }

                if(board[next] == 100)
                    return check[next];
            }
        }
        return 0;
    }

    public static void initBoard() {
        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }
    }

}

