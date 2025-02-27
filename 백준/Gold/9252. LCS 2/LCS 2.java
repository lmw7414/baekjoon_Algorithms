import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {
    static String str1, str2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        if (dp[str1.length()][str2.length()] == 0) {
            System.out.println(0);
        } else {
            System.out.println(dp[str1.length()][str2.length()]);
            findRoute();
        }
    }

    public static void findRoute() {
        Stack<Character> stack = new Stack<>();
        int curX = str1.length();
        int curY = str2.length();
        int val = dp[curX][curY];
        while (val != 0) {
            while (dp[curX][curY-1] == val) {
                curY--;
            }
            while (dp[curX - 1][curY] == val) {
                curX--;
            }
            stack.push(str1.charAt(curX - 1));
            val = dp[--curX][--curY];
        }
        while (!stack.isEmpty()) System.out.print(stack.pop());
    }
}