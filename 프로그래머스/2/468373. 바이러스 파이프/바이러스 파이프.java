import java.util.*;

/**
ABC 파이프 최대 10번
완탐: 3* 2^9 -> 1536가지의 경우의 수


최소 = infection개
n: 배양체의 개수
infection: 감염된 배양체의 수

*/
import java.util.*;

class Solution {
    static int K;
    static int N;
    static int ANSWER = 1;
    static List<Node>[] nodes;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        K = k;
        N = n;
        nodes = new List[n + 1];
        for(int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        initEdge(edges);
        // for(int i = 1; i <= n; i++) {
        //     System.out.print(i + ": ");
        //     for(Node node : nodes[i]) {
        //         System.out.printf("[ %d, %d], ", node.next, node.type);
        //     }
        //     System.out.println();
        // }
        
        
        Set<Integer> infections = new HashSet<>();
        infections.add(infection);
        DFS(0, 0, infections);
        return ANSWER;
    }
    
    public void initEdge(int[][] edges) {
       for(int[] edge : edges) {
           // (X, Y, type)
           nodes[edge[0]].add(new Node(edge[1], edge[2]));
           nodes[edge[1]].add(new Node(edge[0], edge[2]));
       }
    }
    
    public void DFS(int cur, int beforeType, Set<Integer> infections) {
        if(cur == K) {
            ANSWER = Math.max(ANSWER, infections.size());
            //for(int inf : infections) System.out.print(inf + " ");
            //System.out.println();
            //System.out.println("---------끝---------");
            return;
        }
        for(int type = 1; type <= 3; type++) {
            if(beforeType == type) continue;
            //1. 감염 -> Set<> 새로 감염된 친구들
            Set<Integer> next = makeInfection(infections, type);
            next.addAll(infections);
            //2. 다음 파이프 열고 DFS(cur + 1, type, 기존 + 새로운)
            DFS(cur + 1, type, next);
        }
    }
    
    public Set<Integer> makeInfection(Set<Integer> infections, int type) {
        //System.out.print("현재 감염체: ");
        //for(int inf : infections) System.out.print(inf + " ");
        //System.out.println();
        
        boolean[] visit = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        for(Integer infection: infections) {
            queue.add(infection);
            visit[infection] = true;
        }
        Set<Integer> result = new HashSet<>();
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            for(Node node : nodes[cur]) {
                if(node.type != type) continue;
                if(visit[node.next]) continue;
                visit[node.next] = true;
                result.add(node.next);
                queue.add(node.next);
            }
        }
        //System.out.print(type + "로 인한 새로운 감염체: ");
        //for(int inf : result) System.out.print(inf + " ");
        //System.out.println();
        return result;
    }
    
    static class Node {
        int next, type;
        
        public Node(int next, int type) {
            this.next = next;
            this.type =type;
        }
    }
}