import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static Water[] waters;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        waters = new Water[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken()) - 1;
            waters[i] = new Water(s, e);
        }
        Arrays.sort(waters, (a, b) -> a.s - b.s);

        int i = waters[0].s;
        int idx = 0;
        int answer = 0;
        while(i <= waters[waters.length - 1].e) {
            if(waters[idx].e >= i) {
                i += L;
                answer++;
            } else {
                idx++;
                if(waters[idx].s > i) i = waters[idx].s;
            }
        }
        System.out.println(answer);

    }

    static class Water {
        int s, e;
        public Water(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}