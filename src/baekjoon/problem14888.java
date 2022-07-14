package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//연산자 끼워 넣기
//계산하는 과정 다시한번 생각해볼 것
public class problem14888 {
    static int N;
    static int exp[];
    static int operation[] = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        exp = new int[N];
        for (int i = 0; i < N; i++) {
            exp[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        DFS(exp[0], 1);
        System.out.println(max);
        System.out.println(min);

    }

    static void DFS(int result, int n) {
        if (n == N) {
            if (max < result)
                max = result;
            if (min > result)
                min = result;
            return;
        } else {
            for (int i = 0; i < 4; i++) {
                if (operation[i] != 0) {
                    operation[i]--;

                    switch (i) {
                        case 0:
                            DFS(result + exp[n], n+1);
                            break;
                        case 1:
                            DFS(result - exp[n], n+1);
                            break;
                        case 2:
                            DFS(result * exp[n], n+1);
                            break;
                        case 3:
                            DFS(result / exp[n], n+1);
                            break;
                    }
                    operation[i]++;
                }
            }

        }

    }




}
