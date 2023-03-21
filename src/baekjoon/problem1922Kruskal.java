package baekjoon;

import java.util.PriorityQueue;
import java.util.Scanner;

/*
6
9
1 2 5
1 3 4
2 3 2
2 4 7
3 4 6
3 5 11
4 5 3
4 6 8
5 6 8
 */
public class problem1922Kruskal {

    static int N;
    static int M;
    static int parent[];
    static int rank[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        PriorityQueue<Edge> edges = new PriorityQueue<>((a1, a2) -> a1.weight - a2.weight);

        for (int i = 0; i < M; i++) {
            int v1, v2, weight;
            v1 = sc.nextInt();
            v2 = sc.nextInt();
            weight = sc.nextInt();
            edges.add(new Edge(v1, v2, weight));
        }

        parent = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        System.out.println(kruskal(edges));

    }

    public static int kruskal(PriorityQueue<Edge> edges) {
        int total = 0;

        while(!edges.isEmpty()) {
            Edge edge = edges.poll();
            int v1 = edge.v1;
            int v2 = edge.v2;

            if(find(v1) != find(v2)) {
                union(v1, v2);
                total += edge.weight;
            }
        }
        return total;
    }

    public static int find(int v) {
        if(parent[v] == v)
            return v;
        else
            return find(parent[v]);
    }

    public static void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if(rank[root1] > rank[root2])
            parent[root2] = root1;
        else {
            parent[root1] = root2;
            if(rank[root1] == rank[root2])
                rank[root2]++;
        }
    }

    static class Edge {
        int v1;
        int v2;
        int weight;

        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }
}


