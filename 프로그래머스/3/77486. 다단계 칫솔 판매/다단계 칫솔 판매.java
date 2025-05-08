import java.util.*;
class Solution {
    static HashMap<String, List<String>>trees = new HashMap<>();
    static HashMap<String, String>parents = new HashMap<>();
    static HashMap<String, Integer> answer = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        parents.put("-", "-");
        trees.put("-", new ArrayList<>());
        answer.put("-", 0);
        for(int i = 0; i < enroll.length; i++) {
            String child = enroll[i];
            String parent = referral[i];
            if(!trees.containsKey(child)) {
                trees.put(child, new ArrayList<>());
            }
            trees.get(parent).add(child);
            parents.put(child, parent);
            answer.put(child, 0);
        }
        for(int i = 0; i < seller.length; i++) {
            calc(seller[i], amount[i] * 100);
        }
        
        int[] result = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) result[i] = answer.get(enroll[i]);
        return result;
    }
    
    public static void calc(String user, int money) {
        int percent10 = (int)Math.floor(money / 10);
        int mine = money - percent10;
        answer.replace(user, answer.get(user) + mine); // 나의 수익금 증가
        if(percent10 == 0) return;
        calc(parents.get(user), percent10); // 나머지 수익금 부모로 토스
    }
}