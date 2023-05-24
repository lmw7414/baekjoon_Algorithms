
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        int[] prefixSum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        
        int answer = Integer.MIN_VALUE;
        
        for(int i = K; i <=N; i++){
            if(answer < prefixSum[i] - prefixSum[i - K])
                answer = prefixSum[i] - prefixSum[i - K];
        }
        System.out.println(answer);
    }
}