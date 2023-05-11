
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int leftIdx = 0;
        int rightIdx = N - 1;
        while(leftIdx < rightIdx) {
            if(arr[leftIdx] + arr[rightIdx] == M) {
                answer++;
                leftIdx++;
                rightIdx--;
            } else if(arr[leftIdx] + arr[rightIdx] < M) {
                leftIdx++;
            } else
                rightIdx--;
        }
        System.out.println(answer);
    }
}
