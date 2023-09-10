import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * 모양은 같으나, 무게가 모두 다른 N개의 구슬
 * N은 홀수로 1부터 N까지 구슬에 번호가 적혀있다.
 * 무게가 전체의 중간인 구슬을 찾아야 함 (N + 1) / 2번째 -> 즉 N이 7이라면 4번째 구슬을 찾아야 함
 * 우리에게 양팔저울이 있어 한쌍의 구슬을 골라서 양팔 저울의 양쪽에 하나씩 올려보면 알 수 있다.
 * 이렇게 M개의 쌍을 골라서 가각 양팔 저울에 올려서 어느 것이 무거운가를 모두 알아냈다.
 * -> 가운데가 되지 않으려면 자신보다 큰 것이 (N+1)/2 개 이상이면 안된다.
 *
 * [입력]
 * N : 구슬의 개수
 * M : 저울에 올려본 쌍의 개수
 * 두개의 구슬 A, B
 * A > B
 *
 * [문제 해결 프로세스]
 * 1. 경로 이어주기
 *   - 커지는 방향으로 간선을 이어주기
 *   - 작아지는 방향으로 간선을 이어주기
 * 2. 1부터 N까지 위의 두가지 방법으로 모두 탐색
 * 3. 완전 탐색을 돌렸을 때 각 인덱스의 카운트가 (N+1)/2보다 크거나 같으면 안된다.
 * 4. 위의 경우에 해당될때 카운트 증가
 */

public class Main {

    static int N, M;
    static int MID;
    static List<Integer>[] adjList;
    static int[] arr, rarr;  // 정방향 DFS, 역방향 DFS 저장할 배열
    static boolean[] visit;
    static int answer = 0;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MID =  (N + 1) / 2;

        arr = new int[N + 1];
        rarr = new int[N + 1];
        visit = new boolean[N + 1];

        adjList = new List[N + 1];
        for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList[A].add(B);
        }

        for(int i = 1; i <= N; i++) {
            Arrays.fill(visit, false);
            DFS(i, i);
        }

        for(int i = 1; i<= N; i++) {
            if(arr[i] >= MID) answer++;
            if(rarr[i] >= MID) answer++;
        }
        System.out.println(answer);
    }

    public static void DFS(int root, int idx) {
        visit[idx] = true;
        for(int next : adjList[idx]) {
            if(!visit[next]) {
                rarr[next]++;
                arr[root]++;
                DFS(root, next);
            }
        }
    }
}