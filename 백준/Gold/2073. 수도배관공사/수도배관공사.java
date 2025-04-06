
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [입력]
 * D: 강까지의 거리(최대 10만)
 * P: 매입한 파이프의 개수(최대 350개)
 * L: 파이프 길이
 * C: 파이프 용량
 * [주의]
 * 수도관의 길이가 정확히 D와 같게 해야함
 * 길이 합이 D가 되게 하는 수도관은 무조건 존재
 * 가능한 최대 수도관 용량
 * [문제해결]
 * 같은 길이면 용량이 큰 것이 우선
 * 부분집합을 만들어야 함 -> 냅색
 * 물건의 크기 350 X 10만
 */

public class Main {

    static int D, P;
    static Pipe[] pipes;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        pipes = new Pipe[P];
        for(int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            pipes[i] = new Pipe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[D + 1];
        dp[0] = Integer.MAX_VALUE;
        for(Pipe pipe : pipes) {
            for(int d = D; d - pipe.L >= 0; d--) {
                if(dp[d - pipe.L] != 0) {
                    dp[d] = Math.max(dp[d], Math.min(dp[d - pipe.L], pipe.C));
                }
            }
        }
        System.out.println(dp[D]);
    }





    static class Pipe {
        int L;
        int C;

        public Pipe(int L, int C) {
            this.L = L;
            this.C = C;
        }

        @Override
        public String toString() {
            return "Pipe{" +
                    "L=" + L +
                    ", C=" + C +
                    '}';
        }
    }
}