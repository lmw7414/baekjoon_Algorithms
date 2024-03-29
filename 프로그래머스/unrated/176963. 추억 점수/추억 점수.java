import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        HashMap<String, Integer> hm = new HashMap<>();
        for(int i = 0; i < name.length; i++){
            String key = name[i];
            int data = yearning[i];
            
            hm.put(key, data);
        }
        for(int i = 0; i < photo.length; i++) {
            for(int j = 0; j< photo[i].length; j++) {
                if(hm.containsKey(photo[i][j]))
                    answer[i] += hm.get(photo[i][j]);
            }
        }
        
        return answer;
    }
}