
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int maxCnt = 0;
    static int sId = Integer.MAX_VALUE;
    static String S, T;
    static Node Root = new Node(null);
    static Map<String, Integer> inpMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            inpMap.put(input, n);
            calc(Root, input, 0, 0);
        }
        System.out.println(S);
        System.out.println(T);
    }

    public static int getId(String str) {
        if (!inpMap.containsKey(str)) return Integer.MAX_VALUE;
        return inpMap.get(str);
    }

    public static void calc(Node cur, String input, int idx, int sameCnt) {
        if (idx == input.length()) {
            if (sameCnt > maxCnt) {
                S = find(input, sameCnt);
                sId = getId(S);
                T = input;
                maxCnt = sameCnt;
            } else if (sameCnt == maxCnt) {
                String temp = find(input, sameCnt);
                int id = getId(temp);
                if (sId > id) {
                    S = temp;
                    T = input;
                }
            }
            return;
        }
        char curChar = input.charAt(idx);
        if (cur.map.containsKey(curChar)) { // 이미 존재하는 prefix
            Node next = cur.map.get(curChar);
            if (input.length() - 1 == idx) {
                next.isEnd = true;
            }
            calc(next, input, idx + 1, sameCnt + 1);
        } else {
            Node newNode = new Node(curChar);
            cur.map.put(curChar, newNode);
            if (input.length() - 1 == idx) {
                newNode.isEnd = true;
            }
            calc(newNode, input, idx + 1, sameCnt);
        }
    }

    public static String find(String input, int sameCnt) {
        StringBuilder sb = new StringBuilder();
        Node cur = Root;
        for (int i = 0; i < sameCnt; i++) {
            sb.append(input.charAt(i));
            cur = cur.map.get(input.charAt(i));
        }
        if (cur.isEnd && input.length() > sameCnt) return sb.toString();

        boolean flag = false;
        if (input.length() == sameCnt) flag = true;

        do {
            for (Map.Entry<Character, Node> ele : cur.map.entrySet()) {
                if (!flag && ele.getValue().cur == input.charAt(sameCnt)) {
                    flag = true;
                    continue;
                }
                cur = ele.getValue();
                break;
            }
            sb.append(cur.cur);
        } while (!cur.isEnd);
        return sb.toString();
    }

    static class Node {
        Character cur;
        Map<Character, Node> map;
        boolean isEnd;

        public Node(Character cur) {
            this.cur = cur;
            map = new HashMap<>();
            isEnd = false;
        }
    }

}