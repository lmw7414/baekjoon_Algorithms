import java.util.*;
/*
N: 최대 10만개의 등대
Edge: N-1
한 뱃길의 양쪽 끝 등대 중 적어도 하나는 켜져 있도록

1. degree 1인 노드 탐색
*/
class Solution {
    static int answer = 0;
    static int[] degree;
    static List<Integer>[] adjList;
    public int solution(int n, int[][] lighthouse) {
        degree = new int[n + 1];
        adjList = new List[n + 1];
        for(int i = 1; i <= n; i++) adjList[i] = new ArrayList<>();
        for(int[] edge: lighthouse) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }
    
        calc(n);        
        return answer;
    }
    
    public void calc(int n) {
        boolean flag = false;
        while(!flag) {
            flag = true;
            for(int i = 1; i <= n; i++) {
                if(degree[i] != 1) continue;
                flag = false;
                delete(adjList[i].get(0));
            }
        }
    }
    public void delete(int node) {
        for(int child : adjList[node]) {
            degree[child]--;
            degree[node]--;
            adjList[child].remove(Integer.valueOf(node));
        }
        adjList[node] = null;
        answer++;
    }
    
}