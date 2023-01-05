package baekjoon;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
// 특정한 최단 경로
public class problem1504 {
    static int INF = Integer.MAX_VALUE;
    static int N;
    static int C;

    static LinkedList<Node>[] list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        C = sc.nextInt();

        list = new LinkedList[N + 1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new LinkedList<>();
        }

        for (int i = 0; i < C; i++) {
            int s, e, w;
            s = sc.nextInt();
            e = sc.nextInt();
            w = sc.nextInt();

            list[s].add(new Node(e, w));
            list[e].add(new Node(s, w));
        }
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();

        int[] d1 = new int[N + 1]; // 1부터 시작하는 최소 비용
        int[] d2 = new int[N + 1]; // v1부터 시작하는 최소 비용
        int[] d3 = new int[N + 1]; // v2부터 시작하는 최소 비용
        for(int i = 0; i< N+1; i++) {
            d1[i] = INF;
            d2[i] = INF;
            d3[i] = INF;
        }

        dijkstra(1, d1);
        dijkstra(v1, d2);
        dijkstra(v2, d3);

        int c1;
        int c2;

        if (d1[v1] == INF || d2[v2] == INF || d3[N] == INF) {
            c1 = INF;
        } else {
            c1 = d1[v1] + d2[v2] + d3[N];
        }
        if (d1[v2] == INF || d3[v1] == INF || d2[N] == INF) {
            c2 = INF;
        } else {
            c2 = d1[v2] + d3[v1] + d2[N];
        }

        if(c1 == INF && c2 == INF)
            System.out.println(-1);
        else
            System.out.println(Math.min(c1, c2));
    }

    public static void dijkstra(int start, int[] d) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w > o2.w ? 1 : -1;
            }
        });

        queue.add(new Node(start, 0));
        d[start] = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(Node n : list[node.v]) {
                if(d[n.v] > d[node.v] + n.w) {
                    d[n.v] = d[node.v] + n.w;
                    queue.add(new Node(n.v, d[n.v]));
                }
            }
        }
    }

    public static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
