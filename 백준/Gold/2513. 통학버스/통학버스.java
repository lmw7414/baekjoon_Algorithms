
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 아파트 단지 수 | 최대 3만
        K = Integer.parseInt(st.nextToken()); // 통학 버스 정원 | 최대 2000
        S = Integer.parseInt(st.nextToken()); // 학교 위치

        PriorityQueue<District> left = new PriorityQueue<>((a, b) -> a.p - b.p); // 작은 위치 우선순위
        PriorityQueue<District> right = new PriorityQueue<>((a, b) -> b.p - a.p ); // 큰 위치 우선순위
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            if(S > p) left.add(new District(p, cnt));
            else right.add(new District(p, cnt));
        }
        int answer = 0;
        answer += calc(left);
        answer += calc(right);
        System.out.println(answer);
    }


    public static int calc(PriorityQueue<District> pq) {
        int answer = 0;
        int cur = 0; // 현재 버스에 타 있는 인원
        while(!pq.isEmpty()) {
            District dist = pq.poll();
            int d = Math.abs(S - dist.p); // 학교와 단지 간의 거리
            answer += 2 * d * (dist.cnt / K);
            cur = dist.cnt % K;
            if(cur != 0) {
                while(!pq.isEmpty() && cur != K) {
                    int rest = K - cur; // 버스에 추가로 더 태울 수 있는 인원 수
                    District next = pq.peek();
                    if(rest >= next.cnt) { // 다음 마을 인원을 다 태울 수 있는 경우
                        cur += next.cnt;
                        pq.poll();
                    } else { // 다음 마을에 인원이 남아있어서 또 방문해야 하는 경우
                        cur += rest;
                        next.cnt -= rest;
                        break;
                    }
                }
                answer += 2 * d;
            }
        }
        return answer;
    }

    static class District {
        int p; // 위치
        int cnt; // 인원 수

        public District(int p, int cnt) {
            this.p = p;
            this.cnt = cnt;
        }
    }
}
