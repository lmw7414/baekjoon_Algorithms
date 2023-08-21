import java.util.Scanner;

/**
 * 베이킹에 사용하는 오븐을 1개 가지고 있다
 * 3개의 시간 조절용 버튼  A 5분 -> 300초, B 1분 -> 60초, C 10초 
 * @author SSAFY
 *
 */
public class Main {

	static int[] timer = {300, 60, 10};
	static int[] cnt = {0, 0, 0};
	static int answer = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 입력 받을  T초
		int idx = 0;  // timer를 순회할 인덱스
		while(true) {
			if(idx == 3 && T != 0) {  // idx가 timer의 배열 사이즈를 넘어갔고 이때 T는 0이 아니라면 -1
				answer = -1;
				break;
			}
			if(idx == 3) break; // 배열 사이즈를 넘어갔다면 반복문 종료(정상적인 케이스)
			if(T >= timer[idx]) {
				cnt[idx] = T / timer[idx]; // 해당 버튼을 눌러야 하는 카운트 저장
				T %= timer[idx];  // 버튼을 누르고 남은 나머지를 T에 저장
			} 
			idx++;  // 인덱스 증가
		}
		
		if(answer == -1)  // 시간을 정확히 마칠 수 없는 경우
			System.out.println(answer);
		else { // 시간을 정확히 맞춘경우
			for(int i = 0; i< 3; i++)
				System.out.print(cnt[i] + " ");
			System.out.println();
		}
			

	}

}