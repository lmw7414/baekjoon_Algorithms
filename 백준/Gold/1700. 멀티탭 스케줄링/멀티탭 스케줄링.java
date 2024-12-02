import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, K;
    static int[] arr, eCnt;
    static Queue<Integer> next = new ArrayDeque<>();
    static Set<Integer> occupied = new HashSet<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        eCnt = new int[K + 1];
        arr = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            eCnt[num]++;
            arr[i] = num;
        }

        int idx = 0;
        while (idx < K) {
            int electronic = arr[idx++];
            if (occupied.contains(electronic)) { // 이미 꽂아져 있는 경우
                eCnt[electronic]--;
                continue;
            }
            if (occupied.size() >= N) { // 여분이 없는 경우
                int r = findMin(idx);
                answer++;
                occupied.remove(r);
            }
            occupied.add(electronic);
            eCnt[electronic]--;
        }
        System.out.println(answer);

    }

    // 제일 적게 사용하는 전자기기 찾기
    public static int findMin(int curIdx) {
        int idx = -1;
        for (int electronic : occupied) {
            if (eCnt[electronic] == 0) return electronic;
            for (int i = curIdx; i < K; i++) {
                if (arr[i] == electronic) {
                    if (idx < i) idx = i;
                    break;
                }
            }
        }
        return arr[idx];
    }
}