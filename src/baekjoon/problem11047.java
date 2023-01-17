package baekjoon;

import java.util.Scanner;

public class problem11047 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int dest = sc.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i< N; i++) {
            arr[i] = sc.nextInt();
        }

        int answer = 0;

        for(int i = N-1; i>= 0; i-- ) {
            if(arr[i] > dest)
                continue;
            while(dest / arr[i] > 0) {
                dest -= arr[i];
                answer++;
            }

        }
        System.out.println(answer);
    }
}
