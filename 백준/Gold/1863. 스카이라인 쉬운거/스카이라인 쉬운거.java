import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();  // y값 저장
        StringTokenizer st;
        for(int i = 0; i <= N; i++) {
            int x = 0;
            int y = 0;
            if( i != N) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
            }
            if(stack.isEmpty()) stack.push(y);
            else {
                if(stack.peek() <= y) stack.push(y);
                else {
                    while(!stack.isEmpty() && stack.peek() > y) {
                        stack.pop();
                        //System.out.println("현재 건물 " + x + " " + y);
                        answer++;
                    }
                    if(stack.isEmpty() || (!stack.isEmpty() && stack.peek() < y)) stack.push(y);
                }
            }
        }
        System.out.println(answer);
    }
}