
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[문제 해결 프로세스]
* 누적합 *
1. 금액 순 오름차순 정렬
3. 낮은 금액부터 탐색(최대 10만)
4. 현재 무게가 조건에 해당하면 해당 금액 리턴
5. 무게가 맞지 않다면 누적합으로 저장
6. 다음 금액으로 이동
7. 무게가 맞을 때까지 반복
 */
public class Main {
    static int N, M;
    static Meat[] meats;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        meats = new Meat[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            meats[n] = new Meat(weight, price);
        }
        Arrays.sort(meats, (a,b) -> {
            if(a.price == b.price) return b.weight - a.weight;
            return a.price - b.price;
        });

        int totalWeight = 0; // 현재 금액 이전까지의 무게
        int weightPerPrice = 0; // 현재 금액에 할당된 모든 고기의 무게
        int curPrice = meats[0].price;
        int price = 0;
        long answer = Long.MAX_VALUE;
        for (int n = 0; n < N; n++) {
            Meat meat = meats[n];
            if(meat.price == curPrice) { // 현재 할당된 비용에 대한 무게 누적합
                weightPerPrice += meat.weight;
                price += meat.price;
            } else { // 이전 비용에 비해 높아졌다면
                totalWeight += weightPerPrice; // 토탈 무게 변경
                weightPerPrice = meat.weight; // 현재 비용에 대한 무게 초기화
                curPrice = meat.price;
                price = meat.price;
            }
            if (totalWeight + weightPerPrice >= M) {// totalWeight + weight의 무게가 조건을 충족한다면
                answer = Math.min(answer, price);
            }
        }
        if(answer == Long.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static class Meat {
        int weight;
        int price;

        public Meat(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
}