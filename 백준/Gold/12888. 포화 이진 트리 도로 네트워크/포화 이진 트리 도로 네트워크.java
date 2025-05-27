
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long answer  = 0;
        int cur = N - 1;

        while(cur > 0) {
            answer += (long) Math.pow(2, cur);
            cur -= 2;
        }
        System.out.println(answer + 1);

    }
}
