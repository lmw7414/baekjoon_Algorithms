import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Main {
    static int N;
    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) pq.add(Integer.parseInt(br.readLine()));

        while(!pq.isEmpty()) {
            int f = pq.poll();
            if(pq.isEmpty()) {
                System.out.println(answer);
                return;
            }
            int s = pq.poll();
            answer += f + s;
            pq.add(f+s);
        }
    }

}
