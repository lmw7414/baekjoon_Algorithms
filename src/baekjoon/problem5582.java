package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//공통부분 문자열
//Longest Common SubString
public class problem5582 {

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
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                    if(max < arr[i][j])
                        max = arr[i][j];
                }
            }
        }

        System.out.println(max);
    }
}
