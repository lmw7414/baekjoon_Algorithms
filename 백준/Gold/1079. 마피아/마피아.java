
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제 설명]
 * 1. 마피아 | 시민 그룹 | 참가자의 번호는 0부터 시작
 * 2. 짝수명: 밤; 마피아가 죽일 사람 한명, 죽은 사람은 게임에 참여 불가
 * 3. 홀수명: 낮; 참가자가 죽일 사람 한명
 * 4. 만약 마피아가 없다면 시민 승 | 시민이 없다면 마피아 승 | 그 즉시 종료
 * 5. 은진이가 마지막 남은 마피아
 * 6. 유죄지수 : 낮에 시민들이 어떤 참가자를 죽일 것인지 고를 때 쓰임
 */

public class Main {
    static int days = 0; // 가장 높은 경우를 골라야 함
    static int N; // 참가자수 최대 16명
    static int[] score; // 참가자의 유죄 지수
    static boolean[] dead;
    static int[][] R; // 유죄지수 테이블 i가 죽었다면 다른 참가자j의 유죄지수는 R[i][j]만큼 변함
    static int eunjin;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N];
        dead = new boolean[N];
        R = new int[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) score[n] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        eunjin = Integer.parseInt(br.readLine());
        calc(0, N);
        System.out.println(days);
    }

    public static void calc(int night, int survivor) {
        if (isEnd()) { // 은진이가 죽었거나, 시민 모두 죽었거나
            days = Math.max(days, night);
            return;
        }
        if (survivor % 2 == 0) { // 밤
            for (int i = 0; i < N; i++) {
                if (i == eunjin) continue;
                if (dead[i]) continue;

                // 1. 마피아의 시민 지명
                dead[i] = true;


                // 2. 유죄지수 변경
                for (int t = 0; t < N; t++) score[t] += R[i][t];

                calc(night + 1, survivor - 1);
                // 3. 이전 상태로 복원
                for (int t = 0; t < N; t++) score[t] -= R[i][t];
                dead[i] = false;
            }
        } else { // 낮
            int idx = execution();
            dead[idx] = true;
            calc(night, survivor - 1);
            dead[idx] = false;
        }
    }


    public static int execution() {
        int max = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = 0; i < N; i++) {
            if (dead[i]) continue;
            if (score[i] > max) {
                max = score[i];
                idx = i;
            }
        }
        return idx;
    }

    public static boolean isEnd() {
        if (dead[eunjin]) return true; // 마피아(은진)가 죽었을 경우: 시민 승
        for (int i = 0; i < N; i++) {
            if (i == eunjin) continue;
            if (!dead[i]) return false; // 아직 생존자가 있다면
        }
        return true;
    }
}
