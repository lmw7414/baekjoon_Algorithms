import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

// answer 1 : 어떤 문자를 정확히 K개 포함하는 가장 짧은 문자열의 길이
// answer 2 : 어떤 문자를 정확히 K개 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이
public class Main {

    static List<Integer>[] arr; // 인덱스 번호 저장
    static int answer1, answer2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            for (int i = 0; i < str.length(); i++) {
                int idx = str.charAt(i) - 'a';

                if (arr[idx].size() + 1 < K) {
                    // 문자열 그냥 추가
                    arr[idx].add(i);
                } else {
                    if (arr[idx].size() + 1 >= K) {
                        arr[idx].add(i);
                        if (arr[idx].size() > K) arr[idx].remove(0);
                    }
                    answer1 = Math.min(answer1, arr[idx].get(arr[idx].size() - 1) - arr[idx].get(0) + 1);
                    answer2 = Math.max(answer2, arr[idx].get(arr[idx].size() - 1) - arr[idx].get(0) + 1);
                    // 1번 인덱스 제거 그리고 뒤에 추가 후 answer1,2 값 변경
                }
            }

            if (answer1 == Integer.MAX_VALUE || answer2 == Integer.MIN_VALUE) sb.append(-1).append("\n");
            else sb.append(answer1).append(" ").append(answer2).append("\n");
        }
        System.out.println(sb);
    }

    public static void init() {
        arr = new List[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = new LinkedList<Integer>();
        }
        answer1 = Integer.MAX_VALUE;
        answer2 = Integer.MIN_VALUE;
    }

}