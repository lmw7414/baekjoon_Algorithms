import java.io.*;
import java.util.*;

/**
 * [문제 설명]
 * 1. 스브러스는 한 아이의 사탕을 뺏으면 그 아이 친구들의 사탕도 모조리 뺏어버린다.
 * 2. 사탕을 빼앗긴 아이들은 거리에 주저 앉아 울고, K명 이상의 아이들이 울기 시작하면 온 어른들이 거리에 나온다.
 * 3. 스브러스가 어른들에게 들키지 않고 최대한 뺏을 수 있는 사탕의 양을 구하여라
 * [입력]
 * N 거리에 있는 아이들의 수 | 최대 3만
 * M 아이들의 친구 관계 수 | 최대 10만
 * K 최소 아이 수 | 최대 3000
 * <p>
 * [문제 해결 프로세스]
 * K명 미만이면서 최대 수익을 내야함
 * 친구들의 관계를 저장하기
 * 친구 그룹 | 인원 수 | 받을 수 있는 사탕의 양을 우선적으로 만들어야 함
 * knapsack으로 문제 해결
 * dp[i][j] | i는 그룹 Id, j는 선택된 인원, dp[i][j]는 얻을 수 있는 최대 사탕 개수
 */

public class Main {
    static int N, M, K;
    static int[] candies;
    static List<Integer>[] adjList;
    static List<Group> groups = new ArrayList<>();
    static boolean[] visit;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candies = new int[N + 1];
        visit = new boolean[N + 1];
        adjList = new List[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            candies[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        searchGroup();
        dp = new int[groups.size() + 1][K];

        for (int group = 1; group <= groups.size(); group++) {
            for (int total = 0; total < K; total++) {
                int gPopulation = groups.get(group - 1).size;
                if(total - gPopulation < 0) dp[group][total] = dp[group - 1][total];
                else dp[group][total] = Math.max(dp[group -1][total], dp[group - 1][total - gPopulation] + groups.get(group - 1).candy);
            }
        }
        System.out.println(dp[groups.size()][K - 1]);
    }


    public static void searchGroup() {
        for (int i = 1; i <= N; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            Group group = new Group();
            make(i, group);
            groups.add(group);
        }
    }

    public static void make(int cur, Group group) {
        visit[cur] = true;
        group.size++;
        group.candy += candies[cur];
        for (int next : adjList[cur]) {
            if (!visit[next]) make(next, group);
        }
    }

    static class Group {
        int size;
        int candy;
        public Group() {
            size = 0;
            candy = 0;
        }
    }

}