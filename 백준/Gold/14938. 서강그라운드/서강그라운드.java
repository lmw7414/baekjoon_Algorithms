import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] arr;
    static int[] items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        // 1. 길이 무한대로 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(arr[i], 987654321);
            arr[i][i] = 0;
        }

        // 아이템 개수 저장
        items = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            items[i + 1] = Integer.parseInt(st.nextToken());
        }

        // 입력된 길 정보 업데이트
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[s][e] = w;
            arr[e][s] = w;
        }

        for (int k = 1; k <= N; k++) { // 경유지
            for (int i = 1; i <= N; i++) { // 출발지
                for (int j = 1; j <= N; j++) { // 도착지
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int itemsCnt = 0;
            for (int j = 1; j <= N; j++) {
                if (arr[i][j] <= M) {
                    itemsCnt += items[j];
                }
            }
            answer = Math.max(itemsCnt, answer);
        }
        System.out.println(answer);

    }

}