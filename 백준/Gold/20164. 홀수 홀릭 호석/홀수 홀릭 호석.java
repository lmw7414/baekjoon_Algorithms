import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int min = Integer.MAX_VALUE, max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        calc(new Box(input, getOddCnt(input)));

        System.out.println(min + " " + max);
    }

    public static void calc(Box box) {
        int inputSize = box.input.length();
        if (inputSize == 1) { // 종료
            min = Math.min(min, box.oddCnt);
            max = Math.max(max, box.oddCnt);
        } else if (inputSize == 2) {
            two(box);
        } else {
            threeOver(box);
        }
    }

    // 2개로 분할
    public static void two(Box box) {
        String input1 = box.input.substring(0, 1);
        String input2 = box.input.substring(1, 2);
        int sum = Integer.parseInt(input1) + Integer.parseInt(input2);
        calc(new Box(String.valueOf(sum), box.oddCnt + getOddCnt(String.valueOf(sum))));
    }

    // 3개로 분할
    public static void threeOver(Box box) {
        for (int i = 1; i < box.input.length() - 1; i++) {
            for (int j = i + 1; j < box.input.length(); j++) {
                String input1 = box.input.substring(0, i);
                String input2 = box.input.substring(i, j);
                String input3 = box.input.substring(j);
                int sum = Integer.parseInt(input1) + Integer.parseInt(input2) + Integer.parseInt(input3);
                calc(new Box(String.valueOf(sum), box.oddCnt + getOddCnt(String.valueOf(sum))));
                //System.out.printf("%s %s %s\n", input1, input2, input3);
            }
        }
    }

    public static int getOddCnt(String input) {
        int cnt = 0;
        for (int i = 0; i < input.length(); i++) {
            int num = input.charAt(i) - '0';
            if (num % 2 == 1) cnt++;
        }
        return cnt;
    }

    static class Box {
        String input;
        int oddCnt;

        public Box(String input, int oddCnt) {
            this.input = input;
            this.oddCnt = oddCnt;
        }
    }

}