import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputSize = br.readLine();
        int size = Integer.parseInt(inputSize);

        arr = new int[size + 1][size + 1];
        for (int i = 1; i <= size; i++) {
            String arg = br.readLine();
            for (int j = 1; j <= size; j++) {
                arr[i][j] = arg.charAt(j - 1) - '0';
            }
        }
        StringBuilder sb = new StringBuilder();
        divAndCon(sb, 1, 1, size, size);
        System.out.println(sb.toString());
    }

    public static void divAndCon(StringBuilder sb, int startX, int startY, int endX, int endY) {
        int midX = (startX + endX) / 2;
        int midY = (startY + endY) / 2;

        if (startX >= endX || startY >= endY) {
            sb.append(arr[startX][startY]);
            return;
        }
        int atom = arr[startX][startY];
        boolean flag = true;
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (atom != arr[i][j]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        if (!flag) {
            sb.append('(');
            divAndCon(sb, startX, startY, midX, midY);  //1
            divAndCon(sb, startX, midY + 1, midX, endY);  //2
            divAndCon(sb, midX + 1, startY, endX, midY);  //3
            divAndCon(sb, midX + 1, midY + 1, endX, endY);  //4
            sb.append(')');
        } else {
            sb.append(atom);
        }
    }
}
