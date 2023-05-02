class Solution {
    public int solution(String[] babbling) {
        final String[] speak = {"aya", "ye", "woo", "ma"};
        final String[] cannot = {"ayaaya", "yeye", "woowoo", "mama"};
        int answer = 0;
        boolean flag = true;
        for(String str : babbling) {
            flag = true;
            for(int i = 0; i < 4; i++) {
                if(str.contains(cannot[i])) {
                    flag = false;
                    break;
                }
                str = str.replaceAll(speak[i], "x");
            }
            if(!flag)
                continue;
            str = str.replaceAll("x", "");
            if(str.equals(""))
                answer++;
        }
        return answer;
    }
}