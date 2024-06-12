import java.util.*;
import java.io.*;
public class Main {
    static int[] arr = new int[3];
    static int[] count = new int[7];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            count[arr[i]]++;
        }
        
        int maxIdx = 1;
        int cnt = 0;
        for(int i = 1; i<= 6; i++) {
            if(count[i] >= cnt ) {
                cnt = count[i];
                maxIdx = i;
            }
        }
        
        if(cnt == 1) {
            System.out.println(100 * maxIdx);
        } else if(cnt == 2) {
            System.out.println(1000 + 100 * maxIdx);
        } else if(cnt == 3) {
            System.out.println(10000 + 1000 * maxIdx);
        }
    }
}