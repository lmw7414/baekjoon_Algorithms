
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            int N = Integer.parseInt(br.readLine());
            char[] arr = new char[N + N - 1];
            Arrays.fill(arr, ' ');
            for (int i = 0; i < N + N - 1; i += 2) {
                arr[i] = (char) ('0' + ((i + 2) / 2));
            }
            for (int i = 0; i < Math.pow(3, N - 1); i++) {
                int result = calc(arr);
                if (result == 0) {
                    for (char c : arr) sb.append(c);
                    sb.append('\n');
                }
                update(arr, 2 * N - 3);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static int calc(char[] arr) {
        StringBuilder sb2 = new StringBuilder();
        for (char c : arr) {
            if (c == ' ') continue;
            sb2.append(c);
        }
        String[] str = sb2.toString().split("\\+");
        int result = 0;
        for (String s : str) {
            if (s.contains("-")) {
                String[] s2 = s.split("-");
                result += Integer.parseInt(s2[0]);
                for (int i = 1; i < s2.length; i++)
                    result -= Integer.parseInt(s2[i]);
            } else result += Integer.parseInt(s);
        }
        return result;
    }

    public static void update(char[] arr, int idx) {
        if (idx < 0) return;

        if (arr[idx] == ' ') arr[idx] = '+';
        else if (arr[idx] == '+') arr[idx] = '-';
        else {
            arr[idx] = ' ';
            update(arr, idx - 2);
        }
    }

    public static char getVal(int key) {
        if (key == 0) return ' ';
        else if (key == 1) return '+';
        else return '-';
    }

}