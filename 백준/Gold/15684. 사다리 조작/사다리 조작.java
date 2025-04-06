
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [입력]
 * N: | 개수 최대 10개
 * M: - 개수 놓을 수 있는 최대 가로선 270개
 * H: 최대 30개
 * [주의]
 * 최대 놓을 수 있는 가로선의 개수는 3
 */

public class Main {

    static int N, M, H;
    static boolean[][] ladder;
    static int answer = -1;
    static boolean correct = false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new boolean[H + 1][N + 2];
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladder[x][y] = true;
        }
        for(int i = 0; i <= 3; i++) {
            if(answer != -1) break;
            DFS(i, 0, 1, 1);
        }
        System.out.println(answer);

    }

    public static void DFS(int depth, int cur, int x, int y) {
        if(correct) return; // 이미 정답이 나온 경우 더 탐색할 필요없음
        if(depth == cur) {
            if(isAnswer()) {
                answer = cur;
                correct = true;
            }
            return;
        }
        if(y >= N) {
            x++;
            y = 1;
        }
        if(x > H) return;

        if(!isOk(x, y)) DFS(depth, cur, x, y+1);
        else {
            ladder[x][y] = true;
            DFS(depth, cur + 1, x, y + 1);
            ladder[x][y] = false;
            DFS(depth, cur, x, y + 1);
        }
    }


    // 해당 위치에 사다리를 놓을 수 있는지
    // num : 기준되는 세로선 | h: 놓으려는 높이
    // 놓을 수 있는 조건 -> 양쪽에 사다리가 있으면 안됨
    public static boolean isOk(int h, int num) {
        if(ladder[h][num]) return false;
        return !ladder[h][num - 1] && !ladder[h][num + 1];
    }

    // 정답 확인 메서드(i -> i로 가는지)
    public static boolean isAnswer() {
        for(int i = 1; i <= N; i++) {
            int cur = i;
            for(int j = 1; j <= H; j++) {
                if(ladder[j][cur]) cur += 1; // 오른쪽으로 이동
                else if(ladder[j][cur - 1]) cur -= 1; // 왼쪽으로 이동
            }
            if(i != cur) return false;
        }
        return true;
    }

}