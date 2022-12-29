package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class problem2606 {

    static int N;
    static int[][] arr;
    static boolean[] check;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        arr = new int[N + 1][N + 1];
        check = new boolean[N + 1];

        int a = scan.nextInt();

        for (int i = 0; i < a; i++) {
            int l = scan.nextInt();
            int r = scan.nextInt();

            arr[l][r] = 1;
            arr[r][l] = 1;
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        check[1] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int i = 1; i <= N; i++) {
                if (check[i] == false && arr[node][i] == 1) {
                    queue.add(i);
                    check[i] = true;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (check[i] == true)
                answer++;
        }
        System.out.println(answer - 1);

    }
}
