package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

//하노이 탑
//totalcount는 2^n - 1 공식을 사용해도 된다.
public class problem1914 {
    static BigInteger bigCount = new BigInteger("2");

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        if( N > 20) {
            totalCount(N);
        }
        else {
            System.out.println((int)(Math.pow(2,N) -1 ));
            hanoi(N,1,2,3);
            System.out.print(sb.toString());
        }
    }

    static void hanoi(int n, int start, int via, int to) {

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
    static void totalCount (int n) {
        for(int i= 1; i< n; i++)
            bigCount = bigCount.multiply(new BigInteger("2"));
        bigCount = bigCount.subtract(new BigInteger("1"));
        System.out.println(bigCount);
    }
}
