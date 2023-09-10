import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제설명]
 * 달빛여우가 보름달의 달빛을 받으면 아름다운 구미호로 변신한다.
 * 달빛늑대가 달빛을 받으면 늑대인간으로 변신한다.
 * 1번부터 N번까지의 번호가 붙은 N개의 나무 그루터기 존재
 * 그루터기들 사이에는 M개의 오솔길이 나 있다.
 * 오솔길은 어떤 방향으로든 지나갈 수 있으며(무방향)
 * 어떤 두 그루터기 사이에 두 개 이상의 오솔길이 나 있는 경우는 없다.
 * 달빛여우와 달빛늑대는 1번 나무 그루터기에 살고 있다.
 * <p>
 * 보름달이 뜨면 나무 그루터기들 중 하나가 달빛을 받아 밝게 빛나게된다.
 * 그러면 달빛늑대와 달빛여우는 먼저 달빛을 독차지하기 위해 최대한 빨리 오솔길을 따라서 해당 그루터기로 가야한다.
 * -> 달빛 여우는 늘 일정한 속도로 달려가는 반면,
 * -> 달빛 늑대는 달빛 여우보다 빨리 달릴수는 있지만 체력이 부족하기 때문에 다른 전략을 사용한다.
 * -> 달빛 늑대는 출발할 때 오솔길 하나를 달빛 여우의 두배 속도로 달려가고, 그 뒤로는 오솔길 하나를 달빛 여우의 절반속도로 걸어가며
 * 체력을 회복하고 나서 다음 오솔길을 다시 달빛 여우의 두배 속도로 달려 가는 것을 반복한다.
 * 달빛여우가 달빛 늑대보다 먼저 도착할 수 있는 그루터기에 달빛을 비춰주려고 한다. 이런 그루터기가 몇개나 있는지 알아보자.
 * <p>
 * [입력]
 * 1. N : 나무 그루터기 개수(2<=N<=4000)
 * 2. M : 오솔길의 개수
 * 3. a,b,d : a번 그루터기와 b번 그루터기 사이의 길이가 d인 오솔길 존재
 * <p>
 * [문제 해결 프로세스]
 * 1. 여우의 거리에 관해서는 다익스트라로 모든 노드 간의 거리 탐색
 * 2. 늑대에 대해서는 BFS로 모든 노드간의 거리를 구한다. -> 시간초과, 정확한 값 계산 안됨. 다익스트라로 해야함
 * - depth가 홀수일 때는 오솔길 거리의 1/2로
 * - depth가 짝수일 때는 오솔길 거리의 x 2로
 * 3. 늑대와 여우의 모든 노드에 대한 거리를 구했다면 2번 그루터기부터 N번 그루터기 중 여우의 그루터기 인덱스가 작은 것을 카운트
 */
public class Main {

    static int N, M;
    static List<Node>[] graph;
    static long[] fDistance; // 여우의 최단거리
    static long[][] wDistance; // 늑대의 최단거리

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        graph = new List[N + 1];
        fDistance = new long[N + 1];
        wDistance = new long[2][N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long d = Long.parseLong(st.nextToken()) * 2;
            graph[a].add(new Node(b, d));
            graph[b].add(new Node(a, d));
        }

        initDistance();
        dijkstraFox(1);
        dijkstraWolf(1);
        int cnt = 0;
        for (int i = 2; i <= N; i++)
            if (fDistance[i] < Math.min(wDistance[0][i], wDistance[1][i])) cnt++;
        System.out.println(cnt);
    }

    public static void initDistance() {
        Arrays.fill(fDistance, Long.MAX_VALUE);
        Arrays.fill(wDistance[0], Long.MAX_VALUE);
        Arrays.fill(wDistance[1], Long.MAX_VALUE);
        fDistance[1] = 0;
        wDistance[0][1] = 0;
    }

    // 여우를 위한 DFS
    public static void dijkstraFox(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(fDistance[cur.v] < cur.w) continue;

            for (Node next : graph[cur.v]) {
                long weight = fDistance[cur.v] + next.w;
                if (fDistance[next.v] > weight) {
                    fDistance[next.v] = weight;
                    pq.add(new Node(next.v, weight));
                }
            }
        }
    }

    public static void dijkstraWolf(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(wDistance[cur.state][cur.v] < cur.w) continue;

            for(Node next : graph[cur.v]) {
                int state = 1- cur.state;
                long weight = wDistance[cur.state][cur.v] + ((cur.state == 0) ? next.w / 2 : next.w * 2);
                if(wDistance[state][next.v] > weight) {
                    wDistance[state][next.v] = weight;
                    pq.add(new Node(next.v, weight, state));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int v;
        long w;
        int state;

        public Node(int v, long w) {
            this.v = v;
            this.w = w;
            state = 0;
        }

        public Node(int v, long w, int state) {
            this.v = v;
            this.w = w;
            this.state = state;
        }


        @Override
        public int compareTo(Node o) {
            if(this.w < o.w) return -1;
            else return 1;
        }
    }
}