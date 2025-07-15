import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int X, Y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int val  = Y - X;
        if(val == 0) {
            System.out.println(0);
            return;
        }
        long diff = 1; // 길이 차이
        int day = 1;
        boolean flag = false;
        int cnt = 1;
        while(true) {
            if(diff <= val && val < diff + cnt) { // 차이가 조건에 해당한다면
                System.out.println(day);
                break;
            }
            diff += cnt;
            if(!flag) flag = true;
            else {
                cnt++;
                flag = false;
            }
            day++;
        }
    }
}