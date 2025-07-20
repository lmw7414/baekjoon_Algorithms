import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
1. 가까운 기간 순, 가장 큰 가치를 가진 강의 순으로 정렬
 */
public class Main {
    static int N;
    static Course[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Course[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            arr[i] = new Course(d, p);
        }
        Arrays.sort(arr, (a, b) -> {
            if (a.d == b.d) return b.p - a.p;
            return a.d - b.d;
        });

        PriorityQueue<Course> pq = new PriorityQueue<>((a, b) ->{
            if(a.p == b.p) return b.d - a.d;
            return a.p - b.p;
        });
        int answer = 0;
        for (int i = 0; i < N; i++) {
            Course cur = arr[i];
            if (pq.size() < cur.d) {
                pq.add(cur);
                answer += cur.p;
            } else if (pq.peek().d <= cur.d && pq.peek().p < cur.p) {
                answer -= pq.peek().p;
                pq.poll();
                pq.add(cur);
                answer += cur.p;
            }

        }
        System.out.println(answer);

    }


    static class Course {
        int d, p;

        public Course(int d, int p) {
            this.d = d;
            this.p = p;
        }
    }
}