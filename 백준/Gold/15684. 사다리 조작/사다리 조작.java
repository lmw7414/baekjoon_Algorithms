import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H;
    static boolean[][] ladder;
    static int answer;
    static boolean correct = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 세로선의 개수
        M = Integer.parseInt(st.nextToken());  // 가로 선의 개수
        H = Integer.parseInt(st.nextToken());  // 가로 선을 놓을 수 있는 사이즈

        // 사다리 배열 생성 - 가로 선이 있을 경우 해당 위치는 true
        ladder = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladder[x][y] = true;
        }

        for (int i = 0; i <= 3; i++) { // 최대 3개까지 놓을 수 있다!
            answer = i;
            dfs(0);
            if (correct) break;
        }
        if (correct) System.out.println(answer);
        else System.out.println(-1);
    }

    public static void dfs(int depth) {
        if(correct) return;
        if (depth == answer) {
            if (check()) correct = true;
            return;
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (ladder[i][j]) continue;  // 이미 다리가 놓여져 있으면 넘어가기
                if (ladder[i][j - 1] || ladder[i][j + 1]) continue;  // 양옆 체크
                ladder[i][j] = true;
                dfs(depth + 1);
                ladder[i][j] = false;
            }
        }
    }


    // 사다리 i에서 i로 가는지 확인하는 메서드
    public static boolean check() {
        for (int i = 1; i <= N; i++) {
            int cur = i;
            for (int j = 1; j <= H; j++) {
                if (ladder[j][cur - 1])   // 현재 방향의 왼쪽에 다리가 있으면 왼쪽으로 이동
                    cur--;
                else if (ladder[j][cur])  // 현재 방향의 오른쪽에 다리가 있으면 오른쪽으로 이동
                    cur++;
            }
            if (cur != i) return false;
        }
        return true;
    }

}