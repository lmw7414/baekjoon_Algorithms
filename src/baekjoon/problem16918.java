package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem16918 {

    static int R;
    static int C;
    static int N;
    static int[][] BOARD;
    static char[][] MAP;
    static int[] dx = {-1, 1, 0, 0}; // up, down ,left, right
    static int[] dy = {0, 0, -1, 1};
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        BOARD = new int[R][C];
        MAP = new char[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            for (int j = 0; j < C; j++) {
                MAP[i][j] = input.charAt(j);
                if ('O' == input.charAt(j)) {
                    BOARD[i][j] = 3;
                }
            }
        }

        while (time++ < N) {
            if (time % 2 == 0) { // 폭탄 채우는 시간
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (MAP[i][j] == '.') {
                            MAP[i][j] = 'O';
                            BOARD[i][j] = time + 3;
                        }
                    }
                }
            } else {  // 폭탄 터짐
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (BOARD[i][j] == time) {
                            MAP[i][j] ='.';
                            for (int k = 0; k < 4; k++) {
                                int ni = i + dx[k];
                                int nj = j + dy[k];

                                if (ni >= 0 && ni < R && nj >= 0 && nj < C) {
                                    if ( MAP[ni][nj] == 'O' && BOARD[ni][nj] != time) {
                                        MAP[ni][nj] = '.';
                                        BOARD[ni][nj] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        printResult();
    }

    static void printResult() {
        for (int i = 0; i < R; i++) {
            System.out.println(MAP[i]);
        }
        System.out.println();
    }

}
