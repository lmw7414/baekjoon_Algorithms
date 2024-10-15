import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 현재 연료 상태에서 갈 수 있는 만큼 이동하며 만나는 주유소의 연료를 pq(연료 내림차순)에 저장
 * 2. 연료를 다 썼다면 pq에서 뽑아 연료 충천
 * 3. 갈 수 있는 거리가 L보다 작다면 -1
 * 4. 이미 넘어갔다면 cnt 출력
 */
public class Main {
    static int N, L, P;
    static PriorityQueue<Station> stations = new PriorityQueue<>((a1, b1) -> {
        if (a1.loc == b1.loc) return b1.gage - a1.gage;
        return a1.loc - b1.loc;
    });
    static PriorityQueue<Integer> pq = new PriorityQueue<>((a1, b1) -> b1 - a1);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            stations.add(new Station(a, b));
        }

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int answer = 0;
        while (true) {
            if (L <= P) break; // 현재 연료량으로 갈 수 있는지 체크
            // 갈 수 없다면
            addStation(P);
            if (pq.isEmpty()) {
                answer = -1;
                break;
            } else {
//                System.out.println("사용 : " + pq.peek());
//                System.out.println("현재 이동 가능 거리 : " + P + pq.peek());
                P += pq.poll();
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static void addStation(int current) {
        while (!stations.isEmpty()) {
            if (stations.peek().loc <= current) {
//                System.out.println("충전 : " + stations.peek().gage);
                pq.add(stations.poll().gage);
            } else break;
        }
    }


    static class Station {
        int loc;
        int gage;

        public Station(int loc, int gage) {
            this.loc = loc;
            this.gage = gage;
        }
    }
}