import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int boxes = order.length;
        Stack<Integer> stack = new Stack();
        int boxNum = 1;
        
        for(int priorityBox : order) {
            // 우선 순위 박스 찾기
            if(priorityBox >= boxNum) { // 먼저 실어야 할 박스가 벨트에 아직 있는 경우
                while(boxNum <= boxes) {
                    if(boxNum == priorityBox) { //찾은 경우
                        answer++;
                        boxNum++;
                        break;
                    } else if(boxNum < priorityBox) { // 현재 박스보다 우선순위 박스가 뒤에 있을 때
                        while(boxNum != priorityBox)
                            stack.push(boxNum++);             
                        answer++;
                        boxNum++;
                        break;
                    }
                }
            } else { // 벨트에 없는 경우
                while(!stack.isEmpty()) {
                    int get = stack.pop();
                    if(get == priorityBox) {
                        answer++;
                        break;
                    } else if(get > priorityBox)
                        return answer;
                }
            }
        }
        
        return answer;
    }
}