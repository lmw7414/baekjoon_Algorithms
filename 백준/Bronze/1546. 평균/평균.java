
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        double[] newArr = new double[N];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            int input = sc.nextInt();
            if(max < input)
                max = input;
            arr[i] = input;
        }
        for(int i = 0; i< N; i++) {
            newArr[i] = calc(max, arr[i]);
        }
        System.out.println(Arrays.stream(newArr).average().getAsDouble());
    }
    public static double calc(int max, int num) {
        return (double)num / (double)max * 100;
    }
}
