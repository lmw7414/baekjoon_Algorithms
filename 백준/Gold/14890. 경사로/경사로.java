import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, X;
	static int[][] arr;
	static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			cntRow(i);
			cntCol(i);
		}
		System.out.println(answer);
	}

	// 가로 체크
	public static void cntRow(int row) {
		int[] dp = makeDp(row, 'R');
		for (int i = 0; i < N; i++) {

			// 현재 스텝
			int start = arr[row][i];
			int lastIdx = curLastIdx(row, i, start);
			int cnt = dp[lastIdx];

			// 다음 스텝
			if (lastIdx < N - 1) { // N보다 lastIdx가 클 경우 해당 스텝은 마지막 스텝
				int next = arr[row][lastIdx + 1];
				int nLastIdx = curLastIdx(row, lastIdx + 1, next);
				int nCnt = dp[nLastIdx];
				if (Math.abs(start - next) > 1)
					break;
				if (start > next) { // 다음 경사가 낮을 경우
					if (nCnt >= X) {
						dp[nLastIdx] -= X;
						i = lastIdx;
					} else {
						break; // 활주로 건설 불가
					}
				} else if (start < next) { // 다음 경사가 높을 경우
					if (cnt < X) {
						break; // 활주로 건설 불가
					} else {
						i = lastIdx;
					}
				}

			} else {
				answer++;
				break;
			}

		}
	}

	// 세로 체크
	public static void cntCol(int col) {
		int[] dp = makeDp(col, 'C');
		for (int i = 0; i < N; i++) {

			// 현재 스텝
			int start = arr[i][col];
			int lastIdx = curLastIdx2(col, i, start);
			int cnt = dp[lastIdx];

			// 다음 스텝
			if (lastIdx < N - 1) { // N보다 lastIdx가 클 경우 해당 스텝은 마지막 스텝
				int next = arr[lastIdx + 1][col];
				int nLastIdx = curLastIdx2(col, lastIdx + 1, next);
				int nCnt = dp[nLastIdx];
				if (Math.abs(start - next) > 1)
					break;
				if (start > next) { // 다음 경사가 낮을 경우
					if (nCnt >= X) {
						dp[nLastIdx] -= X;
						i = lastIdx;
					} else {
						break; // 활주로 건설 불가
					}
				} else if (start < next) { // 다음 경사가 높을 경우
					if (cnt < X) {
						break; // 활주로 건설 불가
					} else {
						i = lastIdx;
					}
				}

			} else {
				answer++;
				break;
			}

		}
	}

	// 현재 경사의 마지막 인덱스 찾기 - row
	public static int curLastIdx(int row, int startIdx, int start) {
		int j = startIdx;
		while (start == arr[row][j]) {
			j++;
			if (j >= N)
				break;
		}
		return j - 1;
	}

	// 현재 경사의 마지막 인덱스 찾기 - col
	public static int curLastIdx2(int col, int startIdx, int start) {
		int j = startIdx;
		while (start == arr[j][col]) {
			j++;
			if (j >= N)
				break;
		}
		return j - 1;
	}

	public static int[] makeDp(int idx, char c) {
		int[] dp = new int[N];
		if (c == 'R') {
			int start = arr[idx][0];
			int cnt = 1;
			for (int i = 0; i < N; i++) {
				if (arr[idx][i] == start) {
					dp[i] = cnt++;
				} else {
					cnt = 1;
					start = arr[idx][i];
					dp[i] = cnt++;
				}
			}
		} else {
			int start = arr[0][idx];
			int cnt = 1;
			for (int i = 0; i < N; i++) {
				if (arr[i][idx] == start) {
					dp[i] = cnt++;
				} else {
					cnt = 1;
					start = arr[i][idx];
					dp[i] = cnt++;
				}
			}
		}
		return dp;
	}
}