import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        Set<Integer> divisorsA = findDivisor(arrayA);
        Set<Integer> divisorsB = findDivisor(arrayB);
        
        for(int aKey : divisorsA) {
            boolean flag = true;
            for(int num : arrayB) {
                if(num % aKey == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                answer = Math.max(answer, aKey);
        }
        for(int bKey : divisorsB) {
            boolean flag = true;
            for(int num : arrayA) {
                if(num % bKey == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                answer = Math.max(answer, bKey);
        }
        return answer;
    }
    
    public Set<Integer> findDivisor(int[] array) {
        int minValue = Arrays.stream(array).min().getAsInt();
        List<Integer> divisorList = new ArrayList();
        Set<Integer> divisors = new TreeSet<>();
        
        for(int i = 2; i<= minValue; i++) {
            if(minValue % i == 0)
                divisorList.add(i);
        }
        for(int divisor : divisorList) {
            boolean flag = true;
            for(int num : array) {
                if(num % divisor != 0) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                divisors.add(divisor);
        }
        return divisors;
    }
}