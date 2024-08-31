import java.util.*;

/*
1. 1번 노드에 숫자 1,2,3 중 하나를 떨어트려 트리의 리프노트에 숫자를 쌓는 게임
2. 모든 부모 노드는 자신의 자식 노드 중 가장 번호가 작은 노드를 가리키는 간선을 초기 길로 설정
3. 가장 적은 숫자로 target대로 숫자 합을 만들어라. 안되면 -1
--- 규칙
- 1번노드에서 시작
- 숫자는 길인 간선을 따라 리프노드까지 떨어짐
- 숫자가 리프노드에 도달하면, 숫자가 지나간 각 노드는 현재 길로 연결된 자식 노드 다음으로 번호가 큰 자식 노드를 가리키는 간선을
새로운 길로 설정하고 기존의 길은 끊음
    - 만약 현재 길로 연결된 노드의 번호가 가장 크면, 번호가 가장 작은 노드를 가리키는 간선을 길로 설정
    - 노드의 간선이 하나라면 계속 하나의 간선을 길로 설정
- 원하는 만큼 계속해서 루트노드에 숫자를 떨어트릴 수 있음
    - 단 앞서 떨어트린 숫자가 리프노드까지 떨어진 후에 새로운 숫자를 떨어트려야 함
    
[문제 해결 프로세스]
1. 맵으로 트리 구조 생성
  - 키 : 노드 번호
  - 밸류 : 가리키는 자식 인덱스, 자식노드 리스트
2. 값이 들어갈 리프 노드 파악 -> DP
2. 정답 -> 길이가 짧으면, 길이가 같다면 오름차순인 것
[제한 사항]
- 노드의 개수는 최대 101개
- 1번 노드는 항상 루트 노드
*/

class Solution {
    
    static Map<Integer, Child> hm = new HashMap<>();
    static List<Integer> dp = new ArrayList<>();
    static int[] cnts;
    static List<Integer> answer;
    public int[] solution(int[][] edges, int[] target) {
        cnts = new int[target.length]; 
        initTree(edges);
        
        calc(target, 0);
        
        if (answer == null) return new int[]{-1};
        
        int[] result = new int[answer.size()];
        for(int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(answer.size() - 1 - i);
        }
        return result;
    }
    
    
    public void calc(int[] target, int depth) {
        
        // 최소 조건 만족. 만족 못하면 숫자 추가
        if (isPossible(target)) {
            List<Integer> ans = new ArrayList<>();
            int[] sums = new int[target.length];
            for(int i = 0; i < target.length; i++) {
                sums[i] = target[i];
            }

            for(int i = depth - 1; i >= 0; i--) {  // 자식노드 순서대로
                for(int card = 3; card >= 1; card--) {
                    if(sums[dp.get(i)-1] - card >= (cnts[dp.get(i)-1]-1) ) {
                        ans.add(card);
                        sums[dp.get(i)-1] -= card;
                        cnts[dp.get(i)-1]--;
                        break;
                    }
                }

            }
            answer = ans;
            //for(int i : ans) System.out.print(i + " ");
            return;
        }
        int next = nextLeaf(depth);
        if(isOk(target, next - 1)) {
            cnts[next - 1]++;
            calc(target, depth + 1);
        }
        
    }
    
    public boolean isPossible(int[] target) {
        for(int i = 0; i < target.length; i++) {
            if(target[i] == 0) continue;
            if(target[i] > cnts[i] * 3) return false;
        }
        return true;
    }
    
    public boolean isOk(int[] target, int idx) {
        return cnts[idx] + 1 <= target[idx]; 
    }
    
    
    // 다음에 가야할 노드 번호 탐색
    // 이전에 간 위치라면 dp에서 바로 리턴
    public int nextLeaf(int order) {
        if(dp.size() > order) return dp.get(order);
        
        int cur = 1; // root 노드 idx
        while(true) {
            if(!hm.containsKey(cur)) {

                dp.add(cur);
                return cur;
            }
            Child childs = hm.get(cur);
            int next = childs.list.get(childs.idx);
            childs.changeWay();
            cur = next;
        }
    }
    
    
    public void printTree() {
        for(Integer key : hm.keySet()) {
            System.out.println("key: " + key);
            for(int node : hm.get(key).list) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
    
    public void initTree(int[][] edges) {
        for(int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            if(!hm.containsKey(parent)) {
                hm.put(parent, new Child());
                hm.get(parent).list.add(child);
            } else {
                hm.get(parent).list.add(child);
            }
        }
        // 노드의 자식 노드 오름 차순 정렬
        for(Integer key : hm.keySet()) {
            Collections.sort(hm.get(key).list);
        }
    }
    
    static class Child {
        int idx;
        List<Integer> list;
        public Child() {
            idx = 0;
            list = new ArrayList<>();
        }
        public int changeWay() {
            idx = (idx + 1) % list.size();
            return idx;
        }
    }
}