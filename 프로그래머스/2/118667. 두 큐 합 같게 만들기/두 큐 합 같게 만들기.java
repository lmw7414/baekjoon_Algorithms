import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long A = 0;
        long B = 0;
        Queue<Integer> aQ = new ArrayDeque<>();
        Queue<Integer> bQ = new ArrayDeque<>();
        
        for(int n : queue1) {
            A += n;
            aQ.add(n);
        }
        for(int n : queue2) {
            B += n;
            bQ.add(n);
        }
        int size = queue1.length;
        while(true) {
            if(answer > 3 * size) {
                answer = -1;
                break;
            }
            if(A == B) break;
            else if(A > B) {
                A -= aQ.peek();
                B += aQ.peek();
                bQ.add(aQ.poll());
                answer++;
            } else {
                A += bQ.peek();
                B -= bQ.peek();
                aQ.add(bQ.poll());
                answer++;
            }
        }
        
        
        return answer;
    }
}