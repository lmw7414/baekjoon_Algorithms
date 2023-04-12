class Solution {
    /**
    * sum이 k 이상이 될때까지 end 이동
    * 정답이라면 저장
    * k일 경우 end를 더 움직여 초과할 때까지
    * start 이동
    * 정답이 나오면 저장, 그리고 end 이동
    */
    
    public int[] solution(int[] sequence, int k) {
        int[] answer = null;
        int start = 0;
        int end = 0;
        int sum = 0;
        while(true) {

            if(end < sequence.length && sum <= k) {  // end 이동
                sum += sequence[end++];
                if(sum == k)
                    answer = findOptimal(answer, start, end - 1);
            }
            if(start < sequence.length && sum > k) { // sum이 k 이상일 경우
                sum -= sequence[start++];
                if(sum == k)
                    answer = findOptimal(answer, start, end - 1);
            }
            if(start == sequence.length - 1 && end == sequence.length)
                break;
            if(end == sequence.length && sum <= k)
                break;

        }
        return answer;
    }

    public static int[] findOptimal(int[] answer, int start, int end) {
        if(answer == null) {
            answer = new int[] {start, end};
            return answer;
        }
        if(answer[1] - answer[0] >= end - start) { // 길이 비교
            if(answer[1] - answer[0] == end - start) { // 길이가 같을 때
                if(answer[0] < start) {
                    return answer;
                } else {
                    return new int[] {start, end};
                }
            } else {
                return new int[] {start, end};
            }
        }
        return answer;
    }
}
