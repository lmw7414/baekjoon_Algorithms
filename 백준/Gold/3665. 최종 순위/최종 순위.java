import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * impossible인 경우 : 데이터에 일관성이 없어서 순위를 정할 수 없는 경우
 * ?인 경우 : 확실한 순위를 찾을 수 없는 경우 -> 위상정렬에서 inDegree가 0인 노드가 동시에 2개 이상인 경우
 */

public class Main {
    static int[] inDegree;
    static List<Integer>[] check; // 내 뒤에 존재하는 팀을 확인하기 위함
    static final String IMPOSSIBLE = "IMPOSSIBLE\n";
    static final String QUESTION_MARK = "?\n";
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            inDegree = new int[N + 1];
            check = new List[N + 1];
            for (int n = 1; n <= N; n++) check[n] = new ArrayList<>();

            // 현재 순위(InDegree) 등록(ex. 순위가 4위 인 경우 앞에 4팀이 있음)
            int prev = -1;
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                int team = Integer.parseInt(st.nextToken());
                if (n > 0) {
                    check[team].add(prev);
                    for (int num : check[prev]) check[team].add(num);
                }
                prev = team;
                inDegree[team] = n;
            }

            int M = Integer.parseInt(br.readLine());
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int up = Integer.parseInt(st.nextToken());
                int down = Integer.parseInt(st.nextToken());
                swap(up, down);
            }
            sb.append(calc(N));
        }
        System.out.print(sb);
    }


    public static String calc(int N) {
        Queue<Integer> queue = new ArrayDeque<>();
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        for(int i = 1; i <= N; i++) {
            if (queue.size() > 1) return QUESTION_MARK;
            else if(queue.isEmpty()) return IMPOSSIBLE; // 사이클 생성
            int num = queue.poll();
            s.append(num).append(" ");

            for(int j = 1; j <= N; j++) {
                if(--inDegree[j] == 0) queue.add(j);
            }

        }
        return s.append("\n").toString();
    }
    
    public static void swap(int up, int down) {
        if (check[up].contains(down)) {
            check[up].remove(Integer.valueOf(down));
            check[down].add(up);
            inDegree[up]--;
            inDegree[down]++;
        } else {
            check[down].remove(Integer.valueOf(up));
            check[up].add(down);
            inDegree[down]--;
            inDegree[up]++;
        }
    }
}