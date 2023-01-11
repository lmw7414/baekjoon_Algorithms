package baekjoon;

import java.util.Scanner;

public class problem11050 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(cal(n, k) % 10007);

    }

    public static int cal(int n, int k) {

        if (n == k || k == 0)
            return 1;

        return cal(n - 1, k - 1) + cal(n - 1, k);
    }
}
