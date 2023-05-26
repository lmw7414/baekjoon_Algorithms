
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[][] arr = new int[9][9];
    static List<Zero> zeroList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 0) zeroList.add(new Zero(i, j));
                arr[i][j] = input;
            }
        }
        dfs(0);
        printBoard();
    }

    public static void dfs(int idx) {
        if(idx == zeroList.size()) {
            if(allCheck()) {
                printBoard();
                System.exit(0);
            }
        } else {
            Zero z = zeroList.get(idx);
            for(int i = 1; i<= 9; i++) {
                if(checkRow(z.y, i) && checkCol(z.x, i) && checkBox(z.x, z.y, i)) {
                    arr[z.x][z.y] = i;
                    dfs(idx + 1);
                    arr[z.x][z.y] = 0;
                }
            }
        }


    }

    public static boolean allCheck() {
        for(int i = 0; i < 9; i++) {
            int[] checkCol = new int[10];
            int[] checkRow = new int[10];
            for(int j = 0; j< 9; j++) {
                checkCol[arr[i][j]]++;
                checkRow[arr[j][i]]++;
            }

            for(int k = 1; k<= 9; k++) {
                if(checkCol[k] != 1)
                    return false;
                if(checkRow[k] != 1)
                    return false;
            }
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j< 3; j++) {

                int check[] = new int[10];
                for(int k = i * 3; k < i * 3 + 3; k++) {
                    for(int l = j * 3; l < j * 3 + 3; l++ ) {
                        check[arr[k][l]]++;
                    }
                }
                for(int k = 1; k<= 9; k++) {
                    if(check[k] != 1)
                        return false;
                }

            }
        }

        return true;
    }

    public static boolean checkRow(int col, int val) { //|
        int[] check = new int[10];
        for (int i = 0; i < 9; i++) {
            check[arr[i][col]]++;
        }
        if(check[val] > 0) return false;
        else return true;

    }

    public static boolean checkCol(int row, int val) { //-
        int[] check = new int[10];
        for (int i = 0; i < 9; i++) {
            check[arr[row][i]]++;
        }
        if(check[val] > 0) return false;
        else return true;
    }

    public static boolean checkBox(int row, int col, int val) { //-
        int[] check = new int[10];

        row /= 3;
        col /= 3;
        row *= 3;
        col *= 3;
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                check[arr[i][j]]++;
            }
        }
        if(check[val] > 0) return false;
        else return true;
    }

    public static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Zero {
        int x;
        int y;

        public Zero(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}