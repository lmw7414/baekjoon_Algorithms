
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] low = new int[N];
        int[] high = new int[N];

        for(int k = 1; k < N; k++) {
            for(int j = 0; j < k; j++) {
                if(arr[j] > arr[k]) low[k] = Math.max(low[k], high[j] + 1);
                else if(arr[j] < arr[k]) high[k] = Math.max(high[k], low[j] + 1);
            }
        }
        int answer = 0;
        for(int k = 0; k < N; k++)
            answer = Math.max(low[k], high[k]);

        System.out.println(answer + 1);
    }


}