import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static int N;
    static int[] answer;
    static boolean[][] visit;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        answer = new int[N];
        list = new List[N];
        visit = new boolean[N][10];
        for (int n = 0; n < N; n++) {
            list[n] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for (int i = 0; i < m; i++) {
                list[n].add(Integer.parseInt(st.nextToken()));
            }
        }
        calc(0);
        System.out.println(-1);
    }

    public static void calc(int depth) {
        if (depth == N) {
            for (int num : answer) System.out.println(num);
            System.exit(0);
        }

        for (int idx = 0; idx < list[depth].size(); idx++) {
            if (depth == 0) {
                if(visit[depth][list[depth].get(idx)]) continue;
                answer[depth] = list[depth].get(idx);
                visit[depth][list[depth].get(idx)] = true;
            }
            else {
                if (answer[depth - 1] == list[depth].get(idx)) continue;
                if(visit[depth][list[depth].get(idx)]) continue;
                answer[depth] = list[depth].get(idx);
                visit[depth][list[depth].get(idx)] = true;
            }
            calc(depth + 1);
        }

    }
}