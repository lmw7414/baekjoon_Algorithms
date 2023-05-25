
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long answer = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] arr = new long[N + 1];
        long[] mod = new long[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = (arr[i - 1] + Long.parseLong(st.nextToken())) % M;
            if (arr[i] == 0)
                answer++;
            mod[(int) arr[i]]++;
        }

        for (int i = 0; i < M; i++) {
            if (mod[i] > 1)
                answer += (mod[i] * (mod[i] - 1)) / 2;
        }
        System.out.println(answer);
    }
}