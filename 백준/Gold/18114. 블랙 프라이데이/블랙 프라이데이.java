import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static int N, C;
    static List<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == C) { // 물건이 1개 일 때
                System.out.println(1);
                System.exit(0);
            } else if (num > C) continue;
            arr.add(num);
        }
        Collections.sort(arr);

        int left = 0, right = arr.size() - 1;

        while (left < right) {
            int sum = arr.get(left) + arr.get(right);
            if (sum > C) {
                right--;
            } else if (sum < C) {
                int diff = C - sum;
                int result = bs(left + 1, right - 1, diff);
                if (result != -1) {
                    System.out.println(1);
                    System.exit(0);
                } else {
                    left++;
                }
            } else {
                System.out.println(1);
                System.exit(0);
            }
        }

        System.out.println(0);
    }

    public static int bs(int left, int right, int key) {
        if (left > right) return -1;
        int mid = (left + right) / 2;
        if (arr.get(mid) > key) {
            return bs(left, mid - 1, key);
        } else if (arr.get(mid) < key) {
            return bs(mid + 1, right, key);
        } else {
            return mid;
        }
    }
}