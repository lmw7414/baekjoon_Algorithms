import java.util.Scanner;
import java.util.Stack;

/**
 * 분단위로 업무가 추가되고 있음
 * 업무 마감기한은 1분기가 끝날때까지
 * 
 * 업무는 가장 최근에 주어진 순서대로 한다(스택)
 * 업무 도중에 받으면 현재 하고 있는 업무를 중단하고 새로운 업무를 진행
 * 새로운 업무가 끝났다면 이전에 하던 업무를 이전에 하던 부분부터 이어서 한다.
 * @author SSAFY
 *
 */
public class Main {

	static int N;
	static int result = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Task> stack = new Stack<>();
		N = sc.nextInt(); // 이번 분기의 주어진 시간

		Task current = null;
		for(int i = 0; i < N; i++) {  // 흐르는 시간초
			int t = sc.nextInt();
			if(t == 0) {  // 기존 업무 마저 진행
				if(!stack.isEmpty()) {// 스택에 업무가 있다면 지금 해야할 일
					current = stack.pop();  // 스택에서 빼서 업무를 본다.
					current.time--;
					if(current.time <= 0) {  // 업무가 만약 끝났다면 점수 증가
						result += current.score;
						continue;  // 다음 스택에 넣을 필요 없음
					}
				}
				else continue; // 스택에 업무가 없다면 지금 할 일은 없다.
			}
		

			else if(t == 1) {  // 업무가 주어진 경우
				int score = sc.nextInt();
				int time = sc.nextInt();

				current = new Task(score, time - 1);  //새로운 업무 할당받아서 바로 진행
				if(current.time <= 0) {  // 업무가 끝났는지 체크
					result += current.score;
					continue;  // 끝났다면 스택에 넣지 않고 다음 시간초로 넘어감
				}
			} 
			
			stack.push(current);
		}
		System.out.println(result);
	}

	static class Task {
		int score;  // 업무를 마치고 나면 받는 점수
		int time;  // 남은 시간

		public Task(int score, int time) {
			this.score = score;
			this.time = time;
		}
	}

}