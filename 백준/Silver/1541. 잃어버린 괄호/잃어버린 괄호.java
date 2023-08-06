import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] removeMinus = str.split("-");
        boolean first = true;
        for (String s : removeMinus) {
            String[] removePlus = s.split("\\+");
            for (String t : removePlus) {
                if (first) {
                    answer += Integer.parseInt(t);
                } else answer -= Integer.parseInt(t);
            }
            first = false;
        }
        System.out.println(answer);
    }
}