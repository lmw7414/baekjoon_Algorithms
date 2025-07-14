
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        char[][] arr = new char[3][3];
        for (int i = 0; i < 3; i++) Arrays.fill(arr[i], '.');
        dfs(arr, 'X');

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();
            if (str.equals("end")) break;
            if (set.contains(str)) sb.append("valid\n");
            else sb.append("invalid\n");
        }
        System.out.print(sb);
    }

    public static void dfs(char[][] arr, char cur) {
        if (done(arr)) {
            set.add(makeKey(arr));
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] != '.') continue;
                arr[i][j] = cur;
                String key = makeKey(arr);
                if (!set.contains(key)) {
                    dfs(arr, cur == 'X' ? 'O' : 'X');
                }
                arr[i][j] = '.';
            }
        }
    }


    public static boolean done(char[][] arr) {
        int cnt = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(arr[i][j] == '.') cnt++;
            }
        }
        if(cnt == 0) return true;
        // 가로
        for (int i = 0; i < 3; i++) {
            char start = arr[i][0];
            if (start == '.') continue;
            boolean flag = true;
            for (int j = 1; j < 3; j++) {
                if (arr[i][j] != start) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }

        // 세로
        for (int i = 0; i < 3; i++) {
            char start = arr[0][i];
            if (start == '.') continue;
            boolean flag = true;
            for (int j = 1; j < 3; j++) {
                if (arr[j][i] != start) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }

        // 대각선 1
        char start = arr[0][0];
        if (start != '.' && arr[1][1] == start && arr[2][2] == start) return true;
        // 대각선 2
        start = arr[0][2];
        if (start != '.' && arr[1][1] == start && arr[2][0] == start) return true;

        return false;
    }

    public static String makeKey(char[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(arr[i][j]);
            }
        }
        return sb.toString();
    }
}