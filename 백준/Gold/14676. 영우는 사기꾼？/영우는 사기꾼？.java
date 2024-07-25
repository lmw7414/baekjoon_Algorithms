import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
1차 시도 : 시간초과
- 지을 수 있는 건물이라면
 */
public class Main {
    static int N, M, K;
    static int[] state;
    static boolean[] possible;
    static Set<Integer>[] inDegree, outDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        inDegree = new Set[N + 1];
        outDegree = new Set[N + 1];
        for (int i = 1; i <= N; i++) {
            inDegree[i] = new HashSet<>();
            outDegree[i] = new HashSet<>();
        }
        state = new int[N + 1];
        possible = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            inDegree[e].add(s);
            outDegree[s].add(e);
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken());
            int building = Integer.parseInt(st.nextToken());

            if (option == 1) {
                // 건물을 지을 수 있는지 확인
                if (!possible[building]) { // 건물을 지을 수 없는 상태
                    for (int priority : inDegree[building]) {
                        if (state[priority] <= 0) { // 치트키 쓴 경우
                            System.out.println("Lier!");
                            System.exit(0);
                        } else {
                            possible[building] = true;
                        }
                    }
                }
                state[building]++;
            } else {  // 건물 파괴
                if (state[building] <= 0) {
                    System.out.println("Lier!");
                    System.exit(0);
                } else {  // 건물이 존재
                    state[building]--;
                    if (state[building] == 0) {
                        for (int impossible : outDegree[building]) {
                            possible[impossible] = false;
                        }
                    }
                }
            }
        }
        System.out.println("King-God-Emperor");
    }
}