import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] X, Y;
    static long xSum, ySum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        X = new long[N + 1];
        Y = new long[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            X[i] = Long.parseLong(st.nextToken());
            Y[i] = Long.parseLong(st.nextToken());
        }
        X[N] = X[0];
        Y[N] = Y[0];
        for (int i = 0; i < N; i++) {
            xSum += X[i] * Y[i + 1];
            ySum += X[i + 1] * Y[i];
        }
        System.out.printf("%.1f", (Math.abs(xSum - ySum) / 2.0));
    }

}