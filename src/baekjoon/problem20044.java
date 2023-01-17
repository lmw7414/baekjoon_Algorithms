package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class problem20044 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        int[] arr = new int[2 * N];

        for (int i = 0; i < 2 * N; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }
        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = arr[i] + arr[2 * N - 1 - i];
            if(min > sum)
                min = sum;
        }

        System.out.println(min);
    }
}
