
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 0 && b == 0) break;
            if(GCD(a, b)) sb.append("A wins\n");
            else sb.append("B wins\n");
        }

        System.out.println(sb);
    }

    // 매 턴 자신이 이길려면 어떻게 해야할까?
    public static boolean GCD(int big, int small) {
        if (big < small) {
            int temp = big;
            big = small;
            small = temp;
        }
        if(small == 0) return false;
        if(big / small >= 2) return true;
        return !GCD(big % small, small);
    }

}
