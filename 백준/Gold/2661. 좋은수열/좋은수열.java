import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        backtracking("");


    }

    public static void backtracking(String s) {

        if(s.length() == N) {
            System.out.println(s);
            System.exit(0);
        }
        else {
            for(int i =1 ; i<=3; i++) {
                if(isGood(s+i))
                    backtracking(s+i);
            }
        }

    }

    public static boolean isGood(String s) {
        int length = s.length() /2;

        for(int i = 1; i<=length; i++) {
            if(s.substring(s.length()-i).equals(s.substring(s.length()- 2*i, s.length()-i)))
                return false;
        }
        return true;
    }
}
