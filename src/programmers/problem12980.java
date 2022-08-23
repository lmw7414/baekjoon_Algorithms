package programmers;

import java.util.Scanner;

//점프와 순간이동
public class problem12980 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(solution(n));

    }
    public static int solution(int n) {
        String binary = Integer.toBinaryString(n);
        int ans = 0;

        for(int i = 0; i< binary.length(); i++) {
            if(binary.charAt(i) == '1')
                ans++;
        }

        return ans;
    }
}
