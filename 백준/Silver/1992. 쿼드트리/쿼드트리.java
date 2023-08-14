import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i = 0; i< N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		calc(0, 0, N);
		System.out.print(sb);
	}
	// 왼쪽 상단 -> 오른쪽 상단 -> 왼쪽 하단 -> 오른쪽 하단
	public static void calc(int x, int y, int N) {
		
		int flag = arr[x][y];
		boolean check = true;
		for(int i = x; i < x + N; i++) {
			for(int j = y; j < y + N; j++) {
				if(arr[i][j] != flag) {
					check = false;
					break;
				}
			}
			if(!check) break;
		}
		
		if(check) {
			sb.append(flag);
		}else {
			sb.append("(");
			int half = N / 2;
			calc(x, y, half);
			calc(x, y + half, half);
			calc(x + half, y, half);
			calc(x + half, y + half, half);
			sb.append(")");
		}
		
	}

}