import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제 해결 프로세스]
 * 이미 만들어졌던 그림이라면?
 * 새로 만들어진 그림이라면 등록
 * 해시맵에 <String, Integer>: 이차원 배열을 String으로 풀어서
 */
public class Main {
    static Map<String, Integer> visit = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] arr = new int[3][3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(calc(arr));
    }

    public static int calc(int[][] arr) {
        Queue<String> queue = new ArrayDeque<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        String str = parseToString(arr);
        if (str.equals("123456780")) return 0;
        queue.add(str);
        visit.put(str, 0);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            int cnt = visit.get(cur);
            int[][] board = parseTo2DArr(cur);
            int[] pos = find0(board);
            for (int d = 0; d < 4; d++) {
                int nx = pos[0] + dx[d];
                int ny = pos[1] + dy[d];
                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;

                String newStr = swap(board, pos[0], pos[1], nx, ny);
                if (visit.containsKey(newStr)) continue;
                if (newStr.equals("123456780")) return cnt + 1;
                visit.put(newStr, cnt + 1);
                queue.add(newStr);
            }
        }
        return -1;
    }

    public static String swap(int[][] arr, int x, int y, int nx, int ny) {
        // swap
        int temp = arr[nx][ny];
        arr[nx][ny] = 0;
        arr[x][y] = temp;
        String str = parseToString(arr);

        // return
        temp = arr[nx][ny];
        arr[nx][ny] = arr[x][y];
        arr[x][y] = temp;

        return str;
    }

    public static int[] find0(int[][] arr) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == 0) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    public static String parseToString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(arr[i][j]);
            }
        }
        return sb.toString();
    }

    public static int[][] parseTo2DArr(String str) {
        int[][] arr = new int[3][3];
        for (int i = 0; i < str.length(); i++) {
            arr[i / 3][i % 3] = str.charAt(i) - '0';
        }
        return arr;
    }

}