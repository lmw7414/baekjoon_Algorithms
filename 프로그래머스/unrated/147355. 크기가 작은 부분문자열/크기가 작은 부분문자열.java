class Solution {
    
    public int solution(String t, String p) {
        int answer = 0;
        long standard = Long.parseLong(p);
        int gap = p.length();
        
        for(int i = 0; i <= t.length() - gap; i++) {
            Long num = Long.parseLong(t.substring(i, i + gap));
            // System.out.println(num);
            if(standard  >= num)
                answer++;
        }
        
        return answer;
    }
}