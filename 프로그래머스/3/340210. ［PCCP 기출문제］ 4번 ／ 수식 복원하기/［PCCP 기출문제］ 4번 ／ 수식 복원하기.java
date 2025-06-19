import java.util.*;
class Solution {
    static Set<Integer> bases = new HashSet<>();
    public String[] solution(String[] expressions) {
        //1. 진법 초기화
        for(int i = check(expressions) + 1; i <= 9; i++) {
            //System.out.print(i + " ");
            bases.add(i);
        }
        List<String[]> xList = new ArrayList<>();
        for(String expr : expressions) {
            String[] str = expr.split(" ");
            String A = str[0], op = str[1], B = str[2], C = str[4];
            if(C.equals("X")) {
                xList.add(str);
                continue;
            }
            Set<Integer> temp = copySet();
            for(int base : temp) {
                    int A10 = convert10(A, base);
                    int B10 = convert10(B, base);
                    int C10 = convert10(C, base);
                    int RES = -1;
                    if(op.equals("+")) RES = A10 + B10;
                    else RES = A10 - B10;
                    if(RES != C10) {
                        bases.remove(base);
                    }
                }
        }
        
        for(String[] str : xList) {
            String A = str[0], op = str[1], B = str[2];
            int init = -1;
            boolean flag = true;
            for(int base : bases) {
                int A10 = convert10(A, base);
                int B10 = convert10(B, base);
                int RES = -1;
                if(op.equals("+")) RES = A10 + B10;
                else RES = A10 - B10;
                RES = convertTo(RES, base);
                if(init == -1) init = RES;
                else if(init != -1 && init != RES) {
                    str[4] = "?";
                    flag = false;
                    break;
                }
                //System.out.println(init);
            }
            if(flag) str[4] = String.valueOf(init);
            
        }
        
        
        
        String[] answer = new String[xList.size()];
        for(int i = 0; i < xList.size(); i++) {
            answer[i] = String.join(" ", xList.get(i));
        }
        return answer;
    }
    
    
    public Set<Integer> copySet() {
        Set<Integer> temp = new HashSet<>();
        for(int base : bases) temp.add(base);
        return temp;
    }
    
    // 10진법 숫자로 변경
    public int convert10(String num, int n) {
        return Integer.parseInt(num, n);
    }
    
    // 8이면 -> 9진법 이상 부터 가능
    // 제공되어 있는 숫자를 통해 가능한 진법 추리기
    public int check(String[] expressions) {
        int max = 0;
        for(String expression : expressions) {
            for(char c : expression.toCharArray()) {
                if('0' <= c && c <= '9') max = Math.max(max, c - '0');
            }
        }
        return max;
    }
    
    // 10진수 -> n진수
    public static int convertTo(int num, int n) {
        Stack<Integer>stack = new Stack<>();
        
        while(num / n != 0) {
            stack.push(num % n);
            num /= n;
        }
        String str = "" + num;
        while(!stack.isEmpty()) str += stack.pop();
        return Integer.parseInt(str);
    }
}