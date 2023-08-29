import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 1:위, 2:아래, 3:오른쪽, 4:왼쪽
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};
	
    static int UP=1, DOWN=2, RIGHT=3, LEFT=4;
    
	static int R, C;	//행, 열 크기
	static int M;	//상어 수
	
	static Shark[][] map;	//격자판의 상태
	
	static int sum;	//잡은 상어 크기의 합
	
	static class Shark{
		int s;	//속력
		int d;	//방향
		int z;	//크기
		
		public Shark( int s, int d, int z ) {
			//최적화 - 같은 방향에 제자리로 돌아오는 횟수는 제외
			//위, 아래
			if( d <= 2 ) s %= 2*(R-1);
			//왼쪽, 오른쪽
			else s %= 2*(C-1);
			
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R+1][C+1];
		for(int i=0 ; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			Shark shark = new Shark(s,d,z);	//상어 객체 생성
			map[r][c] = shark;	//초기 상어정보 담기
		}
	
		//step 01. 낚시왕이 오른쪽으로 1칸 이동 (1~C 만큼 이동)
		for(int c=1; c<=C; c++) {
			
			//step 02. 상어 잡기
			catchShark( c );
			
			//step 03. 상어 이동
			moveShark();
		}
		
		System.out.println(sum);
	}

	/**
	 * 모든 상어가 이동 후, 배치
	 */
	private static void moveShark() {
		Shark[][] temp = new Shark[R+1][C+1];	//상어를 배치할 임시배열
		
		//상어 찾기
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(map[i][j]==null) continue;
				Shark shark = map[i][j];	//이동할 상어 정보
				
				//1. 이동위치 계산
				int r = i;
				int c = j;
				int s = shark.s;
				int d = shark.d;
				
				//속력(s) 만큼 한 칸씩 이동
				while(s-->0) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					//다음 좌표가 경계를 벗어나는 경우 방향 변경
					if( nr == 0 ) d = DOWN;
					else if( nr == R+1) d = UP;
					else if( nc == 0 ) d = RIGHT;
					else if( nc == C+1) d = LEFT;
						
					//다음 위치로 한 칸 이동
					r+=dr[d];
					c+=dc[d];
				}
				//바뀐 방향 정보 세팅
				shark.d = d;
				
				
				//2. 해당 위치에 배치
				//2-1. 상어가 없는 경우 - 상어 배치
				if(temp[r][c]==null) {
					temp[r][c] = shark;
				}
				//2-2. 상어가 있는 경우
				else {
					//현재 상어가 큰 경우만 배치
					if(temp[r][c].z < shark.z) {
						temp[r][c] = shark;
					}
				}
			}
		}
		//기존 배열이 temp를 참조하도록 설정
		map = temp;
	}

	/**
	 * c 열에서 1~R행 탐색하며 상어 발견 시, 잡고 제거 후 종료
	 * @param c 낚시왕이 위치한 열
	 */
	private static void catchShark(int c) {
		for(int r=1; r<=R; r++) {
			if(map[r][c]!=null) {
				sum += map[r][c].z;	//상어 크기 cnt
				map[r][c] = null;	//상어 제거
				return; //종료
			}
		}
	}
}