import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * [조건]
 * 1. 시간제한 1초
 * 2. 메모리 제한 512MB
 * 3. 1 <= N <= 20
 * <p>
 * [문제 해결 프로세스]
 * 1. 상하좌우로 이동했을 때 모양 만들기
 * 2. 순열로 5단계에 대한 모든 방향 계산하기
 */

public class Main {
    static int[][] arr;
    static int[] permutation = new int[5];
    static int N;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }
        setPermutation(0);
        System.out.println(answer);
    }

    // 특정 방향으로 밀치기
    public static int[][] pushInDirection(int[][] copy, int dir) {
        int[][] copy2 = moveInDirection(copy, dir); // 밀쳐서 정렬
        if (dir == 0) {  // 위로 밀치기
            for (int i = 0; i < N - 1; i++) {
                for (int j = 0; j < N; j++) {

                    // 0이면 계산 할 필요 없음
                    if (copy2[i][j] == copy2[i + 1][j]) {
                        copy2[i][j] *= 2;
                        copy2[i + 1][j] = 0;
                    }
                }
            }
        } else if (dir == 1) {  // 아래로 밀치기
            for (int i = N - 1; i > 0; i--) {
                for (int j = 0; j < N; j++) {
                    if (copy2[i][j] == copy2[i - 1][j]) {
                        copy2[i][j] *= 2;
                        copy2[i - 1][j] = 0;
                    }
                }
            }
        } else if (dir == 2) {  // 좌로 밀치기
            for (int i = 0; i < N - 1; i++) {
                for (int j = 0; j < N; j++) {
                    if (copy2[j][i] == copy2[j][i + 1]) {
                        copy2[j][i] *= 2;
                        copy2[j][i + 1] = 0;
                    }
                }
            }
        } else {  // 우로 밀치기
            for (int i = N - 1; i > 0; i--) {
                for (int j = 0; j < N; j++) {
                    if (copy2[j][i] == copy2[j][i - 1]) {
                        copy2[j][i] *= 2;
                        copy2[j][i - 1] = 0;
                    }
                }
            }
        }
        return moveInDirection(copy2, dir); // 정리되었으니 다시 정렬
    }

    static int[][] moveInDirection(int[][] copy, int dir) {
        if (dir == 0) {  // 상
            for (int i = 0; i < N; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    if (copy[j][i] != 0) {
                        temp.add(copy[j][i]);
                        copy[j][i] = 0;
                    }
                }
                for (int j = 0; j < temp.size(); j++) {
                    copy[j][i] = temp.get(j);
                }
            }
        } else if (dir == 1) {  // 하
            for (int i = 0; i < N; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = N - 1; j >= 0; j--) {
                    if (copy[j][i] != 0) {
                        temp.add(copy[j][i]);
                        copy[j][i] = 0;
                    }
                }
                for (int j = 0; j < temp.size(); j++) {
                    copy[N - 1 - j][i] = temp.get(j);
                }
            }
        } else if (dir == 2) {  // 좌
            for (int i = 0; i < N; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    if (copy[i][j] != 0) {
                        temp.add(copy[i][j]);
                        copy[i][j] = 0;
                    }
                }
                for (int j = 0; j < temp.size(); j++) {
                    copy[i][j] = temp.get(j);
                }
            }
        } else {  // 우
            for (int i = 0; i < N; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = N - 1; j >= 0; j--) {
                    if (copy[i][j] != 0) {
                        temp.add(copy[i][j]);
                        copy[i][j] = 0;
                    }
                }
                for (int j = 0; j < temp.size(); j++) {
                    copy[i][N - j - 1] = temp.get(j);
                }
            }
        }
        return copy;
    }

    static int[][] copyArr() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++)
            copy[i] = arr[i].clone();
        return copy;
    }

    static int findMax(int[][] copy) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, copy[i][j]);
            }
        }
        return max;
    }

    static void setPermutation(int depth) {
        if (depth == 5) {
            // 초반 밀착 필요 4 0 4 0 인 경우 -> 4 4 0 0이 되게끔
            //int[][] copy = moveInDirection(copyArr(), permutation[0]);
            int[][] copy = copyArr();
            for (int i = 0; i < permutation.length; i++) {
                copy = pushInDirection(copy, permutation[i]);
            }
            answer = Math.max(answer, findMax(copy));
            return;
        }
        for (int i = 0; i < 4; i++) {
            permutation[depth] = i;
            setPermutation(depth + 1);
        }
    }
}