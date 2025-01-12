import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] arr;
    static BufferedReader br;
    static final int INF = 98765432;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        initArr();
        map = new int[N][N * 2];
        BFS();
        printAnswer();
    }

    public static void BFS() {
        for (int i = 0; i < N; i++) Arrays.fill(map[i], INF);
        map[0][0] = map[0][1] = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            if(curIdx == N * N - N/2 - 1) break;
            int[] left = getLeftPoint(curIdx);
            int val = map[left[0]][left[1]];
            int[] right = {left[0], left[1] + 1};
            for (int d = 0; d < 6; d++) {
                int[] p;
                int nx, ny;
                if (d == 0) { // 왼쪽 val; 아래
                    p = left;
                    nx = left[0] + 1;
                    ny = left[1];
                } else if (d == 1) { // 오른쪽 val; 아래
                    p = right;
                    nx = right[0] + 1;
                    ny = right[1];
                } else if (d == 2) { // 오른쪽 val; 오른쪽
                    p = right;
                    nx = right[0];
                    ny = right[1] + 1;
                } else if (d == 3) { // 오른쪽 val; 위
                    p = right;
                    nx = right[0] - 1;
                    ny = right[1];
                } else if (d == 4) { // 왼쪽 val; 위
                    p = left;
                    nx = left[0] - 1;
                    ny = left[1];
                } else { // 왼쪽 val; 왼쪽
                    p = left;
                    nx = left[0];
                    ny = left[1] - 1;
                }

                if (OOB(nx, ny)) continue;
                if (arr[nx][ny] != arr[p[0]][p[1]]) continue;
                if (map[nx][ny] <= val + 1) continue;

                int nextIdx = getArrIdx(nx, ny);
                int[] next = getLeftPoint(nextIdx);
                map[next[0]][next[1]] = val + 1;
                map[next[0]][next[1] + 1] = val + 1;
                queue.add(nextIdx);
            }
        }
    }

    public static void printAnswer() {
        for (int tile = N * N - N / 2 - 1; tile >= 0; tile--) {
            int[] p = getLeftPoint(tile);
            if (map[p[0]][p[1]] == INF) continue;
            sb.append(map[p[0]][p[1]]).append("\n");
            findWay(tile, map[p[0]][p[1]]);
            return;
        }
    }

    public static void findWay(int idx, int size) {
        int curIdx = idx;
        List<Integer> way = new ArrayList<>(List.of(idx));
        int curVal = size;
        for (int i = 0; i < size; i++) {
            int[] left = getLeftPoint(curIdx);
            int[] right = {left[0], left[1] + 1};

            for (int d = 0; d < 6; d++) {
                int[] p;
                int nx, ny;
                if (d == 0) { // 왼쪽 val; 아래
                    p = left;
                    nx = left[0] + 1;
                    ny = left[1];
                } else if (d == 1) { // 오른쪽 val; 아래
                    p = right;
                    nx = right[0] + 1;
                    ny = right[1];
                } else if (d == 2) { // 오른쪽 val; 오른쪽
                    p = right;
                    nx = right[0];
                    ny = right[1] + 1;
                } else if (d == 3) { // 오른쪽 val; 위
                    p = right;
                    nx = right[0] - 1;
                    ny = right[1];
                } else if (d == 4) { // 왼쪽 val; 위
                    p = left;
                    nx = left[0] - 1;
                    ny = left[1];
                } else { // 왼쪽 val; 왼쪽
                    p = left;
                    nx = left[0];
                    ny = left[1] - 1;
                }

                if (OOB(nx, ny)) continue;
                if (arr[nx][ny] != arr[p[0]][p[1]]) continue;
                if (map[nx][ny] != curVal - 1) continue;
                curIdx = getArrIdx(nx, ny);
                curVal--;
                way.add(curIdx);
            }


        }
        for (int i = way.size() - 1; i >= 0; i--) sb.append(way.get(i) + 1).append(" ");
        System.out.print(sb);
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= 2 * N;
    }

    public static int getArrIdx(int x, int y) {
        int idx = x / 2 * (N + N - 1);
        if (x % 2 == 1) {
            if (y == 0 || y == 2 * N - 1) return -1;
            idx += (N - 1) + (y + 1) / 2;
        } else {
            idx += y / 2;
        }
        return idx;
    }

    public static int[] getLeftPoint(int idx) {
        int x = idx / (N + N - 1) * 2;
        int y = idx % (N + N - 1);

        if (y < N) return new int[]{x, 2 * y};
        else return new int[]{x + 1, (y - N) * 2 + 1};
    }


    public static void initArr() throws IOException {
        arr = new int[N][N * 2];
        boolean flag = true;
        int idx = 0;
        for (int i = 0; i < N * N - N / 2; ) {
            if (flag) {
                arr[idx++] = getInput(N);
                i += N;
            } else {
                arr[idx++] = getInput(N - 1);
                i += N - 1;
            }
            flag = !flag;
        }
    }

    public static int[] getInput(int size) throws IOException {
        int[] result = new int[2 * N];
        StringTokenizer st;
        if (size == N) {
            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                result[i * 2] = a;
                result[i * 2 + 1] = b;
            }
        } else {
            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                result[i * 2 + 1] = a;
                result[i * 2 + 2] = b;
            }
        }
        return result;
    }

}