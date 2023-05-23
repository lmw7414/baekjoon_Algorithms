
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int arr[];
        int result[];
        arr = new int[N];
        result = new int[N];
        for(int i = 0; i< N; i++) {
            arr[i] = sc.nextInt();
        }

        for(int i = 0; i < N; i++) {
            result[i] = 1;

            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i] && result[i] < result[j] + 1)
                    result[i] = result[j] + 1;
            }
        }
        int max = -1;
        for(int i = 0; i< N; i++) {
            if(max < result[i])
                max = result[i];
        }
        System.out.println(max);
    }
}
