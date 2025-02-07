import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 0~ M까지 돈일 때 최대로 만들 수 있는 값 저장
2. dp[i] : i원 일 때 만들 수 있는 최대 값
 */

public class Main {
    static int N, M;
    static int[] arr;
    static String[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) arr[n] = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        dp = new String[M + 1];
        // 1차 초기화 |
        for (int n = 0; n < N; n++) {
            if (M >= arr[n]) dp[arr[n]] = max(dp[arr[n]], String.valueOf(n));
        }

        for (int m = 1; m <= M; m++) {
            for (int i = m; i >= m / 2; i--) {
                dp[m] = max(dp[m], sum(dp[i], dp[m - i]));
            }
        }
        System.out.println(dp[M]);
    }

    public static String max(String str1, String str2) {
        if (str1 == null && str2 == null) return null;
        else if (str1 == null) return str2;
        else if (str2 == null) return str1;

        if (str1.length() < str2.length()) return str2;
        else if (str1.length() > str2.length()) return str1;
        else {
            for (int i = 0; i < str1.length(); i++) {
                int s1 = str1.charAt(i) - '0';
                int s2 = str2.charAt(i) - '0';
                if (s1 == s2) ;
                else if (s1 > s2) return str1;
                else return str2;
            }
            return str1;
        }
    }

    public static String sum(String str1, String str2) {
        if (str1 == null && str2 == null) return null;
        else if (str1 == null) return str2;
        else if (str2 == null) return str1;

        if (str1.equals("0") && str2.equals("0")) return "0";
        else if (str1.equals("0")) return str2 + str1;
        else if (str2.equals("0")) return str1 + str2;
        return max(str1 + str2, str2 + str1);
    }

}