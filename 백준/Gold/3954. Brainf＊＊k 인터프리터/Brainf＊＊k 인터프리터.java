import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * [문제 설명]
 * - : 포인터가 가리키는 수 1 감소
 * + : 포인터가 가리키는 수 1 증가
 * < : 포인터를 <- 으로 한칸 움직임
 * > : 포인터를 -> 으로 한칸 움직임
 * [ : 만약 포인터가 0이면 ]로 이동
 * ] : 만약 포인터가 0이 아니라면 ]와 짝을 이루는 [의 다음 명령으로 점프
 * . : 포인터가 가리키는 수를 출력
 * , : 문자 하나를 읽고 포인터가 가리키는 곳에 저장. EOF에는 255
 * <p>
 * 1. 첫번째 명령부터 수행
 * 2. 수행할 명령이 없으면 종료
 * 3. 배열의 크기는 문제에서 주어진 값을 사용. underflow overflow인 경우 일반적인 방법
 * 4. 프로그램을 수행하기 전에 데이터 배열의 값은 0. 포인터가 가리키는 칸 0
 */
public class Main {
    static int TC, M, C, I;
    static int[] memory;
    static char[] command, code;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            I = Integer.parseInt(st.nextToken());
            memory = new int[M];
            command = br.readLine().toCharArray();
            code = br.readLine().toCharArray();
            Stack<Integer> stack = new Stack<>();
            int[] startBrackets = new int[C]; // idx : '[' 의 위치 / ']' 위치 저장
            int[] endBrackets = new int[C]; // idx : ']' 의 위치 저장 / '[' 위치 저장
            for (int i = 0; i < C; i++) {
                if (command[i] == '[') {
                    stack.push(i);
                } else if (command[i] == ']') {
                    int start = stack.pop();
                    startBrackets[start] = i;
                    endBrackets[i] = start;
                }
            }
            int commandP = 0, codeP = 0;
            int cnt = 0;
            int endPoint = 0;
            for (int c = 0; c < C; c++) {
                switch (command[c]) {
                    case '-':
                        memory[commandP] = (memory[commandP] + 255) % 256;
                        break;
                    case '+':
                        memory[commandP] = (memory[commandP] + 1) % 256;
                        break;
                    case '<':
                        commandP = (commandP + M - 1) % M;
                        break;
                    case '>':
                        commandP = (commandP + 1) % M;
                        break;
                    case '[':
                        if (memory[commandP] == 0) {
                            c = startBrackets[c];
                        }
                        break;
                    case ']':
                        if (memory[commandP] != 0) {
                            if (cnt > 50000000) {
                                endPoint = Math.max(endPoint, c);  //가장 바깥쪽 루프
                            }
                            c = endBrackets[c];
                        }
                        break;
                    case '.':
                        break;
                    case ',':
                        memory[commandP] = (codeP >= I) ? 255 : code[codeP++];
                        break;
                }
                cnt++;
                if (cnt > 100000000) break;
            }
            if (cnt > 50000000) {
                sb.append("Loops ")
                        .append(endBrackets[endPoint]).append(" ")
                        .append(endPoint).append(" ")
                        .append("\n");
            } else sb.append("Terminates").append("\n");
        }
        System.out.print(sb.toString());
    }


}