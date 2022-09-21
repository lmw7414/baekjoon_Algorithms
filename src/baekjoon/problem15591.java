package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//MooTube(Silver)
public class problem15591 {

    static ArrayList<Node> arrayList[];
    static int N;
    static int Q;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 노드의 개수
        Q = Integer.parseInt(st.nextToken());  // 문제 개수

        arrayList = new ArrayList[N + 1];

        for(int i = 0; i< arrayList.length; i++)
            arrayList[i] = new ArrayList<>();


        for(int i = 1; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            arrayList[p].add(new Node(q, r));
            arrayList[q].add(new Node(p, r));
        }

        //printN();

        for(int i=0; i< Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            System.out.println(countK(k,v));
        }
    }

    public static int countK(int k, int v) {
        int count = 0;
        int value;
        Queue<Integer> queue = new LinkedList();
        boolean visited[] = new boolean[N+1];

        queue.add(v);
        visited[v] = true;
        while(!queue.isEmpty()) {
            value = queue.poll();

            for(int i = 0; i<arrayList[value].size(); i++) {
                if(!visited[arrayList[value].get(i).index]) {

                    if (arrayList[value].get(i).weight >= k) {
                        count++;
                        queue.add(arrayList[value].get(i).index);
                        visited[arrayList[value].get(i).index] = true;
                    }
                }
            }
        }
        return count;
    }

    static void printN() {
        for(int i = 0; i<arrayList.length; i++) {
            for(int j = 0; j<arrayList[i].size(); j++)
                System.out.println("index : " + arrayList[i].get(j).index + " weight : " + arrayList[i].get(j).weight);
        }
    }
    static class Node {
         int index;
         int weight;

        Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
}
