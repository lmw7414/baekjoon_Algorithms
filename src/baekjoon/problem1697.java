package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class problem1697 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int x = scan.nextInt();
        int bro = scan.nextInt();

        if (x > bro)
            System.out.println(x - bro);
        else
            System.out.println(BFS(x, bro));
    }

    public static int BFS(int x, int bro) {
        int[] check = new int[100001];
        int sec = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        check[x] = sec;
        while (!queue.isEmpty()) {
            int currentX = queue.poll();

            if (currentX == bro)
                return check[currentX];

            for (int i = 0; i < 3; i++) {
                int nx;
                if (i == 0) {
                    nx = currentX - 1;
                } else if (i == 1) {
                    nx = currentX + 1;
                } else {
                    nx = 2 * currentX;
                }

                if (nx >= 0 && nx <= 100000 && check[nx] == 0) {
                    check[nx] = check[currentX] + 1;
                    queue.add(nx);
                }
            }
        }
        return 0;
    }
}
