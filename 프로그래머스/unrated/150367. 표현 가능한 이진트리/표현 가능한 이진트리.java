import java.util.*;
class Solution {
    public int[] solution(long[] numbers) {
        List<Integer> answer = new ArrayList<>();

        for(long number : numbers) {
            String pBNum = toPBT(number);
            int depth = findDepth(pBNum.length());
            boolean result = dfs(pBNum, pBNum.length()/2,  depth);
            answer.add(result ? 1 : 0);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static boolean dfs(String bNum, int rootIdx, int depth) {
        int lIdx = rootIdx - (int)Math.pow(2, depth - 1);
        int rIdx = rootIdx + (int)Math.pow(2, depth - 1);
        if(depth == 0)
            return true;
        if(bNum.charAt(rootIdx) == '0') {
            if(bNum.charAt(lIdx) == '1' || bNum.charAt(rIdx) == '1')
                return false;
        }

        boolean left;
        boolean right;
        // 왼쪽
        left = dfs(bNum, lIdx, depth - 1);
        // 오른쪽
        right = dfs(bNum, rIdx, depth - 1);

        return left && right;
    }

    public static String toPBT(long num) {
        String bNum = Long.toBinaryString(num);
        int depth = findDepth(bNum.length()) + 1;
        long n = (long)Math.pow(2, depth) - 1;
        String zero = "0".repeat((int)(n - bNum.length()));
        return zero + bNum;
    }


    public static int findDepth(int length) {
        int i = 1;
        while(true) {
            if((long)Math.pow(2, i) > length)
                return i - 1;
            i += 1;
        }
    }
}