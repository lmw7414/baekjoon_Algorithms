package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Watering the Fields
 * MST 문제
 * 가중치가 C보다 작을 경우 경로를 만들 수 없다.
 * 해결 1. 크루스칼로 풀기(최소 가중치부터 접근)
 * - 그래프 내 간선이 적은 경우 O(ElogeE)
 * 해결 2. 프림으로 풀기(하나의 정점에서 부터 접근)
 * - 그래프 내의 간선이 많은 경우 O(ElogV)
 */

public class problem10021 {

    static int N;
    static int C;
    static int x[];
    static int y[];
    static int parent[];

    static PriorityQueue<Node> pq = new PriorityQueue<>((dist1, dist2) -> (int)(dist1.distance - dist2.distance));


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        x = new int[N];
        y = new int[N];
        parent = new int[N];

        for(int i = 0; i< N; i++)
            parent[i] = i;

        for(int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());

        }

        for(int i = 0; i< N-1; i++) {
            for(int j = i+1; j<N; j++) {
                long distance = (x[i]-x[j])*(x[i]-x[j]) + (y[i]-y[j]) * (y[i]-y[j]);

                pq.add(new Node(i, j, distance));
            }
        }

        System.out.println(MST());

    }

    static int MST() {
        int sum = 0;
        int count = 0;

        // C보다 작을 경우 패스
        while(!pq.isEmpty()) {

            Node node = pq.poll();

            if(node.distance < C)
                continue;

            int nd1 = node.start;
            int nd2 = node.end;

            if(!isSameParent(nd1, nd2)) {
                sum += node.distance;
                union(nd1, nd2);
                count++;
            }

            if(count == N-1)
                break;
        }

        if(count != N-1)
            return -1;
        else
            return sum;
    }

    static int find(int x) {
        if(x == parent[x])
            return x;
        return find(parent[x]); // x와 parent[x]가 다를 경우 루트 부모를 찾기 위함
    }

    //노드와 노드를 연결
    static void union(int nd1, int nd2) {
        nd1 = find(nd1);
        nd2 = find(nd2);

        if(nd1 != nd2)
            parent[nd2] = nd1;
    }

    static boolean isSameParent(int nd1, int nd2) {
        nd1 = find(nd1);
        nd2 = find(nd2);

        if (nd1 == nd2) {
            return true;
        }

        return false;
    }

    static class Node {
        int start;
        int end;
        long distance;

        Node(int start, int end, long distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }
}
