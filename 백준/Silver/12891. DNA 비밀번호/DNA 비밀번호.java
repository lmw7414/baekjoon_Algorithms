import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] answer = new int[4];  // ACGT 갯수를 저장하기 위한 배열
	static int[] cur = new int[4]; // 주어진 문자열에서 알파벳 카운트하기 위한 배열
	static int cnt = 0;  // 만들 수 있는 비밀번호의 종류의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int strLen = Integer.parseInt(st.nextToken());  // 주어진 문자열의 길이
		int partLen = Integer.parseInt(st.nextToken());  // 만들어야 하는 부분문자열의 길이
		String str = br.readLine();
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < 4; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		
		int startIdx = 0;  // 시작 문자열
		int endIdx = partLen;  // 끝 문자열
		
		// 부분문자열의 알파벳 개수 미리 탐색 후 다음 탐색 부터는 제거되는 알파벳, 추가되는 알파벳만 감소, 증가
		for(int i = startIdx; i< endIdx; i++)
			addChar(str.charAt(i), cur);
		if(check(answer, cur)) cnt++;
		
		// 끝 인덱스가 문자열의 마지막에 도착할 때까지
		while(endIdx < strLen) {
			subChar(str.charAt(startIdx++), cur);
			addChar(str.charAt(endIdx++), cur);
			if(check(answer, cur)) cnt++;
		}
		System.out.println(cnt);
	}
	
	public static boolean check(int[] answer, int[] cur) {
		for(int i = 0; i< 4; i++) {
			if(cur[i] < answer[i]) 
				return false; 
		}
		return true;
	}
	
	public static void addChar(char C , int[] arr) {
		switch(C) {
		case 'A':
			arr[0]++;
			break;
		case 'C':
			arr[1]++;
			break;
		case 'G':
			arr[2]++;
			break;
		case 'T':
			arr[3]++;
			break;
		}
	}
	
	public static void subChar(char C , int[] arr) {
		switch(C) {
		case 'A':
			arr[0]--;
			break;
		case 'C':
			arr[1]--;
			break;
		case 'G':
			arr[2]--;
			break;
		case 'T':
			arr[3]--;
			break;
		}
	}
	
}