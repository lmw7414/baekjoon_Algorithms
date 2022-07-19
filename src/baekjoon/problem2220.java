package baekjoon;

import java.util.Scanner;
//힙정렬
//swap 횟수가 최대로
public class problem2220 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int[] arr = new int[N + 1];

        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j /= 2) {
                arr[j] = arr[j / 2];
            }
            arr[1] = i + 1;
        }

        arr[N] = 1; //마지막 노드는 1이 들어가야 최대 스왑이 일어난다.
        for (int i = 1; i < N + 1; i++)
            System.out.print(arr[i] + " ");
    }
}

