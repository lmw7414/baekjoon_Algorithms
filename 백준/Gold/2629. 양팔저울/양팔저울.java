
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N; // 추의 개수
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 만들 수 있는 추의 무게 계산
        Set<Integer> set = new HashSet<>();
        for (int a : arr) {
            List<Integer> li = new ArrayList<>(set);
            for (int s : li) set.add(s + a);
            set.add(a);
        }

        int TC = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < TC; tc++) {
            int num = Integer.parseInt(st.nextToken());
            if (set.contains(num)) {
                sb.append("Y ");
            } else {
                boolean flag = false;
                for (int s : set) {
                    if (set.contains(s + num)) {
                        sb.append("Y ");
                        flag = true;
                        break;
                    }
                }
                if (!flag) sb.append("N ");
            }
        }

        System.out.println(sb);
    }

}