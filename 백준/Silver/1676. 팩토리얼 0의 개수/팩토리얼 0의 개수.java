import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int c2, c5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = n; i > 0; i--) {
            c2 += module2(i);
            c5 += module5(i);
        }

        System.out.println(Math.min(c2, c5));
    }

    public static int module2(int n) {
        int cnt = 0;
        while (n % 2 == 0) {
            cnt++;
            n /= 2;
        }
        return cnt;
    }

    public static int module5(int n) {
        int cnt = 0;
        while (n % 5 == 0) {
            cnt++;
            n /= 5;
        }
        return cnt;
    }

}