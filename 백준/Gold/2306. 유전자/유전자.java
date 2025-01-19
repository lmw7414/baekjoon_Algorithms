import java.io.*;
import java.util.*;

/**
 * DNA 서열 -> {a,c,g,t}
 * at, gc -> 가장 짧은 길이의 유전자
 * [문제 해결 프로세스]
 * 1. 주어진 문자열이 KOI 유전자인지 확인
 * 2. 앞 뒤를 비교 했을 때 aXt, gXc 이면 문자열 길이 리턴
 * 3.
 */
public class Main {
    static int[][] dp;
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        dp = new int[str.length()][str.length()];
        for(int i = 0; i < str.length(); i++) Arrays.fill(dp[i], -1);
        System.out.println(calc(0, str.length() - 1));

//        for(int i = 0; i < str.length(); i++) {
//            for(int j = 0; j < str.length(); j++) {
//                System.out.print(dp[i][j] + "\t");
//            }
//            System.out.println();
//        }
    }

    public static int calc(int left, int right) {
        if(left >= right) return dp[left][right] = 0;
        if(dp[left][right] != -1) return dp[left][right];
        if((str.charAt(left) == 'a' && str.charAt(right) == 't') || (str.charAt(left) == 'g' && str.charAt(right) == 'c'))
            dp[left][right] = calc(left + 1, right - 1) + 2;

        for(int mid = left; mid < right; mid++) {
            dp[left][right] = Math.max(dp[left][right], calc(left, mid) + calc(mid + 1, right));
        }
        return dp[left][right];
    }

}