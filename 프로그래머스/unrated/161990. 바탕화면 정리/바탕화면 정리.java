class Solution {
    public int[] solution(String[] wallpaper) {
        
        int col = wallpaper.length;
        int row = wallpaper[0].length();
        
        int[] answer = new int[4];
        // 위 -> 아래 lux
        boolean flag1 = false;
        for(int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                if(wallpaper[i].charAt(j) == '#') {
                    answer[0] = i;
                    flag1 = true;
                    break;
                }
            }
            if(flag1)
                break;
        }
        
        // 아래 -> 위 rdx
        boolean flag2 = false;
        for(int i = col - 1; i >= 0; i--) {
            for(int j = row - 1; j >= 0; j--) {
                if(wallpaper[i].charAt(j) == '#') {
                    answer[2] = i+1;
                    flag2 = true;
                    break;
                }
            }
            if(flag2)
                break;
            
        }
        
        // 왼 -> 오   luy
        boolean flag3 = false;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(wallpaper[j].charAt(i) == '#') {
                    answer[1] = i;
                    flag3 = true;
                    break;
                }
            }
            if(flag3)
                break;
        }
        
        // 오 -> 왼   rdy
        boolean flag4 = false;
        for(int i = row - 1; i >= 0; i--) {
            for(int j = col - 1; j >= 0; j--) {
                if(wallpaper[j].charAt(i) == '#') {
                    answer[3] = i+1;
                    flag4 = true;
                    break;
                }
            }
            if(flag4)
                break;
        }
        return answer;
    }
}