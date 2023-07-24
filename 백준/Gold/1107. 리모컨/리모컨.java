import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

    static int cur = 100;
    static int dest;
    static HashSet<Integer> hs = new HashSet<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dest = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        if(n != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                hs.add(Integer.parseInt(st.nextToken()));
        }

        // 100번과 가까울 경우 미리 체크
        answer = Math.min(Math.abs(dest - cur), answer);
        int cnt = 0;

        for (int i = 0; i <= 999_999; i++) {

            cnt = 0;
            String toStr = String.valueOf(i);
            boolean flag = true;
            for (int j = 0; j < toStr.length(); j++) {
                if (hs.contains(toStr.charAt(j) - '0')) {
                    flag = false;
                    break;
                }
            }
            if(!flag) continue;
            cnt += toStr.length();
            cnt += Math.abs(dest - i);
            answer = Math.min(answer, cnt);
        }
        System.out.println(answer);
    }

}