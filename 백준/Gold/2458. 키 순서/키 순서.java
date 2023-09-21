import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * 1번부터 N번까지 번호가 붙어져 있는 학색들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어져있다.
 * 학생들의 키를 비교한 결과가 주어질 때, 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇명인지 계산하여 출력
 * A : 나한테 들어오는 것(나보다 작은 것)
 * B : 내가 가는 것(나보다 큰 것)
 * A + B = N -1이면 나의 위치를 알 수 있음
 */
public class Main {
    static int N, M;
    static List<Integer>[] adjList;
    static int[] come, out;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        come = new int[N + 1];
        out = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList[A].add(B);
        }

        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            dfs(i, i);
        }
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (come[i] + out[i] == N - 1) answer++;
        }
        System.out.println(answer);
    }

    public static void dfs(int start, int next) {
        visit[next] = true;
        for (int n : adjList[next]) {
            if (visit[n]) continue;
            dfs(start, n);
            come[start]++;
            out[n]++;
        }
    }
}