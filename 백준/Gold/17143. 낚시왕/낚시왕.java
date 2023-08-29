import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 상어는 크기와 속도를 가지고 있음
 * 낚시왕은 처음에 1번 열의 한칸 왼쪽에서 시작해서
 * 가장 오른쪽 열의 오른쪽 칸에 이동하면 이동을 멈춘다.
 * <p>
 * 낚시왕이 오른쪽으로 한칸 이동
 * 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
 * 상어가 이동한다.
 * 한 칸에 두마리 이상의 상어가 있는 경우 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
 * 낚시왕이 잡은 상어 크기의 합을 출력해라
 * [주어지는 입력값]
 * R * C : 격자판의 크기
 * M : 상어의 수
 * r : 상어의 위치
 * c : 상어의 위치
 * s : 속력
 * d : 이동방향 (1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽)
 * z : 크기
 * <p>
 * [필요한 기능]
 * 1. 초마다 사람 움직이기
 * 2. 사람이 같은 열의 상어 잡기
 * 3. 상어의 이동
 * 4. 상어가 같은 행, 열에 있는지 체크
 * 5. 크기가 큰 상어가 작은 상어를 잡아먹기
 */
public class Main {

    static int R, C, M;
    static Shark[][] arr;
    static List<Shark> sharks;
    static int totalShark;
    static int[] dx = {1, 0, -1, 0}; //하우상좌
    static int[] dy = {0, 1, 0, -1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new Shark[R + 1][C + 1];
        sharks = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());  // 1 상 -> 2, 2 하 -> 0 , 3 우 - > 1, 4 좌 -> 3
            int z = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r, c, s, d, z);
            sharks.add(shark);
            arr[r][c] = shark;
        }
        totalShark = sharks.size();  // 전체 상어 수 저장
        moveHuman();
        System.out.println(answer);
    }

    public static void moveHuman() {
        for (int humanPosY = 1; humanPosY <= C; humanPosY++) {
            if (totalShark == 0) break;
            // 상어 잡기
            answer += hunting(humanPosY);
            // 상어 이동
            moveShark();
        }
    }

    // 상어 잡기
    public static int hunting(int y) {
        for (int x = 1; x <= R; x++) {
            if (arr[x][y] != null) {
                int size = arr[x][y].size;
                sharks.remove(arr[x][y]);  // 상어 리스트배열에서 제거
                totalShark--;
                arr[x][y] = null;  // 배열에서도 제거
                return size;
            }
        }
        return 0;  //잡지 못한 경우
    }

    public static void moveShark() {
        //상어 전체 이동
        for (int i = 0; i < sharks.size(); i++) {
            Shark cur = sharks.get(i);
            arr[cur.x][cur.y] = null;
            calcSharkPos(cur);
        }

        for (int i = 0; i < sharks.size(); i++) {
            Shark shark = sharks.get(i);
            if (arr[shark.x][shark.y] == null) { // 아무도 없으므로 상어는 다른상어에 의해 잡아먹히지 않는다.
                arr[shark.x][shark.y] = shark;
            } else {  // 이미 상어가 존재. 사이즈가 큰상어에게 잡혀먹는다.
                if (shark.size > arr[shark.x][shark.y].size) {
                    sharks.remove(arr[shark.x][shark.y]);
                    arr[shark.x][shark.y] = shark;
                } else {
                    sharks.remove(shark);
                }
                totalShark--;
                i--;
            }
        }
    }

    //상어의 다음 좌표 구하기 (상우하좌)   방향이 바뀌는 경우 (dir + 2) % 4
    public static Shark calcSharkPos(Shark shark) {
        int speed = shark.speed;
        int sharkDir = shark.dir;
        int sharkX = shark.x;
        int sharkY = shark.y;

        for (int i = 0; i < speed; i++) {
            int nx = sharkX + dx[sharkDir];
            int ny = sharkY + dy[sharkDir];

            if (!boarderCheck(nx, ny)) {
                sharkDir = (sharkDir + 2) % 4;
                nx = sharkX + dx[sharkDir];
                ny = sharkY + dy[sharkDir];
            }
            sharkX = nx;
            sharkY = ny;
        }
        shark.dir = sharkDir;
        shark.x = sharkX;
        shark.y = sharkY;
        return shark;
    }

    public static boolean boarderCheck(int x, int y) {
        return x > 0 && y > 0 && x <= R && y <= C;
    }

    static class Shark {
        int x;
        int y;
        int speed;
        int dir;
        int size;

        public Shark(int x, int y, int speed, int dir, int size) {
            if (dir == 1) dir = 2;
            else if (dir == 2) dir = 0;
            else if (dir == 3) dir = 1;
            else if (dir == 4) dir = 3;

            if (dir % 2 == 0) {  // 상어가 상하로 움직이는 경우
                speed %= 2 * (R - 1);
            } else {  // 상어가 좌우로 움직이는 경우
                speed %= 2 * (C - 1);
            }
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }


    }
}