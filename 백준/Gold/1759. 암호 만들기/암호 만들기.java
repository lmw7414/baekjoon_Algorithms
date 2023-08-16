import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int L, C;
	static char[] answer;
	static char[] cArr;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] LC = br.readLine().split(" ");
		L = Integer.parseInt(LC[0]);  // 만들어야 할 암호의 크기
		C = Integer.parseInt(LC[1]);  // 주어지는 문자열의 사이즈
		
		cArr = new char[C];  // 입력받은 문자열을 char 형으로 변환하여 저장
		visit = new boolean[C];
		
		answer = new char[L];
		
		String[] str = br.readLine().split(" ");
		for(int i = 0; i< str.length; i++) cArr[i] = str[i].charAt(0);

		Arrays.sort(cArr);
		dfs(0, 0);
		System.out.print(sb);
		

	}
	
	public static void dfs(int idx, int depth) {
		if(depth == L) {
			if(check(answer)) {
				for(char a : answer) sb.append(a);
				sb.append("\n");
				return;
			}
			return;
		}
		
		for(int i = idx; i < C; i++) {
			if(visit[i]) continue;
			
			visit[i] = true;
			answer[depth] = cArr[i];
			dfs(i + 1, depth + 1);
			visit[i] = false;
		}
	}
	
	
	public static boolean check(char[] chars) {
		int vowel = 0;  // 모음
		int consonant = 0;  // 자음
		for(char a : chars) {
			if(a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u') vowel++;
			else consonant++;
		}
		if(consonant < 2 || vowel < 1) return false;  // 자음이 2개 보다 적거나 모음이 1개 보다 적으면 false 반환
		else return true;
	}

}