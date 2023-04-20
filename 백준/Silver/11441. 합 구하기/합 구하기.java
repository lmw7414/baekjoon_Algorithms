import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size + 1];
        int[] prefixSum = new int[size + 1];
        for(int i = 1; i <= size; i++) {
            arr[i] = sc.nextInt();
            if(i != 0)
                prefixSum[i] = prefixSum[i-1] + arr[i];
            else
                prefixSum[i] = arr[i];
        }

        int tc = sc.nextInt();

        for(int i = 0; i < tc; i++ ) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            System.out.println(prefixSum[b] - prefixSum[a - 1]);
        }
    }
}