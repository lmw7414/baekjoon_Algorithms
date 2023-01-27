package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem8958 {

    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i< N; i++) {
            String tc = br.readLine();
            calc(tc);
        }
        System.out.println(sb.toString());

    }
    public static void calc(String tc) {
        int count = 1;
        int answer = 0;
        for(int i = 0; i< tc.length(); i++) {
            if(tc.charAt(i) == 'O') {
                answer += count++;
            }else {
                count = 1;
            }
        }
        sb.append(answer +"\n");
    }
}
