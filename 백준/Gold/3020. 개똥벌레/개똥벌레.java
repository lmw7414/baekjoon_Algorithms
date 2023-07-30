
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 이진탐색 풀이[답 참고]
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 길이
        int M = Integer.parseInt(st.nextToken());  // 높이
        int[] down = new int[N / 2];
        int[] up = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            int d = Integer.parseInt(br.readLine());
            int u = Integer.parseInt(br.readLine());
            down[i] = d;
            up[i] = u;
        }
        Arrays.sort(down);
        Arrays.sort(up);
        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for (int i = 1; i <= M; i++) {
            int conflict = binarySearch(0, N / 2, i, down) + binarySearch(0, N / 2, M - i + 1, up);
            if (min > conflict) {
                min = conflict;
                cnt = 1;
            } else if (min == conflict) cnt++;
        }
        
        System.out.println(min + " " + cnt);
    }

    public static int binarySearch(int left, int right, int height, int[] arr) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= height)
                right = mid;
            else left = mid + 1;
        }
        return arr.length - right;
    }
    
}
