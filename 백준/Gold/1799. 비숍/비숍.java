import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr, chess;
    static boolean[][] visit;
    static int N;
    static int bAnswer = 0;
    static int wAnswer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        arr = new int[N][N];
        chess = new int[N][N];
        visit = new boolean[N][N];
        int flag = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                chess[i][j] = flag;
                flag = (flag + 1) % 2;
            }
            flag = (chess[i][0] + 1) % 2;
        }
        DFS(0, 0, 0, 1);  // 검은색 체크
        DFS(0, 1, 0, 0);  // 흰색 체크
        System.out.println(bAnswer + wAnswer);
    }

    public static void DFS(int i, int j, int cnt, int color) {
        if (i >= N) {
            if (color == 1) bAnswer = Math.max(bAnswer, cnt);
            else wAnswer = Math.max(wAnswer, cnt);
            return;
        }

        // 다음 위치 선정
        int nx = i;
        int ny = j + 2;
        if (ny >= N) {  // 배열을 벗어났다면 nx 변경
            nx++;
            if (nx < N) {
                if (chess[nx][0] == color) ny = 0;
                else ny = 1;
            }
        }

        if (arr[i][j] == 0) { // 놓을 수 없는 자리
            DFS(nx, ny, cnt, color);
            return;
        }
        if (isPossible(i, j)) {
            visit[i][j] = true;
            DFS(nx, ny, cnt + 1, color);
            visit[i][j] = false;
        }
        DFS(nx, ny, cnt, color);
    }

    // 비숍을 놓을 수 있는 자리인지 체크
    public static boolean isPossible(int x, int y) {
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, 1, -1};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            while (isIn(nx, ny)) {
                if (visit[nx][ny]) return false;
                nx += dx[i];
                ny += dy[i];
            }
        }
        return true;
    }

    // 범위 체크
    public static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

}