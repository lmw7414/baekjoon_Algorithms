
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 최대 십만
        M = Integer.parseInt(st.nextToken()); // 최대 백만
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[s]++;
            arr[e]--;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i<= N; i++) {
            if(set.contains(arr[i])) {
                System.out.println(-1);
                return;
            }
            set.add(arr[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.print(sb);
    }

}
