import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int L, ML, MK, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ML = Integer.parseInt(st.nextToken());
        MK = Integer.parseInt(st.nextToken());
        C =  Integer.parseInt(br.readLine());
        int[] arr = new int[L + 1];
        int[] arr2 = new int[L + 2];
        for(int i = 1; i <= L; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        boolean flag = true;
        int prefixSum = arr2[0];
        for(int i= 1; i <= L; i++) {
            prefixSum += arr2[i];
            if(arr[i] == 0) continue;
            if(arr[i] > Math.min(i, ML) * MK - prefixSum) {
                C--;
                arr2[Math.min(i + 1, L)] += MK;
                arr2[Math.min(i + ML, L + 1)] -= MK;
                if (C < 0) {
                    flag = false;
                    break;
                }
            }
        }

        if(flag) System.out.println("YES");
        else System.out.println("NO");
    }

}