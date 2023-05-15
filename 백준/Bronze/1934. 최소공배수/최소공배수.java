
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i< n; i++) {
            String[] str = br.readLine().split(" ");
            System.out.println(lcm(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }
    }

    public static int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }
    public static int lcm(int a, int b)  {
        return a*b / gcd(a,b);
    }
}