import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
[문제 해결 프로세스]
1. 가장 가까운 키 검색 -> 이분탐색(lower bound)
2. 키-밸류 저장 -> 해시맵
 */

public class Main {
    static int N, M, K;
    static HashMap<Integer, Integer> hm = new HashMap<>();
    static List<Integer> keySet = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 최대 10만
        M = Integer.parseInt(st.nextToken()); // 최대 10만
        K = Integer.parseInt(st.nextToken()); // 최대 만

        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            hm.put(key, val);
            keySet.add(key);
        }
        Collections.sort(keySet);

        for(int m = 0; m < M; m++) {
            st =new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int key, val, idx;
            switch (command) {
                case 1:
                    key = Integer.parseInt(st.nextToken());
                    val = Integer.parseInt(st.nextToken());
                    hm.put(key, val);
                    idx = bs(key);
                    keySet.add(idx + 1, key);
                    break;
                case 2:
                    key = Integer.parseInt(st.nextToken());
                    val = Integer.parseInt(st.nextToken());
                    idx = bs(key);
                    key = getKey(key, idx);
                    if(key < 0) continue;

                    hm.replace(key, val);
                    break;
                case 3:
                    key = Integer.parseInt(st.nextToken());
                    idx = bs(key);
                    key = getKey(key, idx);
                    if(key == -1) sb.append(-1).append("\n");
                    else if(key == -2) sb.append("?\n");
                    else sb.append(hm.get(key)).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }

    public static int bs(int key) {
        int left = 0;
        int right = keySet.size() - 1;
        int res = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(keySet.get(mid) <= key) {
                res = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
        return res;
    }

    public static int getKey(int target, int idx) {

        // 1. idx 비교
        int abs1 = Math.abs(target - keySet.get(idx));
        int abs2 = (idx + 1) < keySet.size() ? Math.abs(target - keySet.get(idx + 1)) : Integer.MAX_VALUE;

        if(abs1 > K) abs1 = Integer.MAX_VALUE;
        if(abs2 > K) abs2 = Integer.MAX_VALUE;
        // 둘 중 절댓값이 더 작은 것
        if(abs1 < abs2) return keySet.get(idx);
        else if(abs1 > abs2) return keySet.get(idx + 1);
        else {
            if(abs1 != Integer.MAX_VALUE) return -2; //
            return -1; // 유일한 조건이 없는 경우
        }
    }
}
