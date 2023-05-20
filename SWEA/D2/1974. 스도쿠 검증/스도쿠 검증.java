import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= tc; i++) {
            arr = new int[9][9];
            for (int j = 0; j < 9; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 9; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            if (check())
                printAnswer(i, 1);
            else printAnswer(i, 0);

        }
    }

    private static boolean check() {
        if (!checkRow()) return false;
        if (!checkCol()) return false;
        if (!checkSquare()) return false;
        return true;

    }

    private static boolean checkSquare() {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                int[] check = new int[10];
                for(int k = i; k < i + 3; k++) {
                    for(int l = j; l < j + 3; l++) {
                        check[arr[k][l]]++;
                    }
                }
                for(int k = 1; k < 10; k++){
                    if(check[k] != 1)
                        return false;
                }
            }
        }
        return true;
    }

    private static boolean checkCol() {
        for(int i = 0; i < 9; i++) {
            int[] check = new int[10];
            for(int j = 0; j< 9; j++) {
                check[arr[i][j]]++;
            }
            for(int k = 1; k < 10; k++){
                if(check[k] != 1)
                    return false;
            }
        }
        return true;
    }

    private static boolean checkRow() {
        for(int i = 0; i < 9; i++) {
            int[] check = new int[10];
            for(int j = 0; j< 9; j++) {
                check[arr[j][i]]++;
            }
            for(int k = 1; k < 10; k++){
                if(check[k] != 1)
                    return false;
            }
        }
        return true;
    }

    private static void printAnswer(int i, int result) {
        System.out.println("#" + i + " " + result);
    }

}