import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제설명]
 * 크기가 NXM인 지도
 * 지도의 좌표는 (r, c)로 나타냄
 * 주사위 지도 윗면이 1, 오른쪽은 3
 * 가장 처음의 주사위에는 모든 면이 0
 * 지도 각 칸에는 정수가 하나씩 쓰여져 있음
 * 주사위를 굴렸을 때
 * 0 -> 주사위 바닥면 숫자 복사
 * 0이 아닌 경우 -> 칸에 쓰여 있는 수가 주사위 바닥면으로 복사. 칸은 0으로 바뀜
 * -> 주사위가 이동했을 때 마다 상단에 쓰여있는 값을 구하여라
 * [조건]
 * 주사위가 바깥으로 이동하는 명령은 무시하고 출력도 하면 안됨
 * 최대 20 x 20
 * 명령의 개수 1000개
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M, K, X, Y;
    static int[] dice = new int[7];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int move = Integer.parseInt(st.nextToken());
            if (outOfRange(X + dx[move - 1], Y + dy[move - 1])) continue;
            X += dx[move - 1];
            Y += dy[move - 1];
            // 굴리기
            roll(move - 1);
            calc(X, Y);
            sb.append(dice[1]).append("\n");
        }
        System.out.print(sb);
    }

    public static void calc(int x, int y) {
        if (arr[x][y] == 0) {  // 주사위 바닥면의 숫자를 arr에 복사
            arr[x][y] = dice[6];
        } else {  // 칸의 숫자 -> 주사위 바닥면으로, 그리고 칸은 0으로 변경
            dice[6] = arr[x][y];
            arr[x][y] = 0;
        }
    }

    public static void roll(int move) {
        int temp = dice[1];
        switch (move) {
            case 0:
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = temp;
                return;
            case 1:
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = temp;
                return;
            case 2:
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = temp;
                return;
            case 3:
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = temp;

        }
    }

    public static boolean outOfRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}