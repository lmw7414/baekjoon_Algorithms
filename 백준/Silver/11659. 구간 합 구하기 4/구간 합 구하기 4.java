
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] Ntc = br.readLine().split(" ");
        int N = Integer.parseInt(Ntc[0]);
        int tc = Integer.parseInt(Ntc[1]);
        int[] arr = new int[N];
        int[] sum = new int[N + 1];
        String[] str = br.readLine().split(" ");
        for(int i = 0; i< str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
            sum[i + 1] = arr[i] + sum[i];
        }
        for(int i = 0; i < tc; i++) {
            String[] tcn = br.readLine().split(" ");
            int a = Integer.parseInt(tcn[0])-1;
            int b = Integer.parseInt(tcn[1]);
            System.out.println(sum[b] - sum[a]);
        }
    }
}
