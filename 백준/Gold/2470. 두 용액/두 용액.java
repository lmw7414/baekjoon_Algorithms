
import java.util.*;
import java.io.*;

/**
 * [문제 해결 프로세스]
 * 1. 알칼리 , 산성 별 오름차순 정렬
 * 2. 같은 종류의 용액 밖에 없다면
 *  - 알칼리: 마지막 인덱스 + 마지막 -1 인덱스
 *  - 산성 :  0번 1번 인덱스
 * 3. 서로 다른 종류
 *  - 하나의 용액을 기준으로 탐색
 *  - (절댓값이) 작거나 같은 것
 */
public class Main {
    static int MIN = Integer.MAX_VALUE;
    static List<Integer> alcali, san;
    static int[] answer = new int[2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        alcali = new ArrayList<>();
        san = new ArrayList<>();
        String[] strs = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(strs[i]);
            if(num < 0) alcali.add(num);
            else san.add(num);
        }
        Collections.sort(alcali);
        Collections.sort(san);

        // 알칼리 조사
        if(alcali.size() > 1) calc(alcali.get(alcali.size() - 2), alcali.get(alcali.size() -1));
        // 산성 조사
        if(san.size() > 1) calc(san.get(0), san.get(1));

        // 알칼리 + 산성
        if(!san.isEmpty()) {
            for (int al : alcali) {
                int idx = bs(-al);
                calc(al, san.get(idx));
                if (san.size() > idx + 1) calc(al, san.get(idx + 1));
            }
        }
        System.out.println(answer[0] + " " + answer[1]);

    }
    // 같거나 낮은 값의 IDX 반환
    public static int bs(int key) {
        int left = 0;
        int right = san.size() - 1;
        int resultIdx = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(san.get(mid) <= key) {
                resultIdx = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
        return resultIdx;
    }

    public static void calc(int first, int second) {
        int min = Math.abs(first + second);
        if(MIN > min) {
            MIN = min;
            answer[0] = first;
            answer[1] = second;
        }
    }

}
