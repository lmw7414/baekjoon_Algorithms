import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        PriorityQueue<Fruit> pq = new PriorityQueue<>((o1, o2) -> o2.quantity - o1.quantity);
        HashMap<Integer, Integer> hm = new HashMap<>();
        int answer = 0;
        
        for(int i : tangerine) {
            if(!hm.containsKey(i)) {
                hm.put(i, 1);
            } else {
                hm.replace(i, hm.get(i)+1);
            }
        }
        
        Set<Integer> keySet = hm.keySet();
        for (Integer key : keySet) {
            pq.add(new Fruit(key, hm.get(key)));
        }
        
        int num = 0;
        while(num < k) {
            if(pq.isEmpty())
                break;
            Fruit fruit = pq.poll();
            num += fruit.quantity;
            answer++;
        }
        
        return answer;
    }
}

class Fruit {
    int size;
    int quantity;
    
    public Fruit(int size, int quantity) {
        this.size = size;
        this.quantity = quantity;
    }
}