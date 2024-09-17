import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr, index;
    static List<Integer> list = new ArrayList<>(); // 수열의 길이를 저장하기 위함

    public static void main(String[] args) throws IOException {
        list.add(Integer.MIN_VALUE);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        index = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            if (arr[i] > list.get(list.size() - 1)) {
                list.add(arr[i]);
                index[i] = list.size() - 1;
            } else {
                int idx = binarySearch(1, list.size() - 1, arr[i]);
                list.set(idx, arr[i]);
                index[i] = idx;
            }
        }
        System.out.println(list.size() - 1);
        Stack<Integer> stack = new Stack<>();
        int idx = list.size() - 1;
        for (int i = index.length - 1; i > 0; i--) {
            if (index[i] == idx) {
                stack.push(arr[i]);
                idx--;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
        System.out.print(sb.toString());
    }

    // key값 보다 크거나 같은 수의 배열 idx 반환
    public static int binarySearch(int left, int right, int key) {
        int mid = (left + right) / 2;
        if (left < right) {
            if (list.get(mid) < key) return binarySearch(mid + 1, right, key);
            else if (list.get(mid) >= key) return binarySearch(left, mid, key);
        }
        return right;

    }
}