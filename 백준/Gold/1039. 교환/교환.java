import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N, M, K;
    static int[] visit = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = str[0].length();
        K = Integer.parseInt(str[1]);

        if (M == 1) { // 한자리 수 인 경우
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(BFS());
    }

    public static int BFS() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        int depth = 1;
        while (!queue.isEmpty()) {
            if (depth == K + 1) break;
            int size = queue.size();
            while (size-- != 0) {
                int num = queue.poll();
                for (int i = 0; i < M - 1; i++) {
                    for (int j = i + 1; j < M; j++) {
                        int swapNum = swap(num, i, j);
                        if (swapNum == -1) continue;
                        if (visit[swapNum] == depth) continue;

                        queue.add(swapNum);
                        visit[swapNum] = depth;
                    }
                }
            }
            depth++;
        }
        if (queue.isEmpty()) return -1;
        int result = 0;
        while (!queue.isEmpty()) result = Math.max(result, queue.poll());
        return result;
    }

    public static int swap(int num, int i, int j) {
        char[] chars = String.valueOf(num).toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;

        if (chars[0] == '0') return -1;
        return Integer.parseInt(new String(chars));
    }

}