import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, K;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Potion> pq = new PriorityQueue<>((a, b) -> b.size - a.size);
        for (int i = 1; i <= K; i++) {
            pq.add(new Potion(i, Integer.parseInt(st.nextToken())));
        }
        int idx = 1;
        while (!pq.isEmpty()) {
            Potion cur = pq.poll();
            if (answer[idx - 1] == cur.id) {
                if (pq.isEmpty()) {
                    System.out.println(-1);
                    return;
                } else {
                    Potion next = pq.poll();
                    pq.add(cur);
                    answer[idx++] = next.id;
                    if (--next.size > 0) {
                        pq.add(cur);
                    }
                    continue;
                }
            }
            answer[idx++] = cur.id;
            if (--cur.size > 0) {
                pq.add(cur);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.print(sb);

    }

    static class Potion {
        int id;
        int size;

        public Potion(int id, int size) {
            this.id = id;
            this.size = size;
        }

    }
}