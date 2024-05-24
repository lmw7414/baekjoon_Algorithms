import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = br.readLine();
            if ("0".equals(str)) break;
            boolean flag = true;
            int len = str.length();
            for (int i = 0; i < len / 2; i++) {
                if (str.charAt(i) != str.charAt(len - i - 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) System.out.println("yes");
            else System.out.println("no");
        }
    }
}