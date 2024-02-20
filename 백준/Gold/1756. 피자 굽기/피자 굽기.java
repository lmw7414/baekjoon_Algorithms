import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [문제 해결 프로세스]
 * N개의 피자 반죽을 오븐에 넣고 굽는다.
 * 피자 반죽은 완성되는 순서대로 오븐에 들어간다.
 * N개의 피자가 오븐에 모두 들어가고 나면, 맨위의 피자가 얼마나 깊이 들어가 있는지 알아내라
 * <p>
 * [입력] 1<= D, N <= 300,000
 * D : 오븐의 깊이
 * N : 피자 반죽의 개수
 * <p>
 * 주어진 N의 배열을 내림차순 형식으로 변환해야함. 5 -> 6이면 5 -> 5가 될 수 있음. 이런 식으로 변환하여 배열을 초기화
 */

public class Main {
    static int D, N, idx;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[D + 1];

        st = new StringTokenizer(br.readLine());
        int max = Integer.MAX_VALUE;

        // 오븐 크기 조정
        for (int i = 1; i <= D; i++) {
            int input = Integer.parseInt(st.nextToken());
            max = Math.min(max, input);
            arr[i] = max;
        }

        st = new StringTokenizer(br.readLine());
        idx = D;
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            calc(input);
        }

        System.out.println(idx);
    }
    
    public static void calc(int pizza) {
        boolean flag = false;
        for(int i = idx; i > 0; i--) {
            if(pizza <= arr[i]) {
                idx = i;
                arr[i] = 0;
                flag = true;
                break;
            }
        }
        if(!flag) {
            idx = 0;
        }
    }

}