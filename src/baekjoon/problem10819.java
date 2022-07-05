package baekjoon;
//차이를 최대로
//완전탐색 백트래킹
// 완전 탐색으로 구현 -> 비효율적
// 다음번엔 백트래킹으로 구현해보자
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem10819 {
    static int N;
    static int arr[];
    static int result = 0;
    static boolean visitArr[];
    static int currentTable[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        arr = new int[N];
        visitArr = new boolean[N];
        currentTable = new int[N];
        for(int i= 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i< N; i++)
            visitArr[i] = false;

        DFS(0);
        System.out.println(result);


    }

    static void DFS(int depth) {

        if(depth == N) {
            int resultCal = calculate();
            if(result < resultCal)
                result = resultCal;
            //printArr();
            //System.out.println(result);

            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visitArr[i]) {
                visitArr[i] = true;
                currentTable[depth] = arr[i];
                DFS(depth + 1);
                visitArr[i] = false;
            }
        }
        return;
    }

    static int calculate() {
        int ans = 0;
        for(int i=1; i< N; i++) {
            if(currentTable[i] - currentTable[i-1] > 0)
                ans += currentTable[i] - currentTable[i-1];
            else
                ans += -(currentTable[i] - currentTable[i-1]);
        }
        return ans;
    }

    static void printArr(){
        for(int i=0; i< N; i++)
            System.out.print(currentTable[i] + " ");
        System.out.println();
    }
}
