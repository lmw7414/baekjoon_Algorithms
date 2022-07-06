package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//하노이 탑 이동순서
//totalcount는 2^n - 1 공식을 사용해도 된다.
public class problem11729 {
    static int count =0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        hanoi(N,1,2,3);
        System.out.println(count);
        System.out.print(sb.toString());
    }

    static void hanoi(int n, int start, int via, int to) {
        count++;
        if(n == 1){
            sb.append(start + " " + to + '\n');
            return;
        }
        else {
            hanoi(n-1, start, to, via);
            sb.append(start + " " + to + '\n');
            hanoi(n-1, via, start, to);
        }
    }
    static int totalCount (int n) {
        return (int) (Math.pow(2, n) -1);
    }
}
