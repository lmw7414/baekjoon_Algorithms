import java.util.*;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        int[] recipe = {1,3,2,1};
        int recipeIdx = 0;
        int previousBread = 0;
        
        Stack<Integer> stack = new Stack();
        for(int i : ingredient) {
            stack.push(i);
            if(!stack.isEmpty() && stack.size() >= 4 ) {
                if(stack.peek() == 1) {
                    Stack<Integer> temp = new Stack();
                    recipeIdx = 0;
                    while(recipe[recipeIdx] == stack.peek()) {
                        //System.out.println(stack.peek());
                        temp.push(stack.pop());
                        recipeIdx++;
                        if(recipeIdx > 3)
                            break;
                    }
                    if(temp.size() == 4)
                        answer++;
                    else
                        while(!temp.isEmpty())
                            stack.push(temp.pop()); 
                }
            }
        }
        return answer;
    }
    
    
}
