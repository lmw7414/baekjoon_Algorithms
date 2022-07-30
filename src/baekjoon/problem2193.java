package baekjoon;

import java.math.BigInteger;
import java.util.Scanner;

//이친수
public class problem2193 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Long arr[][] = new Long[2][N+1];   //N이 90일때 int로는 값이 넘쳐서 Long으로 받아야함

        arr[0][1] = 0L;
        arr[1][1] = 1L;

        for(int i =2; i<=N; i++) {
            arr[0][i] = arr[0][i-1] + arr[1][i-1];  // 이전 이진수 마지막 숫자가 0일때는 0과 1두개로 나온다. 따라서 이전 0이었을 때 갯수와 1이었을 때 갯수를 더하여 저장
            arr[1][i] = arr[0][i-1];  // 이전의 이진수가 0이었을 때 1이 나올 수 있다. 따라서 이전 이친수의 0이었던 갯수를 가져온다.
        }
        System.out.println(arr[0][N] + arr[1][N]);

    }
}
