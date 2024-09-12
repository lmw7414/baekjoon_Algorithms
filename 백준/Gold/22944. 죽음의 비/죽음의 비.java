import java.util.*;
import java.io.*;

public class Main {

    static int N, H, K;
    static char[][] arr;
    static int[][] visit;
    static int answer = Integer.MAX_VALUE;
    static int sx, sy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new int[N][N];
        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if (str.charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                }
                arr[i][j] = str.charAt(j);
            }
        }
        calc(sx, sy);
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    public static void calc(int x, int y) {
        Queue<Pos> queue = new ArrayDeque<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        visit[x][y] = H;
        queue.add(new Pos(x, y, H, 0, 0));

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            for(int d = 0;  d< 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                int hp = cur.h;
                int hd = cur.d;

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if(arr[nx][ny] == 'E') { // 출구
                    answer = Math.min(answer, cur.dist + 1);
                    continue;
                }

                // 체력 감소
                if(hd > 0) hd--;
                else hp--;
                if(hp == -1) continue;


                if(arr[nx][ny] == 'U') { // 우산
                    hd = K;
                }
                if(visit[nx][ny] < hp + hd) {
                    visit[nx][ny] = hp + hd;
                    queue.add(new Pos(nx, ny, hp, hd, cur.dist + 1));
                }
            }
        }

    }

    static class Pos {
        int x;
        int y;
        int h;
        int d;
        int dist;

        public Pos(int x, int y, int h, int d, int dist) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.d = d;
            this.dist = dist;
        }
    }
}