package programmers;

import java.util.*;

public class problem159993 {

    static int[][] check;
    static boolean[][] visited;

    static Point start;
    static Point lever;
    static Point end;

    static int[] dx = {-1, 1, 0, 0};  //상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        //String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        //String[] maps = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};
        //String[] maps = {"SOOOO", "XXXXX", "LOOOO", "XXXXX", "EOOOO"};
        String[] maps = {"OOOOOL", "OXOXOO", "OOSXOX", "OXXXOX", "EOOOOX"};
        //String[] maps = {"SOOOO", "XXXXX", "LOOOO", "XXXXX", "EOOOO"};

        System.out.println(solution(maps));
    }

    public static int solution(String[] maps) {
        check = new int[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'S')
                    start = new Point(i, j);
                else if (maps[i].charAt(j) == 'L')
                    lever = new Point(i, j);
                else if (maps[i].charAt(j) == 'E')
                    end = new Point(i, j);
            }
        }
        if(bfsToLev(maps)) {
            if(bfsToEnd(maps)) {
                return check[end.x][end.y];
            }
        }
        return -1;

    }

    public static boolean bfsToLev(String[] maps) {
        boolean flag = false;
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (maps[p.x].charAt(p.y) == 'L') {
                flag = true;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length() || maps[nx].charAt(ny) == 'X' || visited[nx][ny])
                    continue;
                check[nx][ny] = check[p.x][p.y] + 1;
                queue.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return flag;
    }

    public static boolean bfsToEnd(String[] maps) {
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'L')
                    continue;
                visited[i][j] = false;
                check[i][j] = 0;
            }
        }

        boolean flag = false;
        Queue<Point> queue = new LinkedList<>();
        queue.add(lever);
        visited[lever.x][lever.y] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (maps[p.x].charAt(p.y) == 'E') {
                flag = true;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length() || maps[nx].charAt(ny) == 'X' || visited[nx][ny])
                    continue;
                check[nx][ny] = check[p.x][p.y] + 1;
                queue.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return flag;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

