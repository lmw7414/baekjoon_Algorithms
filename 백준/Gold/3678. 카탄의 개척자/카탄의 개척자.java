
import java.io.*;

public class Main {
    static int[] resources = new int[6];
    static int[] answer = new int[10001];
    static int[][] arr;
    static int[] dx = {-2, -1, 1, 2, 1, -1};
    static int[] dy = {0, 1, 1, 0, -1, -1};
    static int[] cnt = {1, 0, 1, 1, 1, 1};
    static int[] ndx = {-1, -2, -1,  1, 2, 1};
    static int[] ndy = { 1,  0, -1, -1, 0 ,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        arr = new int[300][300];

        int x = 150;
        int y = 150;
        int nx = x;
        int ny = y;
        answer[1] = 1;
        arr[x][y] = 1;
        resources[1]++;
        int idx = -1;
        for(int i = 2; i <= 10000; i++) {
            idx = (idx + 1) % 6;
            if(cnt[idx] == 0) {
                i--;
                continue;
            }
            for(int j = 0; j < cnt[idx]; j++) {
                //System.out.println(i);
                nx = x + ndx[idx];
                ny = y + ndy[idx];
                int val = getBestResources(nx, ny);
                arr[nx][ny] = val;
                //arr[nx][ny] = (i % 9) + 1;
                answer[i] = val;
                resources[val]++;
                x = nx;
                y = ny;
                if(j < cnt[idx] - 1) i++;
                if(i > 10000) break;
            }
            if(idx != 0 && idx % 5 == 0) {
                for(int j = 0; j < 6; j++) cnt[j]++;
            }
        }
        //printArr();



        for(int i = 0; i < c; i++) {
            System.out.println(answer[Integer.parseInt(br.readLine())]);
        }
    }


    public static int getBestResources(int x, int y) {
        boolean[] flag = new boolean[6];
        for(int d = 0; d < 6; d++) {
            //System.out.println((x + dx[d]) + " " + (y + dy[d]));
            flag[arr[x + dx[d]][y + dy[d]]] = true;
        }
        int min = Integer.MAX_VALUE;
        int result = 1;
        for(int i = 1; i <= 5; i++) {
            if(flag[i]) continue;
            if(resources[i] < min) {
                min = resources[i];
                result = i;
            }
        }
        return result;
    }

    public static void printArr() {
        int N = 200;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] == 0) System.out.print(" ");
                else System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}