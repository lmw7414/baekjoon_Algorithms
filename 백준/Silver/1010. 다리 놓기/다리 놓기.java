
import java.util.Scanner;

class Main {
    static long[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int  T = sc.nextInt();
        arr = new long[31][31];
        for(int i = 1; i<= 30; i++) {
            arr[i][0] = 1;
            arr[i][i] = 1;
        }
        for(int i = 0; i< T; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            System.out.println(calc(M, N));
        }
    }
    private static long calc(int M, int N) {
        if(arr[M][N] == 0)
            return arr[M][N] = calc(M - 1, N - 1) + calc(M - 1, N);
        else
            return arr[M][N];
    }
}