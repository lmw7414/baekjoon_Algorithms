package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;
//마라톤 1
// 시간초과 주의!
// 따라서 체크포인트를 지정하기 이전과 지정한 후의 차이를 계산하여 가장 큰 값을 체크포인트로 선정해야함
public class problem10655 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        ArrayList<Point> pointList = new ArrayList<>();

        int checkPoint[] = new int[N];
        int distance[] = new int[N];
        int x, y;
        for(int i = 0 ; i<N; i++) {
            x = sc.nextInt();
            y = sc.nextInt();

            pointList.add(new Point(x, y));
        }

        for(int i = 1; i< N; i++)
            distance[i] = calculate(pointList.get(i-1), pointList.get(i));

        int index = 0;
        int diff = 0;
        for(int i = 1; i< N-1; i++) {
            checkPoint[i] = calculate(pointList.get(i - 1), pointList.get(i + 1));

            int diff1 = distance[i] + distance[i + 1] - checkPoint[i];
            if(diff < diff1) {
                diff = diff1;
                index = i;
            }
        }

        int answer = 0;
        for(int i =1; i< N; i++) {
            if(i == index) {
                answer += checkPoint[i];
                i++;
            }
            else
                answer += distance[i];
        }

        System.out.println(answer);
    }

    static int calculate(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
