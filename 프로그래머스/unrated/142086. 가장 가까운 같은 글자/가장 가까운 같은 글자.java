import java.util.*;
class Solution {
    public int[] solution(String s) {
        List<Integer> answer = new ArrayList<>();;
        HashMap<Character, Integer> hm = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(!hm.containsKey(c)) {
                hm.put(c, i);
                answer.add(-1);
            } else {
                answer.add(i - hm.get(c));
                hm.replace(c, i);
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}