import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        Queue<String> card1 = new LinkedList<>();
        Queue<String> card2 = new LinkedList<>();
        Queue<String> goals = new LinkedList<>();
        
        for (String str : cards1)
            card1.add(str);
        for(String str : cards2)
            card2.add(str);
        for(String str : goal)
            goals.add(str);
        
        boolean flag = true;
        
        while(!goals.isEmpty()) {
            String current = goals.poll();
            
            if(!card1.isEmpty() && card1.peek().equals(current)) {
                card1.poll();
            } else if( !card2.isEmpty() && card2.peek().equals(current)) {
                card2.poll();
            } else {
                flag = false;
                break;
            }
            
        }
        
        if(flag)
            return "Yes";
        else
            return "No";
    }
}