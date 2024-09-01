import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
- 4,6,9,11 -> 30일
- 1,3,5,7,8,10,12 -> 31일
- 2 -> 28일
3월 1일 부터 11월 30일까지 매일 꽃이 한가지 이상
두 조건을 만족하지 못하면 0 출력
[문제 해결 프로세스]
- O(n)에 끝내야 함
- 정렬 조건
    - 시작일 순 오름차순
    - 시작일이 같다면 마지막일 내림차순
 */
public class Main {
    static int N;
    static int[] day = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static List<Flower> flowers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] days = new int[13];
        for (int i = 1; i < 13; i++) { // 누적합
            days[i] = days[i - 1] + day[i];
        }
        N = Integer.parseInt(br.readLine());
        flowers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sMonth = Integer.parseInt(st.nextToken());
            int sDay = Integer.parseInt(st.nextToken());
            int eMonth = Integer.parseInt(st.nextToken());
            int eDay = Integer.parseInt(st.nextToken());
            int start = days[sMonth - 1] + sDay;
            int end = days[eMonth - 1] + eDay;

            if (eMonth <= 2) continue; // 마지막이 3월 1일인 이전인 경우 제거
            if (sMonth > 11) continue; // 시작이 11월 30일 이후인 경우 제거
            if (sMonth <= 2) start = 60; // 3월 1일 부터 시작
            if (eMonth > 11) end = 335; // 마지막일은 12월 1일
            if(start == end) continue;
            if(start > end) end = 335;
            flowers.add(new Flower(start, end));
        }
        flowers.sort((a1, b1) -> {
            if (a1.start == b1.start) return b1.end - a1.end;
            return a1.start - b1.start;
        });
//        for(Flower flower : flowers) System.out.println(flower.start + " " + flower.end);
        if(flowers.isEmpty()) {
            System.out.println(0);
            System.exit(0);
        }
        int start = 60;
        int answer = 0;
        int idx = 0;
        while (start < 335) {
            boolean flag = false;
            int next = start;
            for(;idx < flowers.size(); idx++) {
                if(flowers.get(idx).start > start) break;
                if(flowers.get(idx).end > next) {
                    next = flowers.get(idx).end;
                    flag = true;
                }
            }
            if(flag) {
                start = next;
                answer++;
            } else break;
        }
        if(start < 335) System.out.println(0);
        else System.out.println(answer);
    }

    static class Flower {
        int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}