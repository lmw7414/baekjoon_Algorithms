import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제설명]
 * 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하라
 * [조건]
 * 4 ≤ N, M ≤ 500
 * 시간 2초
 * [문제해결 프로세스]
 * 1. 한 점에서 시작해서 depth가 4일 때까지 DFS
 * 2. depth가 4가 되었을 때 크기가 최대가 되면 정답
 */

public class Main {

    static int N, M;
    static int answer = 0;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {1, 0, 0};  //하좌우
    static int[] dy = {0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                specialCheck(i, j);
                visit[i][j] = true;
                DFS(1, i, j, arr[i][j]);
                visit[i][j] = false;
            }
        }
        System.out.println(answer);
    }

    public static void DFS(int depth, int x, int y, int result) {
        if (depth == 4) {
            answer = Math.max(answer, result);
            return;
        }
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isOutRange(nx, ny) || visit[nx][ny]) continue;
            visit[nx][ny] = true;
            DFS(depth + 1, nx, ny, result + arr[nx][ny]);
            visit[nx][ny] = false;
        }

    }

    public static boolean isOutRange(int nx, int ny) {
        return (nx < 0 || ny < 0 || nx >= N || ny >= M);
    }

    public static void specialCheck(int x, int y) {
        if (!isOutRange(x, y) && !isOutRange(x + 1, y) && !isOutRange(x + 2, y) && !isOutRange(x + 1, y + 1)) {
            answer = Math.max(answer, arr[x][y] + arr[x + 1][y] + arr[x + 2][y] + arr[x + 1][y + 1]);
        }
        if (!isOutRange(x, y) && !isOutRange(x + 1, y) && !isOutRange(x + 2, y) && !isOutRange(x + 1, y - 1)) {
            answer = Math.max(answer, arr[x][y] + arr[x + 1][y] + arr[x + 2][y] + arr[x + 1][y - 1]);
        }
        if (!isOutRange(x, y) && !isOutRange(x, y + 1) && !isOutRange(x, y + 2) && !isOutRange(x + 1, y + 1)) {
            answer = Math.max(answer, arr[x][y] + arr[x][y + 1] + arr[x][y + 2] + arr[x + 1][y + 1]);
        }
        if (!isOutRange(x, y) && !isOutRange(x, y + 1) && !isOutRange(x, y + 2) && !isOutRange(x - 1, y + 1)) {
            answer = Math.max(answer, arr[x][y] + arr[x][y + 1] + arr[x][y + 2] + arr[x - 1][y + 1]);
        }
    }

}