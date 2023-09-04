import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제설명]
 * 여러 섬으로 이루어진 나라
 * 대통령의 공략으로 섬을 잇는 다리를 만들겠다 함
 * 한 섬과 다른 섬을 잇는 다리 하나만 만들고, 다리는 가장 짧게 하여 돈을 아끼려 함
 * 지도가 주어질 때, 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법을 찾자
 * <p>
 * [입력]
 * N: 지도의 크기(100이하의 자연수)
 * 0: 바다
 * 1: 육지
 * <p>
 * [문제 해결 프로세스]
 * 섬에 번호를 붙여 구분한다.
 * BFS탐색
 * 섬의 바깥쪽을 미리 큐에 넣는다
 * 해당 지점에서부터 너비 우선 탐색을 진행하며
 * 처음 섬을 만났을 때 그 결과를 전역변수로 저장
 * 모든 섬에 대해 BFS 반복
 * <p>
 * [필요한 메서드]
 * 섬 분류하기
 * - 1번은 이미 되었으니 2번부터 채우자
 * 섬 당 BFS로 최소 거리 구하기
 * - 최소 거리를 저장할 때 [시작섬, 끝섬, 거리], [끝섬, 시작섬, 거리]로 저장한다면 반복을 피할 수 있지 않을까?
 * - 아니다. 어차피 다른 섬에 대해서도 최소 거리를 구해야하니 의미가 없다.
 */
public class Main {

    static int N;
    static final int INF = Integer.MAX_VALUE;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};  //상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int islandCnt = separateIsland();

        for(int i = 1; i <= islandCnt; i++) {
            answer = Math.min(answer, findMinDist(i));
        }
        System.out.println(answer);
        //printArr();
    }

    public static int findMinDist(int startIsland) {
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] distance = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(distance[i], INF);

        // 섬 전체 큐에 삽입
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == startIsland) {
                    queue.add(new int[]{i, j});
                    distance[i][j] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;  //범위체크
                if (distance[cur[0]][cur[1]] + 1 < distance[nx][ny]) {
                    distance[nx][ny] = distance[cur[0]][cur[1]] + 1;
                    if (arr[nx][ny] != 0 && arr[nx][ny] != startIsland) return distance[cur[0]][cur[1]];
                    queue.add(new int[] {nx, ny});
                }
            }
        }
        return INF;

    }

    public static int separateIsland() {
        boolean[][] visit = new boolean[N][N];
        int islandIdx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1 && !visit[i][j]) {
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    visit[i][j] = true;
                    arr[i][j] = islandIdx;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny]) continue;
                            if (arr[nx][ny] == 0) continue;
                            arr[nx][ny] = islandIdx;
                            queue.add(new int[]{nx, ny});
                            visit[nx][ny] = true;
                        }
                    }
                    islandIdx++;
                }
            }
        }
        return islandIdx;
    }

    public static void printArr() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}