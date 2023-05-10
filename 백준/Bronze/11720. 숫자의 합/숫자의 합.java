import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String buffer;
        N = Integer.parseInt(st.nextToken());
        int result = 0;

        st = new StringTokenizer(br.readLine());
        buffer = st.nextToken();

        for (int i = 0; i < N; i++)
            result += buffer.charAt(i) - '0';

        System.out.println(result);
    }
}
