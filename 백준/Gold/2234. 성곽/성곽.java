
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[] dx = {0, -1, 0, 1}; // 서북동남
    static int[] dy = {-1, 0 , 1, 0};
    static int second, third;
    static int[][] territory;
    static List<Integer> tSize = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        territory = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        makeTerritory();
        System.out.println(tSize.size());
        System.out.println(second);
        getThird();
        System.out.println(third);
    }

    public static void makeTerritory() {
        int id = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(territory[i][j] != 0) continue;
                int cnt = 1;
                territory[i][j] = id;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i, j});

                while(!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for(int d = 0; d < 4; d++) {
                        int nx = cur[0] + dx[d];
                        int ny = cur[1] + dy[d];
                        if(OOB(cur[0], cur[1], d)) continue;
                        if(territory[nx][ny] != 0) continue;
                        cnt++;
                        territory[nx][ny] = id;
                        queue.add(new int[]{nx, ny});
                    }
                }
                tSize.add(cnt);
                second = Math.max(second, cnt);
                id++;
            }
        }
    }

    static void getThird() {
        boolean[][] visit = new boolean[N][M];
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(set.contains(territory[i][j])) continue;
                int key = territory[i][j];
                set.add(key);
                visit[i][j] = true;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{i, j});
                while(!queue.isEmpty()) {
                    int[] cur = queue.poll();

                    for(int d = 0; d < 4; d++) {
                        int nx = cur[0] + dx[d];
                        int ny = cur[1] + dy[d];
                        if(OOB2(nx,ny)) continue;
                        if(visit[nx][ny]) continue;
                        if(territory[nx][ny] != key) { // 타 구역이라면
                            third = Math.max(third, tSize.get(key - 1) + tSize.get(territory[nx][ny] - 1));
                        } else {
                            visit[nx][ny] = true;
                            queue.add(new int[]{nx, ny});
                        }

                    }
                }

            }
        }
    }

    static boolean OOB(int curX, int curY, int d) {
        return (arr[curX][curY] & (1 << d)) != 0;
    }

    static boolean OOB2(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

}