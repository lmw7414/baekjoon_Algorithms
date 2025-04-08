import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [입력]
 * N개의 문제 최대 5만개
 * T: 하루에 풀 수 있는 시간 최대 10만
 * [주의]
 * N개의 문제들 중 링크를 통해 도달할 수 없는 문제는 없다
 * 처음 고른 문제는 보너스로 취급되어 무조건 정답처리 -> 첫번째 문제를 푸는데 0초의 시간이 소요
 * [문제 해결 프로세스]
 * 1. 임의의 점으로 부터 가장 끝 점 찾기
 * 2. 해당 점을 시작으로 깊이가 길지만 가장 짧은 시간의 경로 찾기
 */

public class Main {
    static List<Edge>[] adjList;
    static int N, T;
    static boolean[] visit;
    static int solved = 0;
    static int time = Integer.MAX_VALUE;
    static int end = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        adjList = new List[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) adjList[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[v].add(new Edge(e, w));
            adjList[e].add(new Edge(v, w));
        }
        visit[1] = true;
        DFS(1, 1, 0);

        visit = new boolean[N + 1];
        visit[end] = true;
        solved = 0;
        time = Integer.MAX_VALUE;
        DFS(end, 1, 0);
        System.out.println((int)Math.ceil((double)time / T));
    }

    // cur: 현재 푸는 문제, depth: 지금까지 푼 문제 수, val: 걸린 시간
    public static void DFS(int cur, int depth, int val) {
        if(depth > solved) {
            solved = depth;
            time = val;
            end = cur;
        } else if(depth == solved) {
            if (val < time) {
                time = val;
                end = cur;
            }
        }

        for (Edge next : adjList[cur]) {
            if (visit[next.v]) continue;
            visit[next.v] = true;
            DFS(next.v, depth + 1, val + next.w);
            visit[next.v] = false;
        }
    }

    static class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

}