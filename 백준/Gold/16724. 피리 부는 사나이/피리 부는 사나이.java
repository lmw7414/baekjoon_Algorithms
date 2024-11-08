import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * [문제 해결 프로세스] union-find
 * 1. visit 초기 상태는 0
 * 2. 방문하지 않은 곳(0이면)이면 DFS를 통해 이동ID 부여 후 방향 대로 이동
 * 3. 이미 방문한 지점을 만난다면 break하고 parent 배열에 현재 ID 등록
 * 4. 다른 방문하지 않은 지점에서 다시 DFS
 * 5. 이동 도중 이미 방문한 지점에 갔을 때 해당 ID가 다르다면 parent 배열을 방문한 ID로 변경
 */

public class Main {
    static int M, N;
    static char[][] arr;
    static int[][] visit;
    static List<Integer> list = new ArrayList<>(List.of(0));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        N = Integer.parseInt(st[0]);
        M = Integer.parseInt(st[1]);
        arr = new char[N][M];
        visit = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        int id = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visit[i][j] == 0) {
                    int idx = DFS(i, j, id++);
                    list.add(idx);
//                    print();
//                    System.out.println();
                }
            }
        }

        HashSet<Integer> hs = new HashSet<>();
        for (int i = 1; i < id; i++) {
            hs.add(find(i));
        }
//        System.out.println();
        System.out.println(hs.size());
    }

    public static int DFS(int x, int y, int id) {
        if (visit[x][y] == 0) {
            visit[x][y] = id;
            int[] next = getPos(x, y);
            return visit[x][y] = DFS(x + next[0], y + next[1], id);
        }
        return visit[x][y];
    }


    public static int[] getPos(int x, int y) {
        if (arr[x][y] == 'D') {
            return new int[]{1, 0};
        } else if (arr[x][y] == 'L') {
            return new int[]{0, -1};
        } else if (arr[x][y] == 'R') {
            return new int[]{0, 1};
        } else {
            return new int[]{-1, 0};
        }
    }

    public static int find(int id) {
        if (id == list.get(id)) return id;
        return list.set(id, find(list.get(id)));
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%d ", visit[i][j]);
            }
            System.out.println();
        }
    }

}