import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] answer;
    static int INF = 10000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = new int[N + 1][N + 1];

        // 1. init Arr
        arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(arr[i], INF);
            arr[i][i] = 0;
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            arr[start][dest] = weight;
            arr[dest][start] = weight;
            answer[start][dest] = dest;
            answer[dest][start] = start;
        }

        // 2. 플로이드 워셜 알고리즘
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                        answer[i][j] = answer[i][k];
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) sb.append("- ");
                else sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}