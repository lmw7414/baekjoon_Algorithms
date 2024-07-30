import java.util.*;

class Solution {
    List<Integer>[] tree;
    int answer = 0;
    public int solution(int[] info, int[][] edges) {
        tree = new List[info.length];
        for (int i = 0; i < info.length; i++) tree[i] = new ArrayList<>();
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
        List<Integer> available = new ArrayList<>();
        DFS(info, 0, 0, 0, available);
        return answer;
    }
    
    public void DFS(int[] info, int node, int sheep, int wolf, List<Integer> available) {
        if(info[node] == 0) sheep++;
        else wolf++;
        answer = Math.max(answer, sheep);
        
        if(sheep <= wolf) return;
        
        List<Integer> temp = new ArrayList<>();
        for(int next : available) { // 현재 노드를 제외한 다른 곳 방문
            if(next != node) temp.add(next);
        }
        for(int next : tree[node]) temp.add(next);
        for(int next : temp) DFS(info, next, sheep, wolf, temp);
    }
}