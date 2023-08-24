import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * 두명의 일꾼은 가로로 연속되도록 M개의 벌통을 선택하고, 선택한 벌통에서 꿀을 채취할 수 있음
 * 단, 두명의 일꾼이 선택한 벌통은 서로 겹치면 안된다.
 *
 * 두명의 일꾼은 선택한 벌통에서 꿀을 채취하여 용기에 담아야 한다.
 * 단, 서로 다른 벌통에서 채취한 꿀이 섞이게 되면 상품가치가 떨어지게 되므로, 하나의 벌통에서 채취한 꿀은 하나의 용기에
 * 하나의 벌통에서 꿀을 채취할 때 벌통에 있는 모든 꿀을 담아야 함.
 *
 * 두 일꾼이 채취할 수 있는 꿀의 최대 양은 C
 *
 * [입력 받을 값]
 * N : 벌통들의 크기 N x N
 * M : 한 사람이 꿀을 채취할 수 있는 벌통의 수
 * C : 한 사람이 채취할 수 있는 꿀의 최대 양
 *
 *
 * [제약 사항]
 * Java 3초
 * 3 <= N <= 10
 * 1 <= M <= 5
 * M <= N
 * 10 <= C <= 30
 * 하나의 벌통에서 채취할 수 있는 꿀의 양 1 ~ 9
 *
 * [문제 해결 시나리오]
 * 1. 한 라인씩 테스트
 * 2. 한 라인에서 연속된 M개의 벌꿀을 픽
 * 3. 픽했으면 여기서 C값을 넘지 않는 선에서 최대값 찾기 -> 조합(mCr)
 * 4.
 */
public class Solution {

    static int tempAnswer = 0;
    static boolean[] subsetVisit;
    static int[] subsetArr;

    static int N, M, C;
    static int[][] arr;
    static PriorityQueue<Honey> pq;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            pq = new PriorityQueue<>((a1, b1) -> b1.score - a1.score);
            // 배열 초기화
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = M; j <= N; j++) {
                    findBest(i, j - M, j);
                }
            }
            System.out.println("#"+ tc + " " + calc());

        }
    }

    public static int calc() {
        int answer = 0;
        for(Honey first : pq) {
            for(Honey second : pq) {
                if(first == second) continue;
                if(isCollision(first, second)) continue;
                answer = Math.max(answer, first.score + second.score);
            }
        }
        return answer;
    }

    public static boolean isCollision(Honey first, Honey second) {
        if(first == null || second == null) return false;
        if(first.x != second.x) return false;
        if(second.startY >= first.startY && second.startY <= first.endY) return true;
        if(first.startY >= second.startY && first.startY <= second.endY) return true;
        return false;
    }

    public static int findBest(int x, int startY, int endY) {
        Honey honey = new Honey(x, startY, endY);
        subsetVisit = new boolean[endY - startY];
        subsetArr = new int[endY - startY];
        tempAnswer = 0;
        int idx = 0;
        // 부분집합
        for(int j = startY; j < endY; j++) {
            subsetArr[idx++] = arr[x][j];
        }
        subSet(0, endY - startY, 0);
        honey.score = tempAnswer;
        pq.add(honey);
        return honey.score;
    }

    public static void subSet(int depth, int size, int sum) {
        // C보다 크면 안됨
        if(depth == size) {
            if(sum > C) return;
            int answer = 0;
            for(int i = 0; i < size; i++) {
                if(subsetVisit[i]) answer += Math.pow(subsetArr[i], 2);
            }
            tempAnswer = Math.max(tempAnswer, answer);
            return;
        }
        subsetVisit[depth] = true;
        subSet(depth + 1, size, sum + subsetArr[depth]);
        subsetVisit[depth] = false;
        subSet(depth + 1, size, sum);
    }


    static class Honey {
        int x;
        int startY;
        int endY;
        int score;

        public Honey(int x, int startY, int endY) {
            this.x = x;
            this.startY = startY;
            this.endY = endY;
            this.score = 0;
        }
    }

}