package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem17780 {

    static int N;
    static int K;
    static int board[][];
    static Queue<Integer> queueBoard[][];
    static ArrayList<Piece> pieces;
    static int dx[] = {-1, 0, 0, -1, 1}; // →, ←, ↑, ↓
    static int dy[] = {-1, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N + 1][N + 1];
        queueBoard = new Queue[N + 1][N + 1];
        pieces = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                queueBoard[i][j] = new LinkedList<>();
            }
        }

        int x;
        int y;
        int direction;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            direction = Integer.parseInt(st.nextToken());

            queueBoard[x][y].add(i);
            pieces.add(new Piece(x, y, direction));
        }

        System.out.println(calculate());

    }

    static int calculate() {
        int count = 1;
        while (count <= 1000) {

            for (int i = 0; i < pieces.size(); i++) {
                int cx = pieces.get(i).x;
                int cy = pieces.get(i).y;

                if(queueBoard[cx][cy].peek() != i)
                    continue;

                switch (pieces.get(i).direction) {
                    case 1:
                        cx += dx[1];
                        cy += dy[1];
                        break;
                    case 2:
                        cx += dx[2];
                        cy += dy[2];
                        break;
                    case 3:
                        cx += dx[3];
                        cy += dy[3];
                        break;
                    case 4:
                        cx += dx[4];
                        cy += dy[4];
                        break;
                }

                // 보드 밖으로 빠져나간 경우
                if (cx < 1 || cx > N || cy < 1 || cy > N)
                    blueZone(i);

                else {
                    switch (board[cx][cy]) {
                        case 0:  // 흰색
                            moveQueueOverNumber(cx, cy, pieces.get(i).x, pieces.get(i).y, i);
                            break;
                        case 1:  // 빨간색 - 말 순서 바꾸기
                            moveQueueOverNumber(cx, cy, pieces.get(i).x, pieces.get(i).y, i);
                            changeQueueOrder(cx, cy, i);
                            break;
                        case 2:  // 파란색 - 이동 방향 반대로하고 한칸이동
                            blueZone(i);
                            break;
                    }
                }
                if(queueBoard[pieces.get(i).x][pieces.get(i).y].size() >= 4) // 종료 조건(모두 다  같은 위치 일 경우)
                    return count;

            }
            count++;
        }
        return -1;
    }

    static void blueZone(int num) {

        if (pieces.get(num).direction % 2 == 0)
            pieces.get(num).direction--;
        else
            pieces.get(num).direction++;

        int cx, cy;
        cx = pieces.get(num).x + dx[pieces.get(num).direction];
        cy = pieces.get(num).y + dy[pieces.get(num).direction];

        if(cx < 1 || cx > N || cy < 1 || cy > N){
            return;
        }

        if(board[cx][cy] != 2) {  //파랑이 아닐경우
            moveQueueOverNumber(cx, cy, pieces.get(num).x, pieces.get(num).y, num);
            if(board[cx][cy] == 1)
                changeQueueOrder(cx, cy, num);
        }

    }


    // 큐의 순서 바꾸는 메서드
    static void changeQueueOrder(int cx, int cy, int num) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> temp = new LinkedList<>();
        boolean con = false;
        int curNum;
        while (!queueBoard[cx][cy].isEmpty()) {
            curNum = queueBoard[cx][cy].poll();
            if(curNum == num)
                con = true;
            if(con)
                stack.push(curNum);
            else
                temp.add(curNum);
        }
        while(!temp.isEmpty())
            queueBoard[cx][cy].add(temp.poll());
        while (!stack.isEmpty())
            queueBoard[cx][cy].add(stack.pop());

    }

    static void moveQueueOverNumber(int cx, int cy, int x, int y, int num) {  // (이동할 x, 이동할 y, 이전 x, 이전 y, 현재 이동하는 말)
        Queue<Integer> temp = new LinkedList<>();
        boolean con = false;
        int curNum;
        while (!queueBoard[x][y].isEmpty()) {
            curNum = queueBoard[x][y].poll();

            if (curNum == num)
                con = true;

            if (con) {
                queueBoard[cx][cy].add(curNum);
                pieces.get(curNum).x=cx;
                pieces.get(curNum).y=cy;
            }
            else {
                temp.add(curNum);
            }
        }
        while (!temp.isEmpty())
            queueBoard[x][y].add(temp.poll());
    }

    static class Piece {
        int x;
        int y;
        int direction;

        Piece(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
