import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int size = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        
        int[] arr = new int[size];
        
        for(int i = 0; i< size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        st = new StringTokenizer(br.readLine());
        int answerSize = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< answerSize; i++) {
            int input = Integer.parseInt(st.nextToken());
            if(isContain(arr, 0, size-1, input))
                System.out.println(1);
            else
                System.out.println(0);
        }
        
    }
    
    public static boolean isContain(int[] arr,int left, int right, int result) {
        int mid = (left + right) / 2;
        if(arr[mid] == result)
            return true;
        if(left <= right) {
            if(arr[mid] < result) {
                return isContain(arr, mid + 1, right, result);
            }else if(arr[mid] > result) {
                return isContain(arr, left, mid - 1, result);
            }
        }
        return false;
    }
    
}