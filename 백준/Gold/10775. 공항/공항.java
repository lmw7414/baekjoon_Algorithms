import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int G, P;
    static int[] arr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        arr = new int[G + 1];
        for (int i = 1; i <= G; i++) arr[i] = i;
        arr[0] = -1;

        boolean flag = true;
        for (int p = 0; p < P; p++) {
            int num = Integer.parseInt(br.readLine());
            if (flag && calc(num) != -1) answer++;
            else flag = false;
        }
        System.out.println(answer);
    }

    public static int calc(int num) {
        if (arr[num] == -1) return -1;
        if (arr[num] == num) {
            arr[num]--;
            return num;
        } else {
            return arr[num] = calc(arr[num]);
        }
    }
}