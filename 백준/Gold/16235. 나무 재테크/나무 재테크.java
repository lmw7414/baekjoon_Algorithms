import java.util.*;
import java.io.*;

/**
 * [문제 설명]
 * 가장 처음의 양분은 모든 칸에 5만큼 들어 있음
 * M개의 나무를 심어서 팔려고 함
 * 1. 봄: 나무가 자신의 나이만큼 양분을 먹고, 1 증가
 * - 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹음
 * - 만약 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 바로 죽음
 * 2. 여름: 죽은 나무가 양분으로 변함. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가(소수점 아래는 버림)
 * 3. 가을: 나무 번식, 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개 칸에 나이가 1인 나무가 생김(8방)
 * 4. 겨울: 로봇이 돌아다니면서 양분 추가
 * -> K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성해라
 * [입력]
 * N: 최대 10 (배열 크기)
 * M: 최대 100 (구매할 나무 개수)
 * K: 최대 1000년
 * A[r][c]: 최대 100
 * [문제 해결 프로세스]
 * 배열 -> 각 칸을 나무를 담을 수 있는 리스트로 설정
 * 4가지 함수: 봄 여름 가을 겨울
 */

public class Main {
    static int N, M, K;
    static int[][] arr, energy; // 각 칸에 추가되는 양분의 양
    static List<Integer>[][] trees;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        trees = new List[N + 1][N + 1];
        energy = new int[N + 1][N + 1];
        arr = new int[N + 1][N + 1];

        // 영양분 기록
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                trees[i][j] = new ArrayList<>();
                arr[i][j] = 5;
                energy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees[x][y].add(z);
            answer++;
        }

        for (int k = 0; k < K; k++) {
            int[][] deadIdx = spring();
            summer(deadIdx);
            autumn();
            winter();
        }
        System.out.println(answer);
    }

    public static int[][] spring() {
        int[][] deadIdx = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(deadIdx[i], 1001); // 최대 나이로 초기화

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (trees[i][j].isEmpty()) continue; // 해당 구역에 나무가 없을 경우
                Collections.sort(trees[i][j]); // 어린 나무부터 양분 흡수하기 위한 정렬
                for (int t = 0; t < trees[i][j].size(); t++) {
                    int age = trees[i][j].get(t);
                    if (arr[i][j] >= age) {
                        arr[i][j] -= age;
                        trees[i][j].set(t, age + 1);
                    } else { // 양분이 없는 경우
                        deadIdx[i][j] = t;
                        break;
                    }
                }
            }
        }
        return deadIdx;
    }

    public static void summer(int[][] dead) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dead[i][j] == 1001) continue; // 죽은 나무가 없는 경우
                int idx = dead[i][j];
                for (int id = idx; id < trees[i][j].size(); id++) {
                    arr[i][j] += trees[i][j].get(id) / 2;
                }
                answer -= trees[i][j].size() - idx;
                trees[i][j].subList(idx, trees[i][j].size()).clear();
            }
        }
    }

    public static void autumn() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (trees[i][j].isEmpty()) continue; // 죽은 나무가 없는 경우
                for (int age : trees[i][j]) {
                    if (age % 5 != 0) continue;
                    for (int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (OOB(nx, ny)) continue;
                        trees[nx][ny].add(1);
                        answer++;
                    }
                }
            }
        }
    }

    public static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] += energy[i][j];
            }
        }
    }

    public static boolean OOB(int x, int y) {
        return x < 1 || y < 1 || x > N || y > N;
    }
}