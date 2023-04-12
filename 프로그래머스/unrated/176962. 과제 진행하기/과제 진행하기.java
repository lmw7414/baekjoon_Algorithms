import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        PriorityQueue<HW> pq = new PriorityQueue<>(
            (o1, o2) -> {
                if(o1.hour == o2.hour)
                    return o1.min - o2.min;
                return o1.hour - o2.hour;
            }
        );
        
        for(String[] str : plans) {
            String subject = str[0];
            String[] time = str[1].split(":");
            String playtime = str[2];
            pq.add(new HW(subject, Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(playtime)));
        }
    
        
        // 멈춰둔 과제가 여러 개일 경우, 가장 최근에 멈춘 과제부터 시작 -> 스택 필요
        Stack<HW> stack = new Stack();
        while(!pq.isEmpty()) {
            HW cur = pq.poll();
            HW next = pq.peek();
            
            int term = interval(cur, next);
            
            if(cur.playtime > term) { // 숙제를 잠시 멈춰야한다.
                cur.playtime -= term;
                stack.push(cur);
            } else {  // 숙제를 다 끝낼 수 있다. 끝내고 시간이 남으면 밀린 숙제를 하자. 끝냈으면 정답리스트에 추가
                answer.add(cur.subject);
                int freetime = interval(cur, next);  //여유 시간
                freetime -= cur.playtime;
                // 스택에 밀린 숙제가 있다면 꺼내서 여유시간만큼 차감
                // 끝났다면 정답리스트 추가
                // 아직 안끝났다면 다시 스택에 추가
                if(freetime > 0) {
                    while(!stack.isEmpty()) {
                        HW late = stack.pop();
                    
                        if(late.playtime > freetime) { //덜 끝난경우 플레이 타임 차감 후, 다시 스택에 저장
                            late.playtime -= freetime;
                            stack.push(late);
                            break;
                        } else {  // 밀린숙제를 마친 경우
                            answer.add(late.subject);
                            freetime -= late.playtime;
                        }
                    }
                }
            }
            
            
        }
        // 스택에 남은 과제들 처리하기
        while(!stack.isEmpty()) {
                answer.add(stack.pop().subject);
        }
        
        String[] ans = new String[answer.size()];
        for(int i = 0; i < ans.length; i++)
            ans[i] = answer.get(i);
        return ans;
    }
    
    public static int interval(HW first, HW second) {
        if(second == null)
            return (24 * 60) - ((first.hour * 60) + first.min);
        return ((second.hour * 60) + second.min) - ((first.hour * 60) + first.min);
    }
    
    class HW {
        String subject;
        int hour;
        int min;
        int playtime;
    
        public HW(String subject, int hour, int min, int playtime) {
            this.subject = subject;
            this.hour = hour;
            this.min = min;
            this.playtime = playtime;
        }
    }
    
}


