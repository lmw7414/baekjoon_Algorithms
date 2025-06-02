
import java.io.*;
import java.util.*;

/**
 * 1~K까지의 말
 * - 상하좌우 이동 가능
 * 1턴 :
 * - 1번말 -> K번말까지 순서대로 이동
 * - 나보다 위에 있는 말도 같이 이동
 * - 가장 아래에 있는 말만 가능
 * - 빨강 : 이동시키고 나서 말의 순서를 반대로 바꿈
 * - 파랑 : 말의 이동 방향 반대로 변경 후 한 칸 이동, 이동하려는 칸이 파란색인  경우 이동하지 않고 방향만 변경
 */

class Main {
    static int N, K;
    static final int LIMIT = 1000;
    static List<Node>[][] arr;
    static int[][] color;
    static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dy = {0, 1, 0, -1};
    static Node[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 체스판 크기 4 ~ 12
        K = Integer.parseInt(st.nextToken()); // 말의 개수 4 ~ 10
        arr = new List[N][N];
        color = new int[N][N];
        nodes = new Node[K];
        // 0: 흰 1: 빨 2: 파
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = new ArrayList<>();
            }
        }
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            Node node = new Node(k+1, x, y, convertDir(dir));
            arr[x][y].add(node);
            nodes[k] = node;
        }

        System.out.println(simulation());
    }

    public static int convertDir(int dir) {
        if(dir == 1) return 1;
        else if(dir == 2) return 3;
        else if(dir == 3) return 0;
        else return 2;
    }

    public static int simulation() {
        int turn = 0;
        while(turn < LIMIT) {
            turn++;
            for(Node node : nodes) {
                if(node != arr[node.x][node.y].get(0)) continue;
                move(node);
                if(arr[node.x][node.y].size() >= 4) return turn;
            }
        }
        return -1;
    }

    // 다음 이동 방향
    public static void move(Node node) {
        int nx = node.x + dx[node.dir];
        int ny = node.y + dy[node.dir];
        if(OOB(nx, ny) || color[nx][ny] == 2) blue(node);
        else if(color[nx][ny] == 1) red(node, nx, ny);
        else white(node, nx, ny);
    }

    public static void blue(Node node) {
        changeDir(node);
        int nx = node.x + dx[node.dir];
        int ny = node.y + dy[node.dir];
        // 다시 파랑인 경우
        if(OOB(nx, ny) || color[nx][ny] == 2) { // 파란색
        } else if(color[nx][ny] == 1) {// 빨강
            red(node, nx, ny);
        } else if(color[nx][ny] == 0) { // 하양
            white(node, nx, ny);
        }
    }

    public static void red(Node node, int nx, int ny) {
        Stack<Node> stack = new Stack<>();
        for(Node cur : arr[node.x][node.y]) stack.push(cur);
        arr[node.x][node.y].clear();
        while(!stack.isEmpty()) {
            Node cur = stack.pop();
            cur.x = nx;
            cur.y = ny;
            arr[nx][ny].add(cur);
        }
    }

    public static void white(Node node, int nx, int ny) {
        Queue<Node> queue = new ArrayDeque<>();
        for(Node cur : arr[node.x][node.y]) queue.add(cur);
        arr[node.x][node.y].clear();
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            cur.x = nx;
            cur.y = ny;
            arr[nx][ny].add(cur);
        }
    }

    // 말 방향 변경
    public static void changeDir(Node node) {
        node.dir = (node.dir + 2) % 4;
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    static class Node {
        int id;
        int x, y;
        int dir;

        public Node(int id, int x, int y, int dir) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}