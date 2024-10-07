import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;  // 사람의 수, 파티의 수
    static List<List<Integer>> parties = new ArrayList<>();
    static List<List<Integer>>[] list;
    static boolean[] truer;
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        truer = new boolean[N + 1];
        for (int i = 1; i <= N; i++) list[i] = new ArrayList<List<Integer>>();
        st = new StringTokenizer(br.readLine());
        int trueSize = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수

        for (int i = 0; i < trueSize; i++) {
            int num = Integer.parseInt(st.nextToken());
            truer[num] = true;
            queue.add(num); // 진실을 아는 사람 명단
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            List<Integer> party = new ArrayList<>();
            for (int i = 0; i < size; i++) party.add(Integer.parseInt(st.nextToken()));
            parties.add(party);
            for (int p : party) list[p].add(party);
        }

        while (!queue.isEmpty()) {
            int tr = queue.poll();
            for (List<Integer> p : list[tr]) {
                if (p == null) continue;
                for (int member : p) {
                    if (truer[member]) continue;
                    queue.add(member);
                    truer[member] = true;
                }
                parties.remove(p);
            }
        }
        int answer = 0;
        for (List<Integer> party : parties) {
            if (party == null) continue;
            answer++;
        }
        System.out.println(answer);

    }

}