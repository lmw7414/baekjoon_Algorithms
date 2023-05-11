
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int strLen = Integer.parseInt(st.nextToken());
        int partLen = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        int[] arr = new int[4];  //A, C, G, T

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i< 4; i++)
            arr[i] = Integer.parseInt(st.nextToken());


        int startIdx = 0;
        int endIdx = partLen;
        int[] curArr = new int[4];

        for(int i = startIdx; i < endIdx; i++) {
            addArr(str.charAt(i), curArr);
        }

        if(isOk(arr, curArr)) {
            answer++;
        }
        while (endIdx < strLen) {

            addArr(str.charAt(endIdx), curArr);
            endIdx++;
            subArr(str.charAt(startIdx), curArr);
            startIdx++;
            if(isOk(arr, curArr)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static void addArr(char A, int[] arr) {
        switch (A) {
            case 'A':
                arr[0]++;
                break;
            case  'C':
                arr[1]++;
                break;
            case 'G':
                arr[2]++;
                break;
            case 'T':
                arr[3]++;
        }
    }
    public static void subArr(char A, int[] arr) {
        switch (A) {
            case 'A':
                arr[0]--;
                break;
            case  'C':
                arr[1]--;
                break;
            case 'G':
                arr[2]--;
                break;
            case 'T':
                arr[3]--;
        }
    }

    public static boolean isOk(int[] answer, int[] cur) {
        for(int i = 0; i< 4; i++) {
            if(answer[i] > cur[i])
                return false;
        }
        return true;
    }
}
