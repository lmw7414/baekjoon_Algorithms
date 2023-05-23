import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result[0] = arr[0];
        int resultIdx = 1;
        for (int i = 1; i < N; i++) {
            int key = arr[i];
            if (result[resultIdx - 1] < key)
                result[resultIdx++] = key;
            else {
                int left = 0;
                int right = resultIdx;
                while (left < right) {
                    int mid = (left + right) / 2;

                    if (result[mid] < key) left = mid + 1;
                    else right = mid;
                }
                result[left] = key;
            }
        }
        System.out.println(resultIdx);
    }
}