import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static HW[] arr;
    static int[] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new HW[N];
        a = new int[N + 1];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dl = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            arr[i] = new HW(dl, cnt);
        }
        Arrays.sort(arr);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (HW hw : arr) {
            pq.add(hw.cnt);
            if(hw.deadLine < pq.size()) pq.poll();
        }
        int answer = 0;
        while (!pq.isEmpty()) answer+= pq.poll();
        System.out.println(answer);
    }


    static class HW implements Comparable<HW> {
        int deadLine;
        int cnt;

        public HW(int deadLine, int cnt) {
            this.deadLine = deadLine;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(HW o) {
            if (this.deadLine == o.deadLine) return o.cnt - this.cnt;
            return this.deadLine - o.deadLine;
        }
    }

}