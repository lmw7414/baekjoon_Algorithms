import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer;
    static int[][] map = new int[3][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) cnt++;
                map[i][j] = num;
            }
        }

        if (cnt % 2 == 0) {
            answer = calc(2); // b턴 경우
        } else {
            answer = calc(1); // a턴 경우
        }
        if (answer == 1) {
            System.out.println("W");
        } else if (answer == -1) {
            System.out.println("L");
        } else {
            System.out.println("D");
        }
    }

    // -1 짐, 0 비김, 1 이김
    public static int calc(int turn) {
        int nextTurn = (turn == 1) ? 2 : 1;
        if (isWin(nextTurn)) return -1;  // 상대방 이김 -> 나는 짐
        int result = 2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] != 0) continue;
                map[i][j] = turn;
                result = Math.min(result, calc(nextTurn));
                map[i][j] = 0;
            }
        }
        if (result == 0 || result == 2) return 0;
        return -result;
    }

    public static boolean isWin(int curPlayer) {
        // 가로
        for (int i = 0; i < 3; i++)
            if (map[i][0] == curPlayer && map[i][1] == curPlayer && map[i][2] == curPlayer) return true;
        // 세로
        for (int i = 0; i < 3; i++)
            if (map[0][i] == curPlayer && map[1][i] == curPlayer && map[2][i] == curPlayer) return true;
        // 대각선
        if (map[0][0] == curPlayer && map[1][1] == curPlayer && map[2][2] == curPlayer) return true;
        if (map[0][2] == curPlayer && map[1][1] == curPlayer && map[2][0] == curPlayer) return true;
        return false;
    }

}