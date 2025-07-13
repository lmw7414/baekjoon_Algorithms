import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[] prefixSum;
    static int MAX = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        prefixSum = new int[MAX + 1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            prefixSum[a]++;
            prefixSum[b]--;
        }

        for(int i = 1; i <= MAX; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        int A = 0;
        int B = 0;
        int cnt = 0;
        boolean flag = false;
        while(true) {
            if(B == MAX && cnt < K) break;
            if(cnt < K) {
                if(B < MAX) {
                    B++;
                    cnt += prefixSum[B - 1];
                }
            }
            else if(cnt > K) {
                cnt -= prefixSum[A];
                A++;
            }
            else {
                flag = true;
                break;
            }
        }
        if(flag) System.out.println(A + " " + B);
        else System.out.println(0 + " " + 0);
    }

}