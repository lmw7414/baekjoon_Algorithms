package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// N과 M(4)
public class problem15652 {

    static int N, M;
    static boolean visitArr[];
    static int printArr[];

    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        printArr = new int [M+1];
        visitArr = new boolean[N+1];

        for(int i= 0; i< N+1; i++)
            visitArr[i] = false;

        DFS(M);

    }
    static void DFS(int m) {


        if(m == 0){
            for(int a =1; a<= M; a ++ )
                System.out.print(printArr[a] + " ");
            System.out.println();
            return;
        }

        else {
            for(int i = 1; i <= N; i++) {
                if(i < printArr[M-m]) {
                    continue;
                }

                if( visitArr[i] == false || printArr[M-m] == i)
                {

                    visitArr[i] = true;
                    printArr[M-m+1] = i;
                    DFS( m-1);
                    visitArr[i] = false;
                }

            }
        }
        return;
    }

}
