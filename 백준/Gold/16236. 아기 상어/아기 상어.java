import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제 설명]
 * N x N 크기의 공간에 물고기 M마리, 아기상어 1마리가 있다.
 * 한칸에는 최대 1마리의 물고기가 존재
 * 아기 상어와 물고기는 모두 크기를 가지고 있음
 * 최초의 아기상어 크기는 2
 * 아기상어는 1초에 상하좌우로 인접한 한칸씩 이동
 * 아기상어는 자신보다 큰 크기의 물고가 있는 칸은 지나갈 수 없다.
 * 아기상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
 * 즉, 크기가 같은 물고기는 먹을 수 없다.
 * 즉, 하지만 크기가 같은 물고기의 칸은 지나갈 수 있다.
 *
 * 아기상어가 어디로 이동하는지 결정하는 조건
 * 1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기상어는 엄마상어에게 요청
 * 2. 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 감
 * 3. 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 감
 * 4. 거리는 아기상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야 하는 칸의 개수가 최솟값
 * 5. 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 가장 왼쪽에 있는 물고기를 먹는다.
 *
 * 이동과 동시에 아기상어는 물고기를 잡아먹는다.
 * 물고기를 먹으면 해당 칸은 빈칸이 된다.
 * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가
 * ex) 아기상어 크기 2이면 물고기를 2마리 먹어야 3이 됨
 *
 * -> 아기 상어가 몇초동안 엄마상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지
 *
 * [입력]
 * N : 배열 크기 2 <= N <= 20
 * 0 : 빈칸
 * 1~6 : 물고기 크기
 * 9 : 아기상어의 위치
 *
 *
 * [문제 해결 프로세스]
 * 1. 자신보다 작은 물고기를 찾는다.
 * 2. 찾은 물고기들의 거리를 계산
 *  - 가장 가까운 물고기가 있는지
 *  - 가까운 물고기가 여러마리라면
 *      - 위에 있는 물고기
 *      - 왼쪽에 있는 물고기
 * 3. 2부터 시작해서 2마리(3) -> 3마리(4) -> 4마리(5) ... 로 상어의 사이즈 증가
 * 4. 먹을 물고기가 없다면 프로그램 종료
 *
 * * 물고기를 리스트로 관리(크기를 기준으로 오름차순) -> 물고기의 정보를 담는 X, Y좌표, 크기 클래스 생성할 것
 * * BFS로 현재 상어의 위치로 부터 모든 물고기의 거리 탐색
 * * 리스트를 돌며 자신보다 작은 사이즈의 물고기를 PQ에 저장(위의 BFS 결과를 보고)
 * * PQ의 저장 순서는 거리가 가까운 순서로, 그리고 가깝다면, X좌표는 작은 숫자부터 ,Y좌표가 가장 왼쪽인 물고기로
 * * 리스트가 비어있거나, 가장 앞에 있는 물고기의 사이즈가 아기 상어의 사이즈보다 큰 경우 프로그램 종료
 */

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[][] arr;
    static Fish shark;
    static int sharkSizeCnt = 0;
    static int answer = 0;
    static List<Fish> fishes = new ArrayList<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        // 배열 및 물고기 리스트 초기화
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int size = Integer.parseInt(st.nextToken());
                arr[i][j] = size;
                if (size == 9) // 상어라면
                    shark = new Fish(i, j, 2);
                else if (size != 0)  // 물고기라면
                    fishes.add(new Fish(i, j, size));
            }
        }

        // 물고기 리스트 크기 순으로 정렬
        Collections.sort(fishes);

        //리스트의 물고기가 없거나, 아기 상어보다 작은 물고기가 없을 때까지 반복
        //BFS로 거리 탐색
        // 가장 가까운 물고기 찾기
        while (eatFish()) ;
        System.out.println(answer);
    }

    public static int[][] BFS(Fish shark) {
        int[][] distance = new int[N][N];
        int[] dx = {-1, 1, 0, 0}; // 상하좌우
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < N; i++) Arrays.fill(distance[i], INF);
        distance[shark.x][shark.y] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{shark.x, shark.y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue; //경계 체크
                if (arr[nx][ny] != INF && shark.size < arr[nx][ny]) continue; //나보다 큰 크기의 물고기인 경우;

                if (distance[cur[0]][cur[1]] + 1 < distance[nx][ny]) {
                    distance[nx][ny] = distance[cur[0]][cur[1]] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return distance;
    }

    // PQ의 저장 순서는 거리가 가까운 순서로, 그리고 가깝다면, X좌표는 작은 숫자부터 ,Y좌표가 가장 왼쪽인 물고기로
    public static Fish find(int[][] distance) {
        PriorityQueue<Fish> pq = new PriorityQueue<>((a1, b1) -> {
            if (distance[a1.x][a1.y] == distance[b1.x][b1.y]) {
                if (a1.x == b1.x) return a1.y - b1.y;  // x좌표가 같다면 y좌표가 왼쪽인 물고기 부터
                return a1.x - b1.x; // x좌표가 작은 위에 있는 물고기부터
            }
            return distance[a1.x][a1.y] - distance[b1.x][b1.y]; // 거리 기준 오름차순
        });

        for (Fish fish : fishes) {
            if(distance[fish.x][fish.y] == INF) continue; // 경로가 없다면 넘어가기
            if (fish.size < shark.size) {  // 물고기가 상어보다 작다면 상어에게 먹힌다.
                pq.add(fish);
            }
        }

        return pq.poll();
    }

    // true일 경우 상어가 사냥에 성공
    // false일 경우 상어가 먹을 물고기가 없음
    public static boolean eatFish() {
        int[][] distance = BFS(shark);
        Fish del = find(distance);
        if (del == null) return false;  // 먹을 물고기가 없는 경우

        // 정답에 잡은 물고기의 위치 거리만큼 증가
        answer += distance[del.x][del.y];
        arr[del.x][del.y] = 9;
        arr[shark.x][shark.y] = 0;
        sharkSizeCnt++;
        shark.x = del.x;
        shark.y = del.y;
        if (sharkSizeCnt == shark.size) {
            shark.size++;
            sharkSizeCnt = 0;
        }
        fishes.remove(del);
        return true;
    }

    static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int size;

        public Fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        @Override
        public int compareTo(Fish o) {
            return this.size - o.size;
        }
    }
}