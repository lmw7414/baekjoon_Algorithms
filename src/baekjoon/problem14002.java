package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//가장 긴 증가하는 부분 수열 4
public class problem14002 {

    public static void main(String[] args) throws IOException {
        int N;
        int arr[];
        int result[];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = new int[N];

        st= new StringTokenizer(br.readLine());

        for(int i = 0; i< N; i++)
            arr[i] = Integer.parseInt(st.nextToken());



        for(int i = 0; i < N; i++) {
            result[i] = 1;
            for(int j = 0; j < i; j++) {
                if(arr[i] >arr[j] && result[j] + 1 > result[i])
                    result[i] = result[j] + 1;
            }
        }
        int Max = 0;
        for(int i = 0; i< N; i++)
            if(Max < result[i])
                Max = result[i];
        System.out.println(Max);
        int count = Max;
        Map<Integer, Integer> results = new HashMap<>();
        for(int i=N-1; i >= 0 ; i--) {
            if(Max == result[i]){
                results.put(Max, arr[i]);
                Max--;
                if(Max == 0)
                    break;
            }
        }
        for(int i=1; i<= count; i++)
            System.out.print(results.get(i) + " ");

    }
}
