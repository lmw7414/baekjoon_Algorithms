import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * 배열의 크기는 N * M이고
 * 성의 위치는 N + 1의 위치에 있다.
 * -> 따라서 배열의 크기를((N+1) * M) 크기로 만들자
 *
 * 궁수는 매턴 사정 거리에 있는 가장 가까운 적을 죽일 수 있다.
 * 궁수의 공격이 끝나면 적은 아래로 한칸 내려온다.
 * 턴은 N으로 볼 수 있고, N의 턴이 모두 끝났을 때 궁수가 최대로 죽일 수 있는 적의 수를 찾아야 한다.
 *
 *  [문제 해결 프로세스]
 *  궁수의 주어진 자리 중 3명으로 만들 수 있는 조합을 구한다.
 *  구하였으면 N의 턴동안 죽일 수 있는 적의 수를 계산한다.
 *  궁수는 사정거리 내의 가장 가까운 적을 죽일 수 있으며, 가장 가까운 적이 2명인 경우 가장 왼쪽 적을 죽인다.
 *  현재 최대값보다 죽인 적의 수가 많다면 값을 바꿔준다.
 *
 */

public class Main {

    static int N, M, D;  // 행의 수, 열의 수, 궁수의 공격거리 제한
    static final int MAX_ARCHER = 3;
    static final int INF = 100_000_0;
    static int answer = 0;  // 현재의 최댓값
    static int[][] game;  // 입력받을 배열
    static int[][] temp;
    static Point[] archers = new Point[MAX_ARCHER];  // 조합을 저장할 배열

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        game = new int[N + 1][M];  // 맨 마지막 N행은 성의 위치

        // 배열 입력받기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                game[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bitMaskingCombination(0, 0, 0);
        System.out.println(answer);
    }


    //3자리 조합만드는 메서드 - 비트마스킹
    public static void bitMaskingCombination(int idx, int depth, int flag) {
        // 조합이 완성되면 해야할 프로세스
        // 1. copyArr();
        // 2. 죽인 적의 숫자를 0으로 초기화
        // 3. 적 죽이기
        // 4. 턴 N만큼 진행
        // 5. 완료되고 최댓값 계산
        if(depth == MAX_ARCHER) {
            copyArr();
            int cnt = 0;
            for(int turn = 0; turn < N; turn++ ) {
                cnt += killEnemy(temp, turn);
                nextTurn();
            }
            answer = Math.max(answer, cnt);
            return;
        }
        for(int i = idx; i < M; i++) {
            if((flag & 1 << i) != 0) continue;
            archers[depth] = new Point(N, i);
            bitMaskingCombination(idx + 1, depth + 1, flag | 1 << i);
        }

    }

    // 거리 계산 메서드
    public static int calcDistance(int archerX, int archerY, int enemyX, int enemyY ) {
        return Math.abs(archerX - enemyX) + Math.abs(archerY - enemyY);
    }

    // 원본 배열 복사 메서드 -- 조합 완성되고 사용
    public static void copyArr() {
        temp = new int[N + 1][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++)
                temp[i][j] = game[i][j];
        }
    }

    // 적이 아래로 내려오는 메서드
    // 적이 궁수에 의해 죽고 난 후 실행
    public static void nextTurn() {
        // x = 0인 행을 0으로 모두 초기화
        //Arrays.fill(temp[0], 0);
        // x = N - 1 인 행을 0으로 모두  초기화
        //Arrays.fill(temp[N - 1], 0);
        for(int i = N - 1; i > 0; i--) {
            temp[i] = temp[i - 1];
        }
        temp[0] = new int[M];
    }
    // 궁수가 적을 죽이는 메서드
    public static int killEnemy(int[][] fields, int turn) {
        Set<Point> hs = new HashSet<>();
        Point[] kill = new Point[MAX_ARCHER];
        int archerIdx = 0;
        for(Point archer : archers) {
            int distance = INF;
            int preJ = INF;
            pt : for(int i = N - 1; i >= turn; i-- ) {
                // 궁수 바로 앞에 적이 있다면 가장 짧은 거리
                if(fields[i][archer.y] == 1) {
                    int cd = calcDistance(archer.x, archer.y, i, archer.y);
                    if(D < cd) continue;
                    if(distance > cd) {
                        distance = cd;
                        preJ = archer.y;
                        kill[archerIdx] = new Point(i, archer.y);
                    } else if(distance == cd) {
                        if(preJ > archer.y) {
                            preJ = archer.y;
                            kill[archerIdx] = new Point(i, archer.y);
                        }
                    }
                }
                // 궁수 바로 앞에 없고 왼쪽에 위치한 적이 있다면 해당 위치가 가장 짧은 거리
                for(int j = archer.y - 1; j >= 0; j--) {
                    if(fields[i][j] == 1) {
                        int cd = calcDistance(archer.x, archer.y, i, j);
                        if(D < cd) continue;
                        if(distance > cd) {
                            distance = cd;
                            preJ = j;
                            kill[archerIdx] = new Point(i, j);
                        } else if(distance == cd) {
                            if(preJ > j) {
                                preJ = j;
                                kill[archerIdx] = new Point(i, j);
                            }
                        }
                    }
                }
                // 궁수 왼쪽에도 없고 오른쪽에 있다면 해당 위치가 가장 짧은 거리
                for(int j = archer.y + 1; j < M; j++) {
                    if(fields[i][j] == 1) {
                        int cd = calcDistance(archer.x, archer.y, i, j);
                        if(D < cd) continue;

                        if(distance > cd) {
                            distance = cd;
                            preJ = j;
                            kill[archerIdx] = new Point(i, j);
                        } else if(distance == cd) {
                            if(preJ > j) {
                                preJ = j;
                                kill[archerIdx] = new Point(i, j);
                            }
                        }
                    }
                }
                // 해당 라인에 아무도 없다면 다음 줄로

            }
            archerIdx++;
        }
        for(int i = 0; i < MAX_ARCHER; i++) {
            if(kill[i] == null) continue;
            hs.add(kill[i]);
            fields[kill[i].x][kill[i].y] = 0;
        }

        return hs.size();
    }

}