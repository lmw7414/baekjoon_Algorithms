import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static PriorityQueue<Time> times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        times = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            times.add(new Time(s, e));
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (!times.isEmpty()) {
            Time t = times.poll();
            if (pq.isEmpty() || pq.peek() > t.s) pq.add(t.e);
            else {
                pq.poll();
                pq.add(t.e);
            }
        }
        System.out.println(pq.size());
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