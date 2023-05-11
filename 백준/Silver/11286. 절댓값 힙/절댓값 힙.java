import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            if(Math.abs(a) == Math.abs(b))
                return a - b;
            return Math.abs(a) - Math.abs(b);
        });

        int N = sc.nextInt();

        for(int i = 0; i< N; i++) {
            int input = sc.nextInt();

            if(input == 0) {
                if(pq.isEmpty())
                    System.out.println(0);
                else
                    System.out.println(pq.poll());
            }else
                pq.add(input);

        }
    }
}
