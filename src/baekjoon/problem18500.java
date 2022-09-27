package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//미네랄 2
public class problem18500 {

    static int R;
    static int C;
    static int N;
    static int nArr[];
    static int board[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][C];

        for(int i = 0; i< R; i++) {
            st = new StringTokenizer(br.readLine());
            String c = st.nextToken();
            for(int j = 0; j<C; j++) {
                if(c.charAt(j) == 'x')
                    board[i][j] = 1;
            }
        }

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            nArr[i] = Integer.parseInt(st.nextToken());
        }


        //printBoard();


    }

    /**
     * 클러스터가 발생했는지 확인하는 메서드
     * 클러스터 발생 시 중력에 의한 위치 변경 메서드
     * 아래로 떨어지면서 다른 미네랄에 걸리면 떨어지는 것을 멈춰야 함
     *
     * 깊이 우선 탐색 메서드 - 스택 활용
    */
    static void printBoard() {
        for(int i = 0; i< R; i++) {
            for(int j = 0; j<C; j++) {
                if(board[i][j] == 0)
                    System.out.printf(". ");
                else
                    System.out.printf("x ");
            }
            System.out.println();
        }
    }
}
