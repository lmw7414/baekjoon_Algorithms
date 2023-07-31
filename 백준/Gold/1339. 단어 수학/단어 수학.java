import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int[] arr = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int len = str.length();
            int z = (int) Math.pow(10, len - 1);
            for (int j = 0; j < len; j++) {
                arr[charToInt(str.charAt(j))] += z;
                z /= 10;
            }
        }
        Arrays.sort(arr);
        int start = 9;
        int result = 0;
        for (int i = 25; i >= 17; i--) {
            result += arr[i] * start--;
        }
        System.out.println(result);
    }

    public static int charToInt(char c) {
        return c - 'A';
    }
}