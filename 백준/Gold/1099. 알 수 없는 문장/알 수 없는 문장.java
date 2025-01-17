import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String target;
    static int[] dp;
    static int INF = 7654321;
    static HashMap<Integer, List<String>> hm = new HashMap<Integer, List<String>>();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();
        dp = new int[target.length() + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            int size = str.length();
            if(hm.containsKey(size)) {
                hm.get(size).add(str);
            }else {
                hm.put(size, new ArrayList<>());
                hm.get(size).add(str);
            }
        }

        for(int i = 1; i <= target.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(!hm.containsKey(i - j)) continue;

                String str1 = target.substring(j, i);
                for(String str2 : hm.get(i - j)){
                    dp[i] = Math.min(dp[i], dp[j] + calc(str1, str2));
                }
            }
        }

//        for(int i = 0; i <= target.length(); i++) System.out.print(dp[i] + " ");
//        System.out.println();

        if(dp[target.length()] == INF) System.out.println(-1);
        else System.out.println(dp[target.length()]);
    }

    // str1 : part of target | str2 : str in hm
    public static int calc(String str1, String str2) {
        // 1. 해당 단어(str1)의 알파벳으로 str2를 만들 수 있는지 체크. 아니면 INF 반환
        if(!isPossible(str1, str2)) return INF;
        // 값 계산
        int result = 0;
        boolean[] flag = new boolean[str2.length()];
        for(int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i) == str2.charAt(i)) {
                flag[i] = true;
            }
            else {
                for(int j = 0; j < str1.length(); j++) {
                    if(flag[j]) continue;
                    if(str1.charAt(i) == str2.charAt(j)) {
                        flag[j] = true;
                        result++;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static boolean isPossible(String str1, String str2) {
        int[] flag1 = new int[26];
        int[] flag2 = new int[26];
        for(int i = 0; i < str1.length(); i++) {
            char s1 = str1.charAt(i);
            char s2 = str2.charAt(i);
            flag1[s1 - 'a']++;
            flag2[s2 - 'a']++;

        }
        for(int i = 0; i < 26; i++) {
            if(flag1[i] != flag2[i]) return false;
        }
        return true;
    }

}