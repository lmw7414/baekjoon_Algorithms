import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String nStr = br.readLine();
            int n = Integer.parseInt(nStr);
            char[] buff = new char[n];
            br.read(buff, 0, n);

            int[] numArr = new int[20];
            char[] opArr = new char[20];
            int[] dpMax = new int[30];
            int[] dpMin = new int[30];

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    numArr[i / 2] = buff[i] - '0';
                } else {
                    opArr[i / 2] = buff[i];
                }
            }

            dpMax[0] = numArr[0];
            dpMin[0] = numArr[0];
            if (n == 1) {
                System.out.println(dpMax[0]);
                return;
            }
            dpMax[1] = opFunc(numArr[0], numArr[1], opArr[0]);
            dpMin[1] = dpMax[1];

            for (int i = 2; i < n / 2 + 1; i++) {
                dpMax[i] = Math.max(
                        opFunc(dpMax[i - 1], numArr[i], opArr[i - 1]),
                        opFunc(dpMax[i - 2], opFunc(numArr[i - 1], numArr[i], opArr[i - 1]), opArr[i - 2])
                );
                dpMax[i] = Math.max(
                        dpMax[i],
                        opFunc(dpMin[i - 1], numArr[i], opArr[i - 1])
                );
                dpMax[i] = Math.max(
                        dpMax[i],
                        opFunc(dpMin[i - 2], opFunc(numArr[i - 1], numArr[i], opArr[i - 1]), opArr[i - 2])
                );

                dpMin[i] = Math.min(
                        opFunc(dpMin[i - 1], numArr[i], opArr[i - 1]),
                        opFunc(dpMin[i - 2], opFunc(numArr[i - 1], numArr[i], opArr[i - 1]), opArr[i - 2])
                );
                dpMin[i] = Math.min(
                        dpMin[i],
                        opFunc(dpMax[i - 1], numArr[i], opArr[i - 1])
                );
                dpMin[i] = Math.min(
                        dpMin[i],
                        opFunc(dpMax[i - 2], opFunc(numArr[i - 1], numArr[i], opArr[i - 1]), opArr[i - 2])
                );
            }

            System.out.println(dpMax[n / 2]);
        } catch (IOException e) {

        }
    }

    static int opFunc(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '*':
                return a * b;
            case '-':
                return a - b;
            default:
                return 0;
        }
    }
}