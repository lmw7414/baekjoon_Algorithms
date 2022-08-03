package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//LCS
//Longest Common Subsequence 최대 공통 수열 찾기
//https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence
public class problem9251 {

    public static void main(String[] args) throws IOException {

        String str1;
        String str2;
        int arr[][];
        int max = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();

        arr = new int[str1.length() +1][str2.length() +1];

        for(int i = 1; i<= str1.length(); i++) {
            for(int j = 1; j<=str2.length(); j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1))
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                else
                    arr[i][j] = Math.max(arr[i - 1][j],arr[i][j - 1]);
            }
        }

        System.out.println(arr[str1.length()][str2.length()]);
    }
}
