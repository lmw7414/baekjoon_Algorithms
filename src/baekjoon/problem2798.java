package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//블랙잭
public class problem2798 {

    static int N;
    static int M;
    static int arr[];
    static boolean visit[];
    static int Max = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        visit = new boolean[N];

        for(int i=0; i< N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        DFS(0, 0);

        System.out.println(Max);

    }
    static void DFS(int level, int result) {
        if(level == 3) {
            if(result <= M) {
                if(Max < result)
                    Max = result;
            }
            return;
        }
        else {
            for(int i =0 ;i< N; i++) {
                if(visit[i] == false) {
                    visit[i] = true;
                    DFS(level +1, result + arr[i]);
                    visit[i] = false;
                }
            }
        }
    }
}
