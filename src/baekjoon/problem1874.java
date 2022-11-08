package baekjoon;

import java.io.IOException;
import java.util.*;

// 스택 수열
public class problem1874 {
    static int N;

    public static void main(String[] args) throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        List<Character> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            queue.add(sc.nextInt());
        }
        int start = 1;
        while (!queue.isEmpty()) {
            int num = queue.peek();
            if (start <= num) {
                for (; start <= num; start++) {
                    stack.push(start);
                    result.add('+');
                }
            }
            if (stack.peek() > num) {
                System.out.println("NO");
                System.exit(0);
            }
            while (!stack.isEmpty() && num == stack.peek()) {
                stack.pop();
                result.add('-');
                num = queue.poll();
            }

        }

        for (int i = 0; i < result.size(); i++)
            System.out.println(result.get(i));
    }
}
