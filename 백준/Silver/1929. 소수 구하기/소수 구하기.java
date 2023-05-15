import java.util.*;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        boolean[] arr = new boolean[N+1];

        for(int i = 2; i <= Math.sqrt(N); i++) {
            if(arr[i])
                continue;
            int mod = i;
            for(int j = 2; i * j <= N; j++) {
                arr[i * j] = true;
            }
        }
        for(int i = M; i <= N; i++) {
            if (i < 2)
                continue;
            if(!arr[i])
                System.out.println(i);
        }
    }
}