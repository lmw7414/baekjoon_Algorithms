import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        int N;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            if (max < N)
                max = N;
            q.add(N);
        }


        while (!q.isEmpty()) {
            System.out.println(calc(q.poll()));
        }

    }

    public static long calc(int n) {
        if (n == 1)
            return 0;
        long answer = n;
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) answer = answer * (i - 1) / i;
            while(n % i == 0)
                n /= i;
        }

        if(n != 1) answer = answer * (n - 1)/ n;

        return answer;
    }
}