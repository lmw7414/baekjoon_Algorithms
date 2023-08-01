import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // N과 M(1)
    static int N, M;
    static boolean visitArr[];
    static int printArr[];

    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        printArr = new int [M];
        visitArr = new boolean[N+1];

        for(int i= 0; i< N+1; i++)
            visitArr[i] = false;

        //int total = totalSize(N, M);
        DFS(M);



    }
    static void DFS(int m) {

        //int root = 0;

        if(m == 0){
            for(int a : printArr)
                System.out.print(a + " ");
            System.out.println();
            return;
        }

        else {
            for(int i = 1; i <= N; i++) {
                if( visitArr[i] == false)
                {
                    visitArr[i] = true;
                    printArr[M-m] = i;
                    DFS( m-1);
                    visitArr[i] = false;
                }

            }
        }


    return;
    }

    /*static void printArr(int arr[]){
        for(int i = 0; i < M; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    static int totalSize (int n, int m) {
        int result = n;
        if( m == 1)
            return result;
        else
            return totalSize(n*(n-1), m-1);
    }*/
}
