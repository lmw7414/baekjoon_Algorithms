import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [문제 해결 프로세스]
 * 1. 끝나는 시간 기준 정렬
 * 2. 이분탐색(최소 1개 최대 십만개)
 * - 우선순위 큐 - 오름차순
 * - 데이터를 우선 삽입
 * - 끝 시간을 넣고 다음 넣을 데이터의 시작시간과 비교
 */
public class Main {
    static int N;
    static Time[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        times = new Time[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            times[i] = new Time(s, e);
        }
        Arrays.sort(times);

        int left = 1;
        int right = N + 1;
        int answer = N;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (calc(mid)) {
                answer = mid;
                right = mid - 1;
            } else left = mid + 1;
        }
        System.out.println(answer);
    }

    public static boolean calc(int key) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < key; i++) pq.add(0);

        for (int i = 0; i < N; i++) {
            Time t = times[i];
            if (pq.isEmpty()) return false;
            int end = pq.poll();
            if (end <= t.s) pq.add(t.e);
            else return false;
        }
        return true;
    }

    static class Time implements Comparable<Time> {
        int s, e;

        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }
        @Override
        public int compareTo(Time o) {
            if (this.s == o.s) return this.e - o.e;
            return this.s - o.s;
        }

    }

}