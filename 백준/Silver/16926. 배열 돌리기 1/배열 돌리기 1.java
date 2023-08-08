import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int R;
    static int[][] arr;
    static int depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        depth = Math.min(N, M) / 2;
        for (int i = 0; i < R; i++)
            solve(depth);
        printArr();
    }

    public static void solve(int depth) {
        for (int i = 0; i < depth; i++) {
            int temp = arr[i][i];

            // 위
            for (int j = i + 1; j < M - i; j++)
                arr[i][j - 1] = arr[i][j];
            // 오른
            for (int j = i + 1; j < N - i; j++)
                arr[j - 1][M - i - 1] = arr[j][M - i - 1];
            // 아래
            for (int j = M - i - 2; j >= i; j--)
                arr[N - i - 1][j + 1] = arr[N - i - 1][j];
            // 왼
            for (int j = N - i - 2; j >= i; j--)
                arr[j + 1][i] = arr[j][i];
            arr[i + 1][i] = temp;
        }
    }

    public static void printArr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}