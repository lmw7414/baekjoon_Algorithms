import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        DP = new int[N][3];
        for(int i = 0; i< N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++) {
                DP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i< N; i++) {
            DP[i][0] = Math.min(DP[i-1][1], DP[i-1][2]) + DP[i][0];
            DP[i][1] = Math.min(DP[i-1][0], DP[i-1][2]) + DP[i][1];
            DP[i][2] = Math.min(DP[i-1][0], DP[i-1][1]) + DP[i][2];
        }

        System.out.println(Math.min(DP[N-1][0], Math.min(DP[N-1][1], DP[N-1][2])));

    }

}