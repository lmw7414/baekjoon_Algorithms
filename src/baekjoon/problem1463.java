package baekjoon;

import java.util.Scanner;

public class problem1463 {

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        DP(N, 0);
        System.out.println(result);
    }

    static void DP(int num, int count){
        if(count > result)
            return;
        if(num < 1) {
            return;
        }
        if(num == 1) {
            if(result > count)
                result = count;
            return;
        }
        if(num%3 !=0 && num%2 !=0)
            DP(num-1, count + 1);
        else {
            if(num%3==0 && num%2==0) {
                DP(num / 3, count + 1);
                DP(num / 2, count + 1);
                DP(num - 1, count + 1);
            }
            else if(num%3 == 0 && num%2 !=0) {
                DP(num / 3, count + 1);
                DP(num - 1, count + 1);

            }
            else if(num%2==0 && num%3 !=0) {
                DP(num / 2, count + 1);
                DP(num - 1, count + 1);

            }
        }

    }
}
