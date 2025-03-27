
import java.util.*;
import java.io.*;

/**
 * [주의]
 * 학생들의 키는 모두 다름
 * [문제 해결 프로세스]
 1. 키 내림 차순 정렬
 2. 내가 들어갈 수 있는 팀 중에서 최대 인원인 팀에 들어가는 것이 좋음
 */
public class Main {
    static int N;
    static Oj[] arr;
    static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Oj[N];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            arr[i] = new Oj(height, before);
        }
        Arrays.sort(arr, (a, b) -> b.height - a.height);
        TreeSet<Integer> ts = new TreeSet<>();
        int[] teams = new int[N + 1]; // 최대 팀의 수 -> N + 1
        for(Oj o : arr) {
            int k = o.before;
            Integer team = ts.lower(k);
            if(team == null) {
                ts.add(1);
                teams[1]++;
            } else {
                ts.add(team + 1);
                teams[team + 1]++;
                teams[team]--;
                if(teams[team] == 0) ts.remove(team);
            }
        }

        for(int i = 1; i<= N; i++) {
            //System.out.println(teams[i]);
            if(teams[i] != 0) answer+= teams[i];
        }
        System.out.println(answer);
    }

    static class Oj {
        int height, before;
        public Oj(int height, int before) {
            this.height = height;
            this.before = before;
        }
    }


}