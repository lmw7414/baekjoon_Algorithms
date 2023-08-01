import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] ladder;
	static int destY;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int i = 1; i<= 10; i++) {
			br.readLine();
			ladder = new int[102][102];
			for(int j = 1; j <= 100; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 1; k<= 100; k++) {
					ladder[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			destY = findDestY();
			printAnswer(i, calc()-1);
		}

	}
	
	public static int calc() {
		int startX = 99;
		int startY = destY;
		int[] dx = {-1, 0, 0};// 상, 좌, 우
		int[] dy = {0, -1, 1};
		
		while(startX >= 1) {
			boolean flag = false;
			if(checkBoarder(startX, startY - 1)) {
				if(ladder[startX][startY - 1] == 1) {// 좌 탐색
					while(true) {
						if(ladder[startX][startY - 1] == 1)
							startY--;
						else break;
					}
					startX--;
					flag = true;
				}
			}
			if(checkBoarder(startX, startY + 1)) {
				if(ladder[startX][startY + 1] == 1) { // 우 탐색
					while(true) {
						if(ladder[startX][startY + 1] == 1)
							startY++;
						else break;
					}
					startX--;
					flag = true;
				}
			}
			if(!flag) startX--;
		}


		return startY;
	}
	
	public static boolean checkBoarder(int x, int y) {
		return (x > 0 && y > 0 && x <= 100 && y <= 100);
	}
	public static void printAnswer(int tc, int answer) {
		System.out.println("#" + tc + " " + answer);
	}
	
	public static int findDestY() {
		for(int i = 1; i <= 100; i++) {
			if(ladder[100][i] == 2)
				return i;
		}
		return -1;
	}

}