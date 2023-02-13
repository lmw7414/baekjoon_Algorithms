package baekjoon;

import java.util.Scanner;

public class problem1110 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        String answer = n;
        int count = 0;
        while (true) {
            if (n.length() < 2) {
                n = "0" + n;
            }
            if (Integer.toString(Integer.parseInt(n)).equals(answer) && count > 0)
                break;
            int a1 = n.charAt(1) - '0';
            int a2 = (n.charAt(0) - '0') + (n.charAt(1) - '0');
            if (a2 >= 10)
                a2 %= 10;
            n = Integer.toString(a1) + Integer.toString(a2);
            count++;
        }

        System.out.println(count);
    }


}
