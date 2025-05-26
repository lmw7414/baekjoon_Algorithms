
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        long answer = 0;
        Set<Integer> set = new HashSet<>();
        for(int right = 0; right < N; right++) {
            if(!set.contains(arr[right])) {
                set.add(arr[right]);
            } else {
                long cnt = (long) set.size() * (set.size() + 1) / 2;
                while(arr[left] != arr[right]) {
                    set.remove(arr[left++]);
                }
                left++;
                cnt -= (long) set.size() * (set.size() - 1) / 2;
                answer += cnt;
            }
        }
        if(!set.isEmpty()) {
            int size = set.size();
            long cnt = (long) size * (size + 1) / 2;
            answer += cnt;
        }
        System.out.println(answer);
    }



}
