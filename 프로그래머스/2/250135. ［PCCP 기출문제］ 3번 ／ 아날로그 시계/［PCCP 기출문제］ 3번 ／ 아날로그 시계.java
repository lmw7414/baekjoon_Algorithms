class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startTime = getSec(h1, m1, s1);
        int endTime = getSec(h2, m2, s2);
        int rest = endTime * 59/3600 + endTime * 719/43200 - startTime * 59/3600 - startTime * 719/43200; // 끝나는 시간 동안 만나는 횟수 - 시작시간 동안 만나는 횟수

        if(startTime >= 43200) rest += 1;
        if(endTime >= 43200) rest -= 1;
        if (startTime * 59 % 3600 == 0 || startTime * 719 % 43200 == 0) rest += 1;
        return rest;
    }
    
    
    public int getSec(int h, int m, int s) {
        return (h * 60 * 60) + (m * 60) + s;
    }
}