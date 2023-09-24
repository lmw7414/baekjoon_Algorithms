import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * 구역을 5개의 선거구로 나눠야하고, 각 구역은 다섯 선거구 중 하나로 포함되어야 한다.
 * 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다.
 * [입력값]
 * 5 <= N <= 20
 * <p>
 * [문제 해결 프로세스]
 * x,y,d1,d2를 모두 정해야 함
 * 결국 모든 경우의 수를 따져 봐야 한다.
 * x는 1부터 N까지
 * y도 1부터 N까지
 * d1도 1부터 N까지
 * d2도 1부터 N까지
 * x + d1 + d2가 N보다 크면 안된다.
 * y - d1 이 1보다 작으면 안된다.
 * y + d2가 N 보다 크면 안된다.
 * 5구역구가 정해지고 나면
 * 나머지 구역을 규칙대로 배열에 저장
 * 반복문 돌면서 인원수 확인
 * 최대에서 최소 빼기
 */


class Main {
    static int N;
    static int[][] arr;
    static int total = 0;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];


        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int input = Integer.parseInt(st.nextToken());
                arr[i][j] = input;
                total += input;
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        if (x + d1 + d2 <= N && 1 <= y - d1 && y + d2 <= N) {
                            findAnswer(x, y, d1, d2);
                        }
                    }
                }
            }
        }
        System.out.println(answer);

    }

    public static void findAnswer(int x, int y, int d1, int d2) {
        boolean[][] part = new boolean[N + 1][N + 1];
        int[] gary = new int[6];
        // 5번구역 체크하기
        // 우 상 방향
        for (int i = 0; i <= d1; i++) {
            part[x + i][y - i] = true;
            part[x + d2 + i][y + d2 - i] = true;
        }
        // 우 하 방향
        for (int i = 0; i <= d2; i++) {
            part[x + i][y + i] = true;
            part[x + d1 + i][y - d1 + i] = true;
        }

        // 1번 지역 인구수 계산하기
        for (int i = 1; i < x + d1; i++) {
            for (int j = 1; j <= y; j++) {
                if (part[i][j]) break;
                gary[1] += arr[i][j];

            }
        }
        // 2번 지역 인구수 계산하기
        for (int i = 1; i <= x + d2; i++) {
            for (int j = N; j > y; j--) {
                if (part[i][j]) break;
                gary[2] += arr[i][j];

            }
        }
        // 3번 지역 인구수 계산하기
        for (int i = x + d1; i <= N; i++) {
            for (int j = 1; j < y - d1 + d2; j++) {
                if (part[i][j]) break;
                gary[3] += arr[i][j];

            }
        }
        // 4번 지역 인구수 계산하기
        for (int i = x + d2 + 1; i <= N; i++) {
            for (int j = N; j >= y - d1 + d2; j--) {
                if (part[i][j]) break;
                gary[4] += arr[i][j];

            }
        }
        // 5번지역 - 전체지역에서 1~4 인구수 빼기
        gary[5] = total - (gary[1] + gary[2] + gary[3] + gary[4]);

        Arrays.sort(gary);
        answer = Math.min(answer, gary[5] - gary[1]);
    }
}