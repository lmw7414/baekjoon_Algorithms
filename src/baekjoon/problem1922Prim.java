package baekjoon;

import java.util.*;

public class problem1922Prim {

    static int N;
    static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, List<Edge>> edges = new HashMap<Integer, List<Edge>>();
        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i < M; i++) {
            int v1, v2, weight;
            v1 = sc.nextInt();
            v2 = sc.nextInt();
            weight = sc.nextInt();

            if (!edges.containsKey(v1)) {
                edges.put(v1, new ArrayList<>());
                edges.get(v1).add(new Edge(v2, weight));
            } else {
                edges.get(v1).add(new Edge(v2, weight));
            }

            if (!edges.containsKey(v2)) {
                edges.put(v2, new ArrayList<>());
                edges.get(v2).add(new Edge(v1, weight));
            } else {
                edges.get(v2).add(new Edge(v1, weight));
            }
        }

        System.out.println(prim(edges, 1));
    }

    public static int prim(HashMap<Integer, List<Edge>> edges, int startNode) {
        Set<Integer> visited = new HashSet<>();
        visited.add(startNode);
        PriorityQueue<Edge> adjacentList = new PriorityQueue<>((a1, a2) -> a1.weight - a2.weight);
        adjacentList.addAll(edges.get(startNode));

        int total = 0;
        while (!adjacentList.isEmpty()) {
            Edge edge = adjacentList.poll();
            if (!visited.contains(edge.v)) {
                visited.add(edge.v);
                adjacentList.addAll(edges.get(edge.v));
                total += edge.weight;
            }
        }

        return total;
    }

    static class Edge {
        int v;
        int weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}
