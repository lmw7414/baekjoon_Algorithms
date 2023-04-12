import java.util.*;
class Solution {
    public String solution(String s, String skip, int index) {
        char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] chars = s.toCharArray();
        char[] skips = skip.toCharArray();
        String answer = "";
        for(int i = 0; i < skips.length; i++) {
            abc[skips[i] -'a'] = '0';
        }
        for(int i = 0; i < chars.length; i++) {
            int cc = index;
            int count = 0;
            int current = chars[i] - 'a';
            for(int j = 1; j <= cc; j++) {
                int idx = (current + j) % 26;
                if(abc[idx] == '0') {
                    cc++;
                    count++;
                }
            }
            answer += abc[(current + index + count) % 26];
        }
        return answer;
    }
}