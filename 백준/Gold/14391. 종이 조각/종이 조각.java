
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 최대 4 x 4인 배열에서 직사각형으로 잘라야 함
 * 왼쪽 -> 오른쪽 | 위 -> 아래
 * 완탐 접근(DFS)
 *
 */
public class Main {
    static int N, M;
    static boolean[][] visit;
    static char[][] arr;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        arr = new char[N][M];
        visit = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
            }
        }
        DFS(0, 0);
        System.out.println(answer);

    }

    public static void DFS(int depth, int val) {
        if(depth == N * M) {
            answer = Math.max(answer, val);
            return;
        }

        int x = depth / M;
        int y = depth % M;
        if(visit[x][y]) DFS(depth + 1, val);
        else {
            StringBuilder sb = new StringBuilder();
            visit[x][y] = true;
            sb.append(arr[x][y]);
            DFS(depth + 1, val + Integer.parseInt(sb.toString()));

            // 가로
            int k = y + 1;
            for(; k < M; k++) {
                if(visit[x][k]) {
                    break;
                }
                sb.append(arr[x][k]);
                visit[x][k] = true;
                DFS(depth + 1, val + Integer.parseInt(sb.toString()));
            }
            for(int t = y + 1; t < k; t++) {
                visit[x][t] = false;
            }

            sb = new StringBuilder();
            sb.append(arr[x][y]);
            // 세로
            k = x + 1;
            for(; k < N; k++) {
                if(visit[k][y]) {
                    break;
                }
                sb.append(arr[k][y]);
                visit[k][y] = true;
                DFS(depth + 1, val + Integer.parseInt(sb.toString()));
            }
            for(int t = x + 1; t < k; t++) {
                visit[t][y] = false;
            }
            visit[x][y] = false;
        }
    }


}