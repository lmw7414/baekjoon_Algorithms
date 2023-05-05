
import java.util.Scanner;

public class Main {
    static String S;
    static boolean answer = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextLine();
        String T = sc.nextLine();

        dfs(T);
        System.out.println(answer ? 1 : 0);
    }

    public static void dfs(String t) {
        if (t.length() <= S.length()) {
            if (S.equals(t))
                answer = true;
        } else {
            if (t.charAt(t.length() - 1) == 'A')
                dfs(deleteA(t));
            else
                dfs(reverseStr(t));
        }
    }

    // 문자열 뒤에 A 제거
    public static String deleteA(String t) {
        return t.substring(0, t.length() - 1);
    }

    // B를 빼고 문자열 뒤집기
    public static String reverseStr(String t) {
        StringBuilder sb = new StringBuilder(t.substring(0, t.length() - 1));
        return sb.reverse().toString();
    }
}

