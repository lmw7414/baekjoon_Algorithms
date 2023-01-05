package baekjoon;

import java.util.*;

public class problem1753 {

    static int N;
    static int C;
    static LinkedList<Node>[] list;
    static int d[];
    static int start;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        C = sc.nextInt();
        start = sc.nextInt();

        list = new LinkedList[N + 1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new LinkedList<Node>();
        }
        d = new int[N + 1];

        for (int i = 0; i < C; i++) {
            int u, v, w;
            u = sc.nextInt();
            v = sc.nextInt();
            w = sc.nextInt();

            list[u].add(new Node(v, w));
        }
        dijkstra(start);
        printResult();
    }

    public static void dijkstra(int start) {

        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w > o2.w ? 1 : -1;
            }
        });
        for (int i = 1; i < d.length; i++) {
            d[i] = INF;
        }
        queue.add(new Node(start, 0));
        d[start] = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();


            while (!list[node.v].isEmpty()) {
                Node n = list[node.v].poll();

                if (d[n.v] > d[node.v] + n.w) {
                    d[n.v] = d[node.v] + n.w;
                    queue.add(new Node(n.v, d[n.v]));
                }
            }
        }
    }

    public static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < d.length; i++) {
            if (d[i] == INF)
                sb.append("INF\n");
            else
                sb.append(d[i] + "\n");
        }
        System.out.print(sb.toString());
    }

    public static class Node {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
