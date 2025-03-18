import java.util.*;
/*
[입력]
노드의 개수 40만개
1. 완탐 불가 40만 * 40만
2. 하나를 기준으로 홀짝, 역홀짝인지 판별
3. 그룹 생성
4. 홀짝 노드 또는 역홀짝 노드 개수 둘 중 1이라면 +1(홀짝, 역홀짝 트리 중 동일한 것)

*/

class Solution {
    static Map<Integer, List<Integer>>tree = new HashMap<>();
    static Set<Integer> visit = new HashSet<>();
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = {0, 0};
        
        // 1. initTree
        for(int node : nodes) {
            tree.put(node, new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        
        for(int node : nodes) {
            if(visit.contains(node)) continue;
            visit.add(node);
            int[] check = new int[4];
            check[checkNode(node)]++;
            
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(node);
            
            while(!queue.isEmpty()) {
                int cur = queue.poll();
                for(int next : tree.get(cur)) {
                    if(visit.contains(next)) continue;
                    visit.add(next);
                    check[checkNode(next)]++;
                    queue.add(next);
                }
            }
            // System.out.println("----");
            // for(int i = 0; i< 4; i++) {
            //     System.out.print(check[i] + " ");
            // }
            //System.out.println();
            if((check[0] == 1 && check[1] == 0) || (check[0] == 0 && check[1] == 1)) answer[0]++;
            if((check[2] == 1 && check[3] == 0) || (check[2] == 0 && check[3] == 1)) answer[1]++;
            //System.out.println(answer[0] + " " + answer[1]);
        }
        
        return answer;
    }
    
    
    //홀0, 짝1, 역홀2, 역짝3
    public int checkNode(int node) {
        int cnt = tree.get(node).size(); // 연결되어 있는 노드 개수
        if(cnt % 2 == 0) { // 짝, 역홀수
            if(node % 2 == 0) return 1;
            else return 2;
        } else {// 홀, 역짝수
            if(node % 2 == 0) return 3;
            else return 0;
        }
    }
    
}