class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for(int i = 1; i<= number; i++) {
            int p = getDivisior(i);
            if(p > limit)
                answer += power;
            else
                answer += p;
        }
        return answer;
    }
    
    public int getDivisior(int num) {
        if(num == 1)
            return 1;
        
        int count = 0;
        float sqrtNum = (float)Math.sqrt(num);
        for(int i = 1; i <= sqrtNum; i++) {
            if(num % i == 0)
                count++;
        }
        if((int)sqrtNum * (int)sqrtNum == num)
            return 2 * count - 1;
        else 
            return 2 * count;
    }
}