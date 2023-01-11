package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem10830 {

    static int N;
    static long exponent;
    static long C[][];
    //static long answer[][];
    static long p = 1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        exponent = Long.parseLong(st.nextToken());
        C = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                C[i][j] = Long.parseLong(st.nextToken()) % p;
            }
        }
        long[][] answer = pow(C, exponent);

        for(int i = 0; i< N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static long[][] pow(long[][] a, long exponent) {
        if (exponent == 1L) {
            return a;
        }

        long[][] tmp = pow(a, exponent / 2);

        tmp = multiple(tmp, tmp);

        if (exponent % 2 == 1L) {
            tmp = multiple(tmp, C);
        }
        return tmp;

    }

    static long[][] multiple(long[][] a, long[][] b) {
        long[][] answer = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    answer[i][j] += a[i][k] * b[k][j];
                    answer[i][j] %= p;
                }
            }
        }
        return answer;
    }
}
