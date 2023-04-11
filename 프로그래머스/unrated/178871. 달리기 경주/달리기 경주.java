import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {     
        HashMap<String, Integer> hm = new HashMap<>();
        int i = 1;
        for(String str : players) {
            hm.put(str, i++);
        }
        
        for(String str : callings) {
            int ranking = hm.get(str);  // ex) kai
            String prior = players[ranking - 2]; // ex) poe
            hm.replace(str, ranking - 1);
            hm.replace(prior, ranking);
            swap(players, ranking - 1 , ranking - 2);
        }
        return players;
    }
    
    public static void swap(String[] players, int from, int to) {
        String temp = players[to];
        players[to] = players[from];
        players[from] = temp;
    }
}