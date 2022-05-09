package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class problem1260 {  // class 명 Main으로 변경할 것

    public static void main (String[] args) {

        Scanner scan =new Scanner(System.in);
        dfsGraph dg;
        bfsGraph bg;
        int n;  //정점의 개수
        int m;  // 간선의 개수
        int startNum; // 시작점


        n = scan.nextInt();
        m = scan.nextInt();
        startNum = scan.nextInt();

        dg = new dfsGraph(n, m);
        bg = new bfsGraph(n, m);

        dg.initGraph();
        bg.initGraph();

        for(int i=0; i<m; i++){
            int v1 = scan.nextInt();
            int v2 = scan.nextInt();
            dg.add(v1, v2);
            bg.add(v1, v2);
        }
        dg.dfs(startNum);
        System.out.println();
        bg.bfs(startNum);

    }

}

class Graph{
    public int n;
    public int m;
    public int [][]graph;
    public boolean visitArr[];


    public Graph(int n,int m){
        this.n = n;
        this.m = m;
        graph = new int[this.n+1][this.n+1];
        visitArr = new boolean[this.n+1];
    }
    public void initGraph() {
        for (int i=0; i<= n; i++ ) {
            visitArr[i] = false;
            for (int j = 0; j <= n; j++)
                graph[i][j] = 0;
        }
    }
    public void add (int v1, int v2) {
        //양방향 그래프
        graph[v1][v2] = 1;
        graph[v2][v1] = 1;
    }

}
class bfsGraph extends Graph{

    Queue<Integer> queue = new LinkedList<>();

    public bfsGraph (int n, int m) {
        super(n, m);
    }
    public void bfs(int startNum){
        queue.add(startNum);
        //System.out.print(startNum + " ");

        while(!queue.isEmpty()){
            int i= queue.poll();
            visitArr[i] = true;

            for(int j = 1; j<= n; j++) {
                if (graph[i][j] == 1 && visitArr[j] == false) {

                    queue.add(j);
                    visitArr[j] = true;
                }
            }
            System.out.print(i + " ");
        }

    }
}

class dfsGraph extends Graph {

    public dfsGraph (int n, int m) {
        super(n, m);
    }
    public void dfs(int startNum){

        visitArr[startNum] = true;
        System.out.print(startNum +" ");

        for(int i=1; i<=n; i++) {
            if(graph[startNum][i] == 1 && visitArr[i] == false )
                dfs(i);
        }
        return;
    }
}