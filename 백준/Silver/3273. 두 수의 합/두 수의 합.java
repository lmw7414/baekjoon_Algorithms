
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;
        int answer = 0;
        while (left < right) {
            if (arr[left] + arr[right] == x) {
                answer++;
                left++;
                right--;
            } else if (arr[left] + arr[right] > x) {
                right--;
            } else {
                left++;
            }

        }
        System.out.println(answer);
    }
}