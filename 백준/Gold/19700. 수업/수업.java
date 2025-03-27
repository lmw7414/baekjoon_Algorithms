
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [주의]
 * 학생들의 키는 모두 다름
 * [문제 해결 프로세스]
 * 1. 키 내림 차순 정렬
 * 2. 내가 들어갈 수 있는 팀 중에서 최대 인원인 팀에 들어가는 것이 좋음
 */
public class Main {
    static int N;
    static List<Integer> teams = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Oj> pq = new PriorityQueue<>((a, b) -> b.height - a.height);
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            pq.add(new Oj(height, before));
        }

        while (!pq.isEmpty()) {
            int idx = getLowerBoundIdx(pq.poll().before);
            if(idx == -1) {
                teams.add(0, 1);
            } else {
                teams.set(idx, teams.get(idx) + 1);
            }
        }

        System.out.println(teams.size());
    }

    public static int getLowerBoundIdx(int key) {
        int left = 0;
        int right = teams.size() - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (teams.get(mid) < key) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    static class Oj {
        int height, before;

        public Oj(int height, int before) {
            this.height = height;
            this.before = before;
        }
    }
    
}