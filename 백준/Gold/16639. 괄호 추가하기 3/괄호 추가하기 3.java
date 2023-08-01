

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        String s = st.nextToken();

        int min[][] = new int[N][N];
        int max[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(min[i], Integer.MAX_VALUE);
            Arrays.fill(max[i], Integer.MIN_VALUE);
        }

        for (int i = 0; i < N; i++) {
            if (Character.isDigit(s.charAt(i)))
                min[i][i] = max[i][i] = s.charAt(i) - '0';
        }

        for (int j = 2; j < N; j += 2) {
            for (int i = 0; i < N - j; i += 2) {
                for (int k = 2; k <= j; k += 2) {
                    int[] tmp = new int[4];

                    tmp[0] = calculate(min[i][i + k - 2], s.charAt(i + k - 1), min[i + k][i + j]);  //min min
                    tmp[1] = calculate(min[i][i + k - 2], s.charAt(i + k - 1), max[i + k][i + j]);  //min max
                    tmp[2] = calculate(max[i][i + k - 2], s.charAt(i + k - 1), min[i + k][i + j]);  //max min
                    tmp[3] = calculate(max[i][i + k - 2], s.charAt(i + k - 1), max[i + k][i + j]);  //max max

                    Arrays.sort(tmp);

                    min[i][i+j] = Math.min(min[i][i+j], tmp[0]);
                    max[i][i+j] = Math.max(max[i][i+j], tmp[3]);
                }
            }
        }
        System.out.println(max[0][N-1]);
    }

    static int calculate(int a, char op, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }

}
