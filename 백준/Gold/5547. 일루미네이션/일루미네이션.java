import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;
    static boolean[][] arr;

    static int[] dx1 = {0, 1, 0, -1, -1, -1};  // y가 짝수 일 때
    static int[] dy1 = {-1, 0, 1, 1, 0, -1};  // 상 우 하 좌하, 좌, 좌상
    static int[] dx2 = {0, 1, 1, 1, 0, -1}; // y가 홀수 일 때
    static int[] dy2 = {-1, -1, 0, 1, 1, 0};  // 상 우상 우 우하 하 좌


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new boolean[H + 2][W + 2];

        for (int y = 1; y <= H; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= W; x++) {
                if (Integer.parseInt(st.nextToken()) == 1) arr[y][x] = true;
            }
        }
        fillArr();
        System.out.println(calc());

    }


    // 내부에 있는 건물 채우기
    // 가외를 나갈 수 있으면 해당 건물은 내부가 아님
    // 경계 밖에서 시작하여 경계외 연결되어있는 공터는 다 지움
    public static void fillArr() {
        boolean[][] visit = new boolean[H + 2][W + 2];


        Queue<int[]> queue = new ArrayDeque<>();
        visit[0][0] = true;
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 6; i++) {
                int nx, ny;
                if (now[0] % 2 == 1) {
                    ny = now[0] + dy2[i];
                    nx = now[1] + dx2[i];
                } else {
                    ny = now[0] + dy1[i];
                    nx = now[1] + dx1[i];
                }
                if (ny < 0 || nx < 0 || ny > H + 1 || nx > W + 1) continue; // 경계를 벗어남
                if (visit[ny][nx]) continue; // 이전에 방문했음
                if (arr[ny][nx]) continue; //건물이 있음
                visit[ny][nx] = true;
                queue.add(new int[]{ny, nx});
            }
        }
//        printArr(visit);
        for (int y = 1; y <= H; y++) {
            for (int x = 1; x <= W; x++) {
                if (!visit[y][x]) arr[y][x] = true;
            }
        }
        //System.out.println();
        //printArr(arr);
    }

    public static int calc() {
        int answer = 0;
        for (int y = 1; y <= H; y++) {
            for (int x = 1; x <= W; x++) {
                int cnt = 6;
                if (arr[y][x]) {

                    if (y % 2 == 0) { // 짝수
                        for (int d = 0; d < 6; d++) {
                            if (arr[y + dy1[d]][x + dx1[d]]) cnt--;
                        }
                    } else { //홀수
                        for (int d = 0; d < 6; d++) {
                            if (arr[y + dy2[d]][x + dx2[d]]) cnt--;
                        }
                    }
                    answer += cnt;
                }
            }
        }
        return answer;
    }

    public static void printArr(boolean[][] arr) {
        for (int y = 1; y < H + 1; y++) {
            for (int x = 1; x < W + 1; x++) {
                if (arr[y][x]) System.out.print(1 + " ");
                else System.out.print(0 + " ");
            }
            System.out.println();
        }
    }
}