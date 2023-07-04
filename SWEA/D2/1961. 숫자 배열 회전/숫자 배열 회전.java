import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static int[][] A;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= tc; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            A = new int[N][N];

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    A[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            sb = new StringBuilder();
            calc(N);
            printAnswer(i, sb);
        }
    }

    public static void calc(int N) {
        int a = 0;  // 90
        int b = N - 1;  // 180
        int c = N - 1;  // 270
        for (int j = 0; j < N; j++) {
            first(N, a++);
            second(N, b--);
            third(N, c--);

        }
    }

    public static void first(int N, int a) {
        for (int i = N - 1; i >= 0; i--)
            sb.append(A[i][a]);
        sb.append(" ");
    }

    public static void second(int N, int b) {
        for (int i = N - 1; i >= 0; i--)
            sb.append(A[b][i]);
        sb.append(" ");
    }

    public static void third(int N, int c) {
        for (int i = 0; i < N; i++)
            sb.append(A[i][c]);
        sb.append("\n");
    }

    public static void printAnswer(int tc, StringBuilder sb) {
        System.out.println("#" + tc);
        System.out.print(sb);
    }
}