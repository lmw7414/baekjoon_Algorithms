import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] arr;
    static int pinCnt, move;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            // 1. init
            pinCnt = 0;
            arr = new char[5][9];
            for (int i = 0; i < 5; i++) {
                String str = br.readLine();
                for (int j = 0; j < 9; j++) {
                    char c = str.charAt(j);
                    arr[i][j] = c;
                    if (c == 'o') pinCnt++;
                }
            }
            move = 0;
            int p = pinCnt;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (arr[i][j] == 'o') calc(i, j, p, 0);
                }
            }
            sb.append(pinCnt).append(" ").append(move).append("\n");
            br.readLine();
        }
        System.out.print(sb);
    }

    // pc : 현재 남은 pin의 개수, m : 움직인 횟수
    public static void calc(int x, int y, int pc, int m) {
        if (pinCnt > pc) {
            pinCnt = pc;
            move = m;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (OOB(nx, ny) || OOB(nx + dx[d], ny + dy[d])) continue;
            if (!isMovable(nx, ny, nx + dx[d], ny + dy[d])) continue;

            // 2. 해당 핀 제거
            arr[nx][ny] = '.';

            // 3. 핀 이동
            arr[x][y] = '.';
            arr[nx + dx[d]][ny + dy[d]] = 'o';

            // 4. 다음 스텝
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (arr[i][j] == 'o') calc(i, j, pc - 1, m + 1);
                }
            }

            // 5. 핀 원위치
            arr[x][y] = 'o';
            arr[nx + dx[d]][ny + dy[d]] = '.';

            // 6. 제거된 핀 복구
            arr[nx][ny] = 'o';
        }

    }

    public static boolean isMovable(int nx, int ny, int nx2, int ny2) {
        return arr[nx][ny] == 'o' && arr[nx2][ny2] == '.';
    }

    // 범위를 벗어나면 true
    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= 5 || y >= 9 || arr[x][y] == '#';
    }

    static class Pin {
        int x, y;

        public Pin(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }
}