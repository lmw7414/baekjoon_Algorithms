
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        System.out.println(calculation(N, K));
    }

    static int calculation(int n, int k) {
        int bottle;
        int count;
        int buy = 0;

        if(n == 0)
            return 0;
        
        while(true) {
            bottle = n + buy;
            count = 0;
            while(bottle > 0) {
                if(bottle %2 != 0)
                    count++;
                bottle/=2;
            }
            if(count <= k)
                return buy;
            buy++;
        }

    }
}
