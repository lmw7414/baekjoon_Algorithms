
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 1;
        int N = sc.nextInt();

        int left = 1;
        int right = 1;
        int sum = 1;

        while(left != N) {
            if(sum == N) {
                answer++;
                right++;
                sum += right;
            } else if(sum < N) {
                right++;
                sum+= right;
            } else {
                sum -= left;
                left++;
            }
        }

        System.out.println(answer);
    }
}
