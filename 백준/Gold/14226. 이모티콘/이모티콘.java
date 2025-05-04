
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] visit = new boolean[1002][1002];
        Queue<Step> queue = new ArrayDeque<>();
        queue.add(new Step(0, 0, 1));
        visit[0][1] = true;
        while(!queue.isEmpty()) {
            Step cur = queue.poll();
            if(cur.window == N) {
                System.out.println(cur.sec);
                break;
            }


            for(int d = 0;  d < 3; d++) {
                if(d == 0) {
                    if(cur.window == 0) continue;
                    Step n = copy(cur);
                    if(n.clipBoard > N) continue;
                    if(visit[n.clipBoard][n.window]) continue;
                    queue.add(n);
                    visit[n.clipBoard][n.window] = true;
                } else if( d == 1) {
                    if(cur.clipBoard == 0) continue;
                    Step n = paste(cur);
                    if(n.window > N) continue;
                    if(visit[n.clipBoard][n.window]) continue;
                    queue.add(n);
                    visit[n.clipBoard][n.window] = true;
                } else {
                    if(cur.window == 0) continue;
                    Step n = delete(cur);
                    if(visit[n.clipBoard][n.window]) continue;
                    queue.add(n);
                    visit[n.clipBoard][n.window] = true;
                }
            }

        }

    }

    public static Step copy(Step s) {
        return new Step(s.sec + 1, s.window, s.window);
    }
    public static Step paste(Step s) {
        return new Step(s.sec + 1, s.clipBoard, s.window + s.clipBoard);
    }
    public static Step delete(Step s) {
        return new Step(s.sec + 1, s.clipBoard, s.window - 1);
    }


    static class Step {
        int sec; // 시간초
        int clipBoard; // 클립보드 안 이모티콘 개수
        int window; // 화면 내 이모티콘 개수

        public Step(int sec, int clipBoard, int window) {
            this.sec = sec;
            this.clipBoard = clipBoard;
            this.window = window;
        }
    }
}
