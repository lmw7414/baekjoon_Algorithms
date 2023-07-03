import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Solution {

    static long[] A;
    static long[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= tc; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            A = new long[N];
            B = new long[M];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[j] = Long.parseLong(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                B[j] = Long.parseLong(st.nextToken());
            }
            printAnswer(i, calc());
        }
    }

    public static long calc() {

        ArrayList<Long> answer = new ArrayList<>();
        if (A.length > B.length) { // M이 항상 길도록 설정
            long[] temp = A;
            A = B;
            B = temp;
        }

        for (int i = 0; i < B.length; i++) {
            long ans = 0;
            if(B.length - A.length < i )
                break;

            for (int j = 0; j < A.length; j++) {
                if(j >= B.length)
                    break;
                ans += A[j] * B[i + j];
            }
            answer.add(ans);
        }

        return Collections.max(answer);
    }

    public static void printAnswer(int tc, long answer) {
        System.out.println("#" + tc + " " + answer);
    }
}