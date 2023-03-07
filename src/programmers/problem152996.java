package programmers;

import java.util.*;

public class problem152996 {

    public static void main(String[] args) {
        System.out.println(solution(new int[] {100,180,360,100,270}));
    }
    public static long solution(int[] weights) {

        Arrays.sort(weights);
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        Set<Integer> weightSet = new TreeSet<>();  // 순서대로 정렬하기 위해 TreeSet을 사용
        for(int i = 0; i< weights.length; i++) {
            if(!map.containsKey(weights[i])) {
                HashSet<Integer> hs = new HashSet<>();
                hs.add(i);
                map.put(weights[i], hs);
            } else {
                map.get(weights[i]).add(i);
            }
            weightSet.add(weights[i]);
        }

        long answer = 0;
        for(int key : weightSet) {
            HashSet<Integer> as = new HashSet<>();

            //a == b 일때
            long dup = 0;
            if(map.containsKey(key)){
                if(map.get(key).size() > 1)
                    dup = map.get(key).size(); // 같은 무게가 중복이 될 경우 해당 무게의 조합 개수가 필요하고, 다른 무게와 평행을 이룰 경우 별도로 생각할 필요가 있어 as Set에 넣지 않았다.
            }
            //a == 3/2b 일때
            double dKey = (double)key * 3.0 / 2.0;
            if(isInteger(dKey) && map.containsKey(key*3/2)) {
                for(int data : map.get(key*3/2))
                    as.add(data);
            }

            //a == 2b 일때
            if(map.containsKey(key*2)){
                for(int data : map.get(key*2))
                    as.add(data);
            }
            //a == 4/3b 일때
            dKey = (double)key * 4.0 / 3.0;

            if(isInteger(dKey)  && map.containsKey(key*4/3)) {
                for(int data : map.get(key*4/3))
                    as.add(data);
            }
            long asSize = as.size();
            if(dup != 0)
                answer += dup * (asSize) + (dup * (dup-1) / 2);
            else
                answer += asSize;

            map.remove(key);
        }

        return answer;
    }

    public static boolean isInteger(double num) {
        return num % 1 == 0.0;
    }
}
