import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] game;
	static int[] playerList;  // 타자 순서 리스트
	static boolean[] visit;
	static int finalScore = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		game = new int[N][10];
		playerList = new int[10];
		visit = new boolean[10];
		visit[1] = true;  // 4번타자는 1번 선수로 고정이므로 미리 체크
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 9; j++) {
				game[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(1);
		System.out.println(finalScore);
	}
	//1~9까지 playerList가 완성된 상태
	// 1번 타자부터 돌아간다.
	public static void calc() {
		int idx = 1;  // 9번타자가 타석을 마쳤을 경우 1번 타자로 이동 -> %10으로 처리할 예정
		int outCnt = 0; // 3아웃인 경우 이닝 끝
		int striker = playerList[idx];
		int score = 0;
		boolean[] ruta = new boolean[4];
		//홈, 1루타, 2루타, 3루타
		// 이닝시작!!
		for(int i = 0; i< N; i++) {
			outCnt = 0;
			ruta = new boolean[4];
			while(outCnt < 3) {
				striker = game[i][playerList[idx]];  // 0 1 2 3 중 하나
				if(striker == 0) {  // 아웃일 경우
					outCnt++;
				}
				else {
					if(striker == 4) {
						// 홈런인 경우 true인 루의 갯수를 카운트에 점수를 증가시키고 루의 조건을 false로 변경 
						for(int ru = 1; ru<=3; ru++) {
							if(ruta[ru]) {
								score++;
								ruta[ru] = false;
							}
						}
						score++;
					}
					else { // 1루타 2루타 3루타 인 경우
						for(int ru = 3; ru>=1; ru--) {  // 타자 이동
							if(ruta[ru]) {
								if((ru + striker) >= 4) {
									score++;
									ruta[ru] = false;
								} else {  // 3루타 안에 있을 경우
									ruta[ru] = false;
									ruta[ru + striker] = true;
								}
							}
						}
						ruta[striker] = true; //마지막 타자의 이동
						
					}
				}
				idx++;
				idx %= 10;
				if(idx == 0) idx = 1;
			}
			
		}
		finalScore = Math.max(finalScore, score);
	}
	// 순열
	public static void dfs(int depth) {
		if(depth == 10) {
			calc();
			return;
		}
		if(depth == 4) {  // 4번타자는 1번 선수로 고정
			playerList[depth] = 1;
			dfs(depth+1);
		} else {
			for(int i = 2; i< 10; i++) {
				if(visit[i]) continue;  // 방문했으므로 넘어감
				visit[i] = true;
				playerList[depth] = i;
				dfs(depth+1);
				visit[i] = false;
			}
		}
		
	}
}