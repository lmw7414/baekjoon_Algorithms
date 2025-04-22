import java.util.*;
import java.io.*;

/**
 * [문제 해결 프로세스]
 * 스택 활용
 */

public class Main {
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();
        for (char c : str.toCharArray()) {
            stack.push(c);
            if(!stack.isEmpty() && stack.peek() == bomb.charAt(bomb.length() - 1)) { // 마지막 문자열이 같다면
                Stack<Character> temp = new Stack<>();
                for(int idx = bomb.length() -1; idx >= 0; idx--) {
                    if(!stack.isEmpty() && stack.peek() == bomb.charAt(idx)) temp.push(stack.pop());
                    else {
                        while(!temp.isEmpty()) stack.push(temp.pop());
                        break;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if(stack.isEmpty()) sb.append("FRULA");
        else {
            for (char c : stack) {
                sb.append(c);
            }
        }
        System.out.print(sb);

    }
}
