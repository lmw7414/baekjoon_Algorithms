package baekjoon;

import java.util.Scanner;

public class problem9461 {

    static long[] P = new long[101];

    public static void main(String[] args) {
        P[0] = 0;
        P[1] = 1;
        P[2] = 1;
        calc();

        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for (int i = 0; i < tc; i++) {
            int t = sc.nextInt();
            System.out.println(P[t]);
        }

    }

    static void calc() {
        for(int i = 3; i< 101; i++)
            P[i] = P[i-3] + P[i-2];
    }
}
