
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        int[] arrIdx = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            arrIdx[arr[i]] = i;
        }
        int max = 0;
        for(int i = 0; i < N; i++) { // arr 반복
            int cnt = 1;
            int val = arr[i];
            while(true) {
                if(val >= N) break;
                if (arrIdx[val] < arrIdx[val + 1]) {
                    cnt++;
                    val += 1;
                } else break;
            }
            max = Math.max(max, cnt);
        }
        System.out.println(N - max);
    }

    

}