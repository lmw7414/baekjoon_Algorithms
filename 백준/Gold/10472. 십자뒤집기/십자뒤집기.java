
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int[][] board = new int[3][3]; // 0 흰보드, 1 검은색
            int[][] answer = new int[3][3];
            set.clear();
            for (int j = 0; j < 3; j++) {
                String str = br.readLine();
                for (int k = 0; k < 3; k++) {
                    char c = str.charAt(k);
                    if (c == '*') {
                        answer[j][k] = 1;
                    }
                }
            }
            sb.append(bfs(board, answer)).append("\n");
        }
        System.out.print(sb);
    }

    public static int bfs(int[][] arr, int[][] answer) {
        if (isAnswer(arr, answer)) return 0;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(arr, 0));
        set.add(makeKey(arr));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 9; i++) {
                int nx = i / 3;
                int ny = i % 3;
                int[][] copy = copy(cur.arr);
                push(copy, nx, ny);
                int key = makeKey(copy);
                if (set.contains(key)) continue;
                if (isAnswer(copy, answer)) return cur.cnt + 1;
                set.add(key);
                queue.add(new Node(copy, cur.cnt + 1));
            }
        }
        return -1;
    }

    public static void push(int[][] arr, int x, int y) {
        arr[x][y] = nextVal(arr[x][y]);
        for (int d = 0; d < 4; d++) {
            if (OOB(x + dx[d], y + dy[d])) continue;
            arr[x + dx[d]][y + dy[d]] = nextVal(arr[x + dx[d]][y + dy[d]]);
        }
    }

    public static int nextVal(int target) {
        return target == 0 ? 1 : 0;
    }

    public static int makeKey(int[][] arr) {
        int res = 0;
        for (int i = 0; i < 9; i++) {
            int x = i / 3;
            int y = i % 3;
            if (arr[x][y] == 1) {
                res += (1 << i);
            }
        }
        return res;
    }

    public static boolean isAnswer(int[][] answer, int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (answer[i][j] != board[i][j]) return false;
            }
        }
        return true;
    }

    public static int[][] copy(int[][] arr) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= 3 || y >= 3;
    }

    static class Node {
        int[][] arr;
        int cnt;

        public Node(int[][] arr, int cnt) {
            this.arr = arr;
            this.cnt = cnt;
        }
    }

}