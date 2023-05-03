import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int len = topping.length;
        int[] A = new int[len];
        int[] B = new int[len];
        Set<Integer> aSet = new HashSet<>();
        // 사람 A
        int kindA = 0;
        for(int i = 0; i < len; i++) {
            if(!aSet.contains(topping[i])) {
                aSet.add(topping[i]);
                kindA++;
            }
            A[i] = kindA;
        }
        Set<Integer> bSet = new HashSet<>();
        //사람 B
        int kindB = 0;
        for(int i = len - 1; i >= 0; i--) {
            if(!bSet.contains(topping[i])) {
                bSet.add(topping[i]);
                kindB++;
            }
            B[i] = kindB;
        }
        
        for(int i = 0; i< len - 1; i++) {
            if(A[i] == B[i+1])
                answer++;
        }
        return answer;
    }
}