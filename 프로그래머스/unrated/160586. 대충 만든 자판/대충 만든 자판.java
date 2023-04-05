import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        HashMap<Character, Integer> hm = new HashMap<>(); 
        
        for(String str : keymap) {
            for(int i = 0; i < str.length(); i++) {
                if(!hm.containsKey(str.charAt(i))) {
                    hm.put(str.charAt(i), i+1);
                } else {
                    if(i + 1 < hm.get(str.charAt(i)))
                       hm.put(str.charAt(i), i + 1);
                }
            }
        }
        int idx = 0;
        for(String str : targets) {
            int click = 0;
            boolean flag = true;
            for(int i = 0; i< str.length(); i++) {
                if(hm.containsKey(str.charAt(i)))
                    click += hm.get(str.charAt(i));
                else {
                    flag = false;
                    break;
                }
            }
            if(flag)
                answer[idx++] = click;
            else
                answer[idx++] = -1;
        }
        return answer;
    }
}