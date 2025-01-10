import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제 해결 프로세스]
 * 1. 영역전개 - 해시맵<ID, SIZE> 저장
 * 2. 1인 곳에서 사방탐색하여 size 추가
 */
public class Main {

    static int N, M;
    static int[][] arr, territory, answer;
    static Map<Integer, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        territory = new int[N][M];
        answer = new int[N][M];
        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            for (int m = 0; m < M; m++) {
                arr[n][m] = str.charAt(m) - '0';
            }
        }
        setTerritory();
        calc();
        printAnswer();
    }

    public static void calc() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Set<Integer> set = new HashSet<>();
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (arr[n][m] == 0) continue;

                answer[n][m] = 1;
                for (int d = 0; d < 4; d++) {
                    int nx = n + dx[d];
                    int ny = m + dy[d];
                    if (OOB(nx, ny)) continue;
                    if (arr[nx][ny] == 1) continue;

                    if (set.contains(territory[nx][ny])) continue;
                    answer[n][m] += hm.get(territory[nx][ny]);
                    set.add(territory[nx][ny]);
                }
                answer[n][m] %= 10;
                set.clear();
            }
        }
    }

    public static void setTerritory() {
        Queue<int[]> queue = new ArrayDeque<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        int id = 1;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (arr[n][m] == 1) continue;
                if (territory[n][m] != 0) continue;
                int size = 1;
                territory[n][m] = id;
                queue.add(new int[]{n, m});
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();

                    for (int d = 0; d < 4; d++) {
                        int nx = cur[0] + dx[d];
                        int ny = cur[1] + dy[d];
                        if (OOB(nx, ny)) continue;
                        if (arr[nx][ny] == 1) continue;
                        if (territory[nx][ny] != 0) continue;

                        territory[nx][ny] = id;
                        queue.add(new int[]{nx, ny});
                        size++;
                    }
                }
                hm.put(id, size % 10);
                id++;
                queue.clear();
            }
        }
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    public static void printAnswer() {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                System.out.print(answer[n][m]);
            }
            System.out.println();
        }
    }

}