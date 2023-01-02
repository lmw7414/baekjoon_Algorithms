package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//스타트링크
public class problem5014 {
    static int FULL; // 건물의 최대 층 수
    static int CURRENT; // 현재 위치
    static int G; // StartLink가 위차한 층
    static int UP; // 한번에 올라갈 수 있는 층 수
    static int DOWN; // 한번에 내려갈 수 있는 층 수

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FULL = scan.nextInt();
        CURRENT = scan.nextInt();
        G = scan.nextInt();
        UP = scan.nextInt();
        DOWN = scan.nextInt();

        int result = BFS();

        if (result == -1)
            System.out.println("use the stairs");
        else
            System.out.println(result);
    }

    public static int BFS() {
        int[] check = new int[FULL + 1];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(CURRENT);
        check[CURRENT] = count;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (x == G) {
                return check[x];
            }
            for (int i = 0; i < 2; i++) {
                int nx;
                if (i == 0)
                    nx = x + UP;
                else
                    nx = x - DOWN;

                if (nx > 0 && nx <= FULL && check[nx] == 0 && x != nx) {
                    check[nx] = check[x] + 1;
                    queue.add(nx);
                }
            }
        }
        return -1;
    }
}
