
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int[] maxSeg;
    public static int[] minSeg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int depth = 1;
        while((int)Math.pow(2, depth - 1) <= N) depth++;

        maxSeg = new int[(int)Math.pow(2, depth)];
        Arrays.fill(maxSeg, Integer.MIN_VALUE);
        minSeg = new int[(int)Math.pow(2, depth)];
        Arrays.fill(minSeg, Integer.MAX_VALUE);

        int startIdx = (int)Math.pow(2, depth - 1);
        for(int i = startIdx; i< startIdx + N; i++) {
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());
            maxSeg[i] = input;
            minSeg[i] = input;
        }

        initMax((int)Math.pow(2, depth) - 1);
        initMin((int)Math.pow(2, depth) - 1);


        for(int i = 0; i< M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            System.out.println(calcMin(depth, start, end) + " " + calcMax(depth, start, end));
        }
    }

    public static int calcMin(int depth, int start, int end) {
        int answer = Integer.MAX_VALUE;
        start = (int)Math.pow(2, depth - 1) + start - 1;
        end = (int)Math.pow(2, depth - 1) + end - 1;

        while(start <= end) {
            if(start % 2 == 1) answer = Math.min(answer, minSeg[start++]);
            if(end % 2 == 0) answer = Math.min(answer, minSeg[end--]);

            start /= 2;
            end /= 2;
        }
        return answer;
    }
    public static int calcMax(int depth, int start, int end) {
        int answer = 0;
        start = (int)Math.pow(2, depth - 1) + start - 1;
        end = (int)Math.pow(2, depth - 1) + end - 1;

        while(start <= end) {
            if(start % 2 == 1) answer = Math.max(answer, maxSeg[start++]);
            if(end % 2 == 0) answer = Math.max(answer, maxSeg[end--]);

            start /= 2;
            end /= 2;
        }
        return answer;
    }

    public static void initMax(int endIdx) {
        int rootIdx = endIdx/2;
        for(int i = rootIdx; i> 0; i--) {
            maxSeg[i] = Math.max(maxSeg[i * 2], maxSeg[i * 2 + 1]);
        }
    }
    public static void initMin(int endIdx) {
        int rootIdx = endIdx/2;
        for(int i = rootIdx; i> 0; i--) {
            minSeg[i] = Math.min(minSeg[i * 2], minSeg[i * 2 + 1]);
        }
    }
}


