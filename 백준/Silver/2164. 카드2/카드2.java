
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new LinkedList<>();
        int N = sc.nextInt();

        for(int i = 1; i<=N; i++) {
            list.add(i);
        }

        while(list.size() != 1) {
            list.remove(0);
            int num = list.remove(0);
            list.add(num);
        }
        System.out.println(list.get(0));
    }
}
