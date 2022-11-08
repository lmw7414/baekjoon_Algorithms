package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
//음계 다국어
public class problem2920 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> input = new ArrayList<>();
        for (int i = 0; i < 8; i++)
            input.add(Integer.parseInt(st.nextToken()));

        if (checkAsc(input))
            System.out.println("ascending");
        else if (checkDesc(input)) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }

    public static boolean checkAsc(List<Integer> arr) {
        for (int i = 1; i < 9; i++) {
            if (arr.get(i - 1) != i)
                return false;
        }
        return true;
    }

    public static boolean checkDesc(List<Integer> arr) {
        for (int i = 8; i > 0; i--) {
            if (arr.get(8 - i) != i)
                return false;
        }
        return true;
    }
}
