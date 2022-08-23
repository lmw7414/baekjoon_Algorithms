package programmers;

import java.util.Stack;
//크레인 인형뽑기 게임
public class problem64061 {
    static int [][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
    static int [] moves = {1,5,3,5,1,2,1,4};

    public static void main(String[] args) {
        System.out.println(solution());
    }

    public static int solution() {
        int answer = 0;
        int data1 = 0;
        int data2 = 0;
        int j = 0;
        Stack<Integer> stack = new Stack();
        for(int i = 0; i< moves.length; i++) {

            while(true) {
                if(j == board.length)
                    break;
                if(board[j][moves[i]-1] == 0)
                    j++;
                else {
                    stack.push(board[j][moves[i]-1]);
                    board[j][moves[i]-1] = 0;
                    break;
                }
            }
            while(!stack.isEmpty()) {
                if(stack.size() >1) {
                    data1 = stack.pop();
                    data2 = stack.pop();

                    if(data1 == data2) {
                        answer++;
                    }
                    else {
                        stack.push(data2);
                        stack.push(data1);
                        break;
                    }
                }
                else
                    break;
            }
            j = 0;
        }
        return answer*2;
    }
}
