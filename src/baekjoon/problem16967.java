package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 배열 복원하기
public class problem16967 {

    static int H, W, X, Y;
    static int A[][];
    static int B[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        A = new int[H + X][W + Y];
        B = new int[H + X][W + Y];     // A + tmp = B   B - tmp = A


        for (int i = 0; i < H + X; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W + Y; j++)
                B[i][j] = Integer.parseInt(st.nextToken());
        }


        // b[m][n] = a[m][n] + a[m-x][n-y]   -> a[m][n] = b[m][n] - a[m-x][n-y]
        for(int i = 0; i< H; i++) {
            for(int j = 0; j< W; j++) {

                if(i-X < 0 || j-Y < 0)
                    A[i][j] = B[i][j];
                else
                    A[i][j] = B[i][j] - A[i-X][j-Y];
            }
        }

        for(int i = 0; i< H; i++) {
            for(int j = 0; j< W; j++) {
                System.out.printf(A[i][j] +" ");
            }
            System.out.println();
        }

    }
}
