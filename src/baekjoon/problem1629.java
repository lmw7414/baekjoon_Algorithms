package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1629 {

    static long A;
    static long B;
    static long C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(pow(A,B));
    }

    public static long pow(long a, long exponent) {
        if(exponent == 1)
            return a % C;

        long tmp = pow(a, exponent/2);

        if(exponent % 2 == 1) {
            return (tmp * tmp % C) * A % C;
        }
        return tmp * tmp % C;

    }
}
