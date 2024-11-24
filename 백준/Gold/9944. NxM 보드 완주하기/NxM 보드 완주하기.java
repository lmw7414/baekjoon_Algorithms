import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N, M;
    static char[][] map;
    static boolean[][] visit;
    static int INF = 1_000_000;
    static int answer = INF;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = "";
        int tc = 1;
        while ((str = br.readLine()) != null && !str.isEmpty()) {
            String[] nm = str.split(" ");
            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);
            map = new char[N][M];
            visit = new boolean[N][M];
            answer = INF;
            int visitCnt = 0;
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    if (line.charAt(j) == '*') {
                        visit[i][j] = true;
                        visitCnt++;
                    }
                    map[i][j] = line.charAt(j);
                }
            }

            // 1. 돌 위치 정하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '*') continue;
                    // 방문 체크
                    visit[i][j] = true;
                    DFS(i, j, visitCnt + 1, 0);
                    visit[i][j] = false;
                    // 방문 해제
                }
            }
            sb.append("Case ").append(tc++).append(": ");
            if (answer == INF) sb.append(-1).append('\n');
            else sb.append(answer).append('\n');
        }
        System.out.print(sb);

    }

    // 다음 위치, 방문 개수, 현재 단계
    public static void DFS(int x, int y, int visitCnt, int cnt) {
        if (cnt >= Math.min(INF, answer)) return;
        if (visitCnt == N * M) {
            answer = Math.min(answer, cnt);
            return;
        }
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (OOB(nx, ny)) continue;
            if (visit[nx][ny]) continue;

            List<int[]> way = move(nx, ny, d);
            // 방문 처리
            for (int[] p : way) visit[p[0]][p[1]] = true;
            int[] cur = way.get(way.size() - 1);
            DFS(cur[0], cur[1], visitCnt + way.size(), cnt + 1);
            // 방문 취소
            for (int[] p : way) visit[p[0]][p[1]] = false;
        }
    }

    public static List<int[]> move(int x, int y, int d) {
        List<int[]> way = new ArrayList<>();
        way.add(new int[]{x, y});
        int nx = x + dx[d];
        int ny = y + dy[d];
        while (!OOB(nx, ny) && !visit[nx][ny]) {
            way.add(new int[]{nx, ny});
            nx += dx[d];
            ny += dy[d];
        }
        return way;
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

}