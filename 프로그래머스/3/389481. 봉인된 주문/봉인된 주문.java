import java.util.*;
/**
각 주문은 알파벳 소문자 11글자 이하
*/
class Solution {
    public String solution(long n, String[] bans) {
        Arrays.sort(bans, (a1, b1) -> {
            if(a1.length() == b1.length()) return a1.compareTo(b1);
            return a1.length() - b1.length();
        });
        
        for(String b : bans) {
            String data = getString(n);
            if(b.length() < data.length() || (b.length() == data.length() && b.compareTo(data) <= 0)) n++;
            else break;
        }
        
        return getString(n);
    }
    
    
    // 숫자가 주어졌을 때 해당 문자 찾기
    public String getString(long n) {
        Stack<Character> stack = new Stack<>();
        
        while(n > 0) {
            long modulo = n % 26;
            n /= 26;
            if(modulo == 0) {
                stack.push('z');
                n--;
            } else stack.push((char)('a' + modulo - 1));
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pop());
        return sb.toString();

    }
    
} 