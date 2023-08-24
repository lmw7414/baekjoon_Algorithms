import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 가로 세로 크기는 10으로 고정
 * A는 0,0에서 B는 9,9에서 출발
 * 총 이동하는 시간은 20초에서 100초 사이
 * 충전기의 개수는 최대 8개
 * 충전기의 범위는 최대 4
 * 충전기의 성능은 10이상 500이하
 * 같은위치에 충전기 2개가 설치될 수는 없음
 * 
 * [문제 해결 시나리오]
 * 1. 충전기 관련 정보 입력 받기 - main()
 * 2. 비트 마스킹으로 배열에 저장하기 - installBC();
 * 3. 시간초에 따라 사람  A, B 이동시키기 - move();, movePerson();
 * 4. 각 초마다 A, B가 충전기 위치에 있는지 체크, TreeSet으로 반환 - isIn(); 
 * 5. A와 B의 최대 충전량 계산하기 - calcCharge();
 */
public class Solution {

	static int M, A;  // 입력받을 시간초, 충전기 개수
	static int[][] board;
	static int[][] dir; // 사람의 이동방향 저장할 배열
	static int[] personA; // A의 위치
	static int[] personB; // B의 위치
	static List<BC> bcs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());  // 시간초
			A = Integer.parseInt(st.nextToken());  // 충전기 개수

			dir = new int[2][M];
			personA = new int[]{0, 0};
			personB = new int[]{9, 9};
			bcs = new ArrayList<>();

			// A의 이동 방향 저장하기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				dir[0][i] = Integer.parseInt(st.nextToken());
			}

			// B의 이동 방향 저장하기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				dir[1][i] = Integer.parseInt(st.nextToken());
			}

			// 충전기 저장하기
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());
				bcs.add(new BC(x - 1, y - 1, range, energy));
			}
			installBC(bcs);
			System.out.println("#" + tc + " " + move());
			//printMap();
		}
	}
	// 시간초마다 이동하는 메서드
	public static int move() {
		int answer = 0;
		//초기위치에서 충전되는지 확인
		TreeSet<BC> A = isIn(personA[0], personA[1]);
		TreeSet<BC> B = isIn(personB[0], personB[1]);
		answer += calcCharge(A, B);
		// i는 시간초
		for (int i = 0; i < M; i++) {
			personA = movePerson(personA[0], personA[1], dir[0][i]);
			personB = movePerson(personB[0], personB[1], dir[1][i]);
			A = isIn(personA[0], personA[1]);
			B = isIn(personB[0], personB[1]);
			answer += calcCharge(A, B);
		}
		return answer;
	}
	// 사람 이동
	public static int[] movePerson(int x, int y, int dir) {
		switch (dir) {
		case 0: return new int[]{x, y};     // 이동하지 않음
		case 1: return new int[]{x - 1, y}; // 위로 이동
		case 2: return new int[]{x, y + 1}; // 우로 이동
		case 3: return new int[]{x + 1, y}; // 하로 이동
		case 4: return new int[]{x, y - 1}; // 좌로 이동
		}
		return new int[]{x, y};
	}

	// 현재 있는 곳이 충전기 위치인지 확인하는 메서드
	// 비트마스킹 되어 있는 지역을 확인하면서 몇번 충전기의 위치에 사람이 서있는지 확인 후 TreeSet 배열에 저장
	// TreeSet을 사용한 이유는 정렬하여 뽑을 때 가장 큰 에너지 충전기를 가져오기 위해서
	public static TreeSet<BC> isIn(int x, int y) {
		// 0이면 충전기 위치 아님
		TreeSet<BC> result = new TreeSet<>((a1, b1) -> b1.energy - a1.energy);
		for (int i = 0; i < A; i++)
			if ((board[x][y] & 1 << i) != 0) result.add(bcs.get(A - i - 1));

		return result;
	}

	// 사람 A, B의 위치에서 충전기 정보를 받아 최댓값을 반환
	public static int calcCharge(TreeSet<BC> A, TreeSet<BC> B) {
		//둘다 충전기 위치가 아닐경우
		if (A.isEmpty() && B.isEmpty()) return 0;
		// A혼자 충전기 위치인 경우
		if (!A.isEmpty() && B.isEmpty()) return A.first().energy;
		// B혼자 충전기 위치인 경우
		if (A.isEmpty() && !B.isEmpty()) return B.first().energy;
		// 둘다 충전기 위치이지만 서로 겹치지 않을 때
		if (!A.isEmpty() && !B.isEmpty() && (A.first() != B.first())) return A.first().energy + B.first().energy;

		// 겹칠경우
		if (A.size() == 1 && B.size() == 1) {
			if (A.first() == B.first())   // 같은 충전기인 경우
				return A.first().energy;
		}
		// 각각 공통된 충전 지역 하나, 서로 다른 충전 지역 하나. 즉 각 사람이 2개 이상의 충전 지역에 해당함
		// ex A(360, 240), B(360, 10)일 경우 370rhk 600 중 600을 선택해야함.
		if (A.size() > 1 && B.size() > 1) {
			if (A.first() == B.first()) {
				int value;
				BC chooseA = A.first();
				B.pollFirst();  // B에 있는 A의 충전지를 빼준다.
				value = chooseA.energy + B.first().energy; // 그리고나서 최대값 체크

				B.add(chooseA);  // 반대의 경우도 체크해준다. 아까 뺏던 값을 B에 넣어주고
				A.pollFirst();  // A에서는 하나 제거
				value = Math.max(value, B.first().energy + A.first().energy);
				return value;
			}
		}

		// 한 사람이 2개이상의 충전지역에 걸쳐있고, 나머지 하나는 A와 같은 지역 중 하나만 가지고 있는 경우
		// ex) A(360, 240), B(360) -> 600
		if (A.size() != B.size()) {
			int value = 0;
			// 크기가 작은 것을 A로
			if (A.size() > B.size()) {
				TreeSet<BC> temp = A;
				A = B;
				B = temp;
			}
			value += A.first().energy;
			B.remove(A.first());
			value += B.first().energy;
			return value;
		}

		return 0;
	}

	// 충전기 설치하기
	// 충전기 리스트의 정보를 얻어 배열에다 범위에 맞게 저장
	// 비트마스킹 사용하여 충전기 정보 저장 
	// ex) 101(2) -> 1번과 3번 충전기의 정보, 1101(2) -> 1번 2번 4번 충전기 정보 저장

	public static void installBC(List<BC> bcs) {
		board = new int[10][10];
		for (BC bc : bcs) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (calcDist(bc.x, bc.y, i, j) <= bc.range) {
						if (board[i][j] > 0) { // 이미 충전기가 설치된 지역이라면
							board[i][j] <<= 1;  // 왼쪽으로 한칸 쉬프트하고
							board[i][j] += 1;  // 1증가
						} else board[i][j] = 1;  // 충전 지역일 경우0에서 1로 세팅
					} else board[i][j] <<= 1;  // 왼쪽으로 쉬프트
				}
			}
		}
	}

	// 거리 계산 메서드
	public static int calcDist(int bcX, int bcY, int x, int y) {
		return Math.abs(bcX - x) + Math.abs(bcY - y);
	}

	static class BC {
		int x;
		int y;
		int range;
		int energy;

		public BC(int x, int y, int range, int energy) {
			this.x = x;
			this.y = y;
			this.range = range;
			this.energy = energy;
		}
	}
}