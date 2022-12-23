package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 4 2 10
 * 7 4 5 6
 */
public class problem13335 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<Truck> truckQueue = new LinkedList<>();
        Queue<Truck> bridge = new LinkedList<>();

        int N = Integer.parseInt(st.nextToken()); // 트럭의 개수
        int W = Integer.parseInt(st.nextToken()); // 다리의 길이
        int L = Integer.parseInt(st.nextToken()); // 최대 하중(작거나 같아야 한다.)

        int currentWeight = 0;
        int time = 1;
        int cur = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            truckQueue.add(new Truck(Integer.parseInt(st.nextToken())));
        }

        while (N > 0 ) {
            if (!truckQueue.isEmpty()) {
                cur = truckQueue.peek().weight;
            }
            if (!truckQueue.isEmpty() && bridge.size() < W && currentWeight + cur <= L) {
                currentWeight += cur;
                bridge.add(truckQueue.poll());
                cur = 0;
            }
            if (!bridge.isEmpty()) {
                Iterator<Truck> it = null;
                it = bridge.iterator();
                while (it.hasNext()) {
                    Truck truck = it.next();
                    truck.curIdx++;
                }
            }
            if (bridge.peek().curIdx > W) {
                currentWeight -= bridge.peek().weight;
                bridge.poll();
                N--;
            }
            time++;
        }
        System.out.println(time);
    }

    public static class Truck {
        int weight;
        int curIdx;

        public Truck(int weight) {
            this.weight = weight;
            this.curIdx = 1;
        }
    }
}




