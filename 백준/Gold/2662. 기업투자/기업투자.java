import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr, dp;
    static int[][] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        initDp();
        System.out.println(dp[M][N]);
        getPath();

//        for(int i = 1; i <= M; i++) {
//            for(int j = 1; j <= N; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }


    }

    public static void initDp() {
        dp = new int[M + 1][N + 1]; // [기업][비용]
        path = new int[M + 1][N + 1];
        for (int company = 1; company <= M; company++) {
            for (int total = 1; total <= N; total++) {
                for (int cur = 0; cur <= total; cur++) {  // 현재 순서의 기업이 사용할 금액
                    //System.out.println(company+"회사에서 " + cur + "원 투자:" + arr[cur][company] + "수익");
                    if(dp[company][total] < dp[company - 1][total - cur] + arr[cur][company]) {
                        dp[company][total] = dp[company - 1][total - cur] + arr[cur][company];
                        path[company][total] = cur;
                    }
                }
            }
        }
    }

    public static void getPath() {
        List<Integer> answer = new ArrayList<>();
        int curr = M;
        int cost = N;

        while (curr > 0) {
            int invest = path[curr][cost];
            answer.add(invest);

            cost -= invest;
            curr--;
        }

        Collections.reverse(answer);
        for(int i : answer) System.out.print(i + " ");
    }

}