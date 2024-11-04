import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [문제 해결 프로세스]
 * isPalindrome[i][j] : i부터 j까지 펠린드롬의 여부
 * dp[i] : 0~i까지의 펠린드롬 집합 개수
 * dp[i] : Math.min(dp[i-1] + 1, ?)
 */

public class Main {
    static String str;
    static int INF = 987654321;
    static boolean[][] palindrome;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        int size = str.length();
        palindrome = new boolean[size][size];
        dp = new int[size];

        // 1. init palindrome
        for (int j = 0; j < size; j++) {
            palindrome[j][j] = true;
            for (int i = 0; i < j; i++) {
                if (str.charAt(j) == str.charAt(i) && palindrome[i + 1][j - 1])
                    palindrome[i][j] = true;
                else if (i + 1 == j && str.charAt(j) == str.charAt(i))
                    palindrome[i][j] = true;
            }
        }

        //2. calc dp
        Arrays.fill(dp, INF);
        dp[0] = 1;

        for (int j = 1; j < size; j++) {
            for (int i = 0; i <= j; i++) {
                if (palindrome[i][j]) {
                    //System.out.println(i + " " + j);
                    dp[j] = Math.min(dp[j], Math.min(dp[j - 1] + 1, (i - 1 < 0) ? 1 : dp[i - 1] + 1));
                }
            }
        }
//        for(int i: dp) System.out.print(i + " ");
//        System.out.println();
        System.out.println(dp[size - 1]);
    }
}