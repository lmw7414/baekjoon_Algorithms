package baekjoon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class problem1026 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int A[] = new int[N];
        int B[] = new int[N];

        for(int i=0; i<N; i++)
            A[i] = sc.nextInt();
        for(int i=0; i<N; i++)
            B[i] = sc.nextInt();
        Arrays.sort(B);
        Integer A1[] = Arrays.stream(A).boxed().toArray(Integer[]::new);
        Arrays.sort(A1, Collections.reverseOrder());


        int result = 0;
        for(int i =0; i< N; i++)
            result += A1[i]*B[i];

        System.out.println(result);

    }
}
