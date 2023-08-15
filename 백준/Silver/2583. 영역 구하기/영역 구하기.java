import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[][] board;
    static int N, M, K;
    static int cnt = 0;
    static List<Integer> answers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new boolean[N][M];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken());
            int sx = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            fillRectangle(sx, sy, ex, ey);
        }

        // BFS로 탐색
        BFS();
        // 정렬
        Collections.sort(answers);
        //출력
        System.out.println(cnt);
        for(int i = 0; i < answers.size(); i++)
            System.out.print(answers.get(i) + " ");
        System.out.println();
    }

    public static void BFS() {
        int[] dx = {-1, 1, 0, 0};  // 상하좌우
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> queue;
        
        for(int i = 0; i< N; i++) {
            for(int j = 0; j < M; j++) {
                if(!board[i][j]) {
                    board[i][j] = true;
                    queue = new ArrayDeque<>();
                    cnt++;
                    queue.add(new int[] {i, j});
                    int size = 1;
                    while(!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for(int k = 0; k < 4; k++) {
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];

                            if(nx < 0 || ny < 0 || nx >= N || ny >= M || board[nx][ny]) continue;

                            board[nx][ny] = true;
                            queue.add(new int[] {nx, ny});
                            size++;
                        }
                    }
                    answers.add(size);
                }
            }
        }
    }

    public static void fillRectangle(int sx, int sy, int ex, int ey) {
        for(int i = sx; i < ex; i++) {
            for(int j = sy; j < ey; j++) {
                board[i][j] = true;
            }
        }
    }

}