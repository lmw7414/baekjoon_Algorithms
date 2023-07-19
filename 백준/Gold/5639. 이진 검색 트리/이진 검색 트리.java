import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));
        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.equals("") ) break;
            int data = Integer.parseInt(input);
            root.addNode(data);
        }
        postOrder(root);
    }

    public static void postOrder(Node n) {
        if (n == null) return;
        postOrder(n.left);
        postOrder(n.right);
        System.out.println(n.data);
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public void addNode(int data) {
            if (this.data > data) {// 왼쪽 저장
                if (this.left == null)
                    this.left = new Node(data);
                else
                    this.left.addNode(data);

            } else {
                if (this.right == null)
                    this.right = new Node(data);
                else
                    this.right.addNode(data);
            }
        }
    }


}