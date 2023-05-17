
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static HashMap<Character, ArrayList<Character>> hm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        hm = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            hm.put(root, new ArrayList<>(List.of(left, right)));
        }
        preorder('A');
        System.out.println();
        inorder('A');
        System.out.println();
        postorder('A');

    }

    public static void preorder(char root) {
        if (root == '.') return;
        System.out.print(root);
        preorder(hm.get(root).get(0));
        preorder(hm.get(root).get(1));
    }

    public static void inorder(char root) {
        if (root == '.') return;
        inorder(hm.get(root).get(0));
        System.out.print(root);
        inorder(hm.get(root).get(1));
    }

    public static void postorder(char root) {
        if (root == '.') return;
        postorder(hm.get(root).get(0));
        postorder(hm.get(root).get(1));
        System.out.print(root);
    }
}



