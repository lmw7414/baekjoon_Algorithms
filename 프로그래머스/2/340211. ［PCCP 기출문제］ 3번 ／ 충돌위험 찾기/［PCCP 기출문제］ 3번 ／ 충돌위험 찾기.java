import java.util.*;

class Solution {
    static List<Node>[] robotWays; // 로봇의 ArrayList 인덱스는 시간 초를 의미
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        robotWays = new List[routes.length + 1];
        for(int i = 1; i <= routes.length; i++) robotWays[i] = new ArrayList<>();

        for(int id = 1; id <= routes.length; id++) {
            // 해당 로봇의 경로 설정
            for(int p = 0; p < routes[id - 1].length; p++) {
                int next = routes[id-1][p] - 1;
                if(p == 0){
                    robotWays[id].add(new Node(points[next][0], points[next][1])); // 로봇의 초기 위치
                    continue;
                }
                
                int curR = robotWays[id].get(robotWays[id].size() - 1).r;
                int curC = robotWays[id].get(robotWays[id].size() - 1).c;
                while(!isSame(curR, curC, points[next][0], points[next][1])) {
                    Node nextNode = next(curR, curC, points[next][0], points[next][1]);
                    robotWays[id].add(new Node(nextNode.r, nextNode.c));
                    curR = robotWays[id].get(robotWays[id].size() - 1).r;
                    curC = robotWays[id].get(robotWays[id].size() - 1).c;
                }
                // System.out.println();
            }
            
        }
        
        int maxTime = 0;
        for(int id = 1; id < robotWays.length; id++) maxTime = Math.max(maxTime, robotWays[id].size() - 1);
        // System.out.println(maxTime);
        
        int[][] arr;
        for(int time = 0; time <= maxTime; time++) {
            arr = new int[101][101];
            for(int id = 1; id < robotWays.length; id++) {
                if(time > robotWays[id].size() - 1) continue;
                Node pos = robotWays[id].get(time);
                arr[pos.r][pos.c]++;
                if(arr[pos.r][pos.c] == 2) answer++;
            }
        }
        
        // for(int id = 1; id < robotWays.length; id++) {
        //     for(Node n : robotWays[id]) {
        //         System.out.printf("[%d, %d] -> ", n.r, n.c);
        //     }
        //     System.out.println();
        // }
        
        
        return answer;
    }
    
    public boolean isSame(int curR, int curC, int destR, int destC) {
        return curR == destR && curC == destC;
    }
    
    public Node next(int curR, int curC, int destR, int destC) {
        if(curR == destR) { // c 이동
            if(Math.abs(curC + 1 - destC) < Math.abs(curC - 1 - destC))
                return new Node(curR, curC + 1);
            else return new Node(curR, curC - 1);
        } else { // r 이동
            if(Math.abs(curR + 1 - destR) < Math.abs(curR - 1 - destR))
                return new Node(curR + 1, curC);
            else return new Node(curR - 1, curC);
        }
    }
    
    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}