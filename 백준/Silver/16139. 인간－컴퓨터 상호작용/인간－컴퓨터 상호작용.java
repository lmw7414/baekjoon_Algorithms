
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int[][] prefixSum = new int[S.length() + 1][26];

        for (int i = 0; i < S.length(); i++) {
            int idx = S.charAt(i) - 'a';
            prefixSum[0][idx]++;
        }
        for (int i = 1; i < S.length(); i++) {
            for (int j = 0; j < 26; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j];
            }
            int idx = S.charAt(i - 1) - 'a';
            prefixSum[i][idx]--;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= tc; i++) {
            st = new StringTokenizer(br.readLine());
            char A = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            System.out.println(prefixSum[start][A - 'a'] - prefixSum[end + 1][A - 'a']);
        }

    }
}