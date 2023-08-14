import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<Character, ArrayList<Character>> hm = new HashMap<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			char child1 = st.nextToken().charAt(0);
			char child2 = st.nextToken().charAt(0);
			
			hm.put(parent, new ArrayList<>());
			hm.get(parent).add(child1);
			hm.get(parent).add(child2);
			
		}
		preorderTraversal('A');
		sb.append("\n");
		inorderTraversal('A');
		sb.append("\n");
		postorderTraversal('A');
		System.out.println(sb);

		
	}
	//전위
	public static void preorderTraversal(char root) {
		if(root == '.') return;
		sb.append(root);
		preorderTraversal(hm.get(root).get(0));
		preorderTraversal(hm.get(root).get(1));
	
	}
	//중위
	public static void inorderTraversal(char root) {
		if(root == '.') return;
		inorderTraversal(hm.get(root).get(0));
		sb.append(root);
		inorderTraversal(hm.get(root).get(1));
		
	}
	//후위
	public static void postorderTraversal(char root) {
		if(root == '.') return;
		postorderTraversal(hm.get(root).get(0));
		postorderTraversal(hm.get(root).get(1));
		sb.append(root);
	}
	
}