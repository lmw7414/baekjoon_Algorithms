package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//N과 M(3)
//시간 조절을 위해 System.out.println()을 최소화 해야한다
//따라서 StringBuilder 사용
public class problem15651 {
    static int N, M;
    static int printArr[];
    static StringBuilder sb = new StringBuilder();

    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        printArr = new int [M];

        DFS(M);
        System.out.println(sb);

    }

    static void DFS(int m) {

        if(m == 0){
            for(int a : printArr)
                sb.append(a + " ");
            sb.append('\n');
            return;
        }

        else {
            for(int i = 1; i <= N; i++) {
                printArr[M-m] = i;
                DFS(m-1);
            }
        }
        return;
    }

}
