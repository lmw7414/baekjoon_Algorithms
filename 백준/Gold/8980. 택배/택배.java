import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, C;
    static int[] village;  // 마을 별 배달해야할 상자의 개수
    static Delivery[] deliveries;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        village = new int[N + 1];
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        deliveries = new Delivery[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int ariv = Integer.parseInt(st.nextToken());
            int dep = Integer.parseInt(st.nextToken());
            int si = Integer.parseInt(st.nextToken());
            si = Math.min(C, si);
            deliveries[i] = new Delivery(ariv, dep, si);
        }
        Arrays.sort(deliveries);

        int answer = 0;
        for (Delivery delivery : deliveries) {
            int cap = 0;
            for (int i = delivery.departure; i < delivery.arrival; i++) {
                cap = Math.max(village[i], cap);
            }
            int get = 0;
            if (C - cap >= delivery.size) get = delivery.size;
            else get = C - cap;
            for (int i = delivery.departure; i < delivery.arrival; i++) {
                village[i] += get;
            }
            answer += get;
        }
        System.out.println(answer);

    }

    static class Delivery implements Comparable<Delivery> {
        int departure;
        int arrival;
        int size;

        public Delivery(int departure, int arrival, int size) {
            this.departure = departure;
            this.arrival = arrival;
            this.size = size;
        }

        @Override
        public int compareTo(Delivery o) {
            if (this.arrival == o.arrival) return this.departure - o.departure;
            return this.arrival - o.arrival;
        }

        @Override
        public String toString() {
            return departure + " " + arrival + " " + size;
        }
    }
}