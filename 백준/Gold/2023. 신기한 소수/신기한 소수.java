import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 
 * 1의자리가 소수여야한다.
 * 1, 10의 자리가 소수여야 한다.
 * 1, 10, 100의 자리가 소수여야 한다.
 * 1, 10, 100 ... N의 자리까지 소수여야한다.
 * 
 * 1의자리가 소수인 수를 찾았다면 다음에서 10의 자리까지 소수인 숫자를 찾고 넘겨준다.
 * 
 */
public class Main {
	
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		calc(0, "");
		
		System.out.print(sb);

	}
	/**
	 * 
	 * @param depth : 현재의 깊이. 깊이가 N이 되면 N자리수를 만들었다는 의미로 정답에 해당
	 * @param num : 현재까지 만들어진 숫자. depth == N일 때 num이 저장
	 */
	public static void calc(int depth, String num) {

		if(depth == N) {
			sb.append(num).append("\n");
			return;
		}
		for(int i = 1; i < 10; i++) {
			if(depth == 0 && i == 1) continue;  // 1은 소수가 될 수 없다.
			if(isPrime(Integer.parseInt(num + i))) {
				calc(depth + 1, num + i);
			}
		}
	}
	
	public static boolean isPrime(int num) {
		for(int i = 2; i < num; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

}