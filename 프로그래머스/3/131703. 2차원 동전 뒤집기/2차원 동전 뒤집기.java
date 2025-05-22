class Solution {
    static int X, Y;
    public int solution(int[][] beginning, int[][] target) {
        X = beginning.length;
        Y = beginning[0].length;
        int answer = Integer.MAX_VALUE;
        
        // X에 대해서 조합
        for(int i = 0; i < (1 << X); i++) {
            int result = 0;
            int[][] copy = copyArr(beginning);
            
            for(int x = 0;  x < X; x++) {
                if((i & (1 << x)) != 0) {
                    reverseRow(copy, x);
                    result++;
                }
            }
            
            for(int y = 0; y < Y; y++) {
                if(copy[0][y] != target[0][y]) {
                    reverseCol(copy, y);
                    result++;
                }
            }
            if(isSame(target, copy)) {
                answer = Math.min(answer, result);
            }
        }
        if(answer == Integer.MAX_VALUE) return -1;
        return answer;
    }
    
    public static void reverseRow(int[][] arr, int x) {
        for(int y = 0; y < Y; y++) {
            arr[x][y] = arr[x][y] == 1 ? 0 : 1;
        }
    }
    
    public static void reverseCol(int[][] arr, int y) {
        for(int x = 0; x < X; x++) {
            arr[x][y] = arr[x][y] == 1 ? 0 : 1;
        }
    }
    
    public static int[][] copyArr(int[][] origin) {
        int[][] copy = new int[X][Y];
        
        for(int x = 0; x < X; x++) {
            for(int y = 0; y < Y; y++) {
                copy[x][y] = origin[x][y];
            }
        }
        return copy;
    }
    
    public static boolean isSame(int[][] target, int[][] copy) {
        for(int x = 0; x < X; x++) {
            for(int y = 0; y < Y; y++) {
                if(target[x][y] != copy[x][y]) return false;
            }
        }
        return true;
    }
}