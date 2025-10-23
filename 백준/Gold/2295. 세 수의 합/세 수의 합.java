
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
[문제 해결 프로세스]
1. 정렬
2. 큰 수부터 타겟 설정 -> H라 칭함
3. 2개의 숫자 탐색 -> a, b
4. 이분 탐색으로 H - (a + b)가 존재하는지 체크
5. 있다면 정답
 */
public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 최대 1000
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        for (int i = N - 1; i >= 0; i--) {
            int target = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                for (int k = j; k >= 0; k--) {
                    if (arr[j] + arr[k] >= target) continue;

                    // 이분탐색으로 target - (arr[j] + arr[k])가 존재하는지 확인
                    int key = target - (arr[j] + arr[k]);
                    if (binarySearch(key, 0, k)) {
                        System.out.println(target);
                        return;
                    }
                }
            }
        }
    }

    public static boolean binarySearch(int key, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == key) return true;
            else if (arr[mid] > key) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }

}