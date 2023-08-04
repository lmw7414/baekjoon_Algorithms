import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int[][] timeTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        timeTable = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            timeTable[i][0] = start;
            timeTable[i][1] = end;
        }
        Arrays.sort(timeTable, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1])
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });
        int cnt = 1;
        int endBorder = timeTable[0][1];
        for (int i = 1; i< timeTable.length; i++) {
            if(endBorder <= timeTable[i][0]) {
                //System.out.println(timeTable[i][0] + " " + timeTable[i][1]);
                endBorder = timeTable[i][1];
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}