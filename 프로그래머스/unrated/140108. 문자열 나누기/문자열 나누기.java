class Solution {
    static int answer = 0;
    public int solution(String s) {
        int idx = 0;
        
        while(idx != s.length())
            idx = cut(s, idx);
        
        return answer;
    }
    
    public static int cut(String s, int startIdx) {
        char alphabet = s.charAt(startIdx);
        int self = 0;
        int others = 0;
        
        for(int i = startIdx; i< s.length(); i++) {
            char c = s.charAt(i);
            if(c == alphabet)
                self++;
            else
                others++;
            
            if(self == others){
                answer++;
                return i + 1;
            }
                
        }
        answer++;
        return s.length();
    }
}