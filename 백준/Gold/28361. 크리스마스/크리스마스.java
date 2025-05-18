
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
집은 원형으로 이루어짐 1 -> 2 -> 3 ... N-1 -> N -> 1 ...
이웃한 집 사이의 거리는 1
산타클로스는 1번 집부터 시작해서 모든 집에 방문해 선물을 나눠준 뒤, 다시 1번 집으로 돌아옴
- 좌 우로 현재 집에서 거리가 2 이하인 집에만 갈 수 있음
- 세 번 연속 같은 방향으로 갈 수 없음
- 같은 집을 두번 연속으로 방문 할 수 없음

1번 집에서 출발하여 가능한 적게 이동하며 모든 집을 방문하고 1번집으로 돌아올 때
이동 횟수와 방문 순서를 출력해라
 */

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean flag  = false;
        sb.append(1).append(" ");
        if(N % 3 == 0) {
            sb.append(N).append(" ");
            flag = true;
        }
        sb.append(2).append(" ");
        sb.append(4).append(" ");
        sb.append(3).append(" ");
        int cur = 3;
        while(true) {
            if(cur + 2 < N) {
                sb.append(cur + 2).append(" ");
            } else {
                sb.append(cur + 2).append(" ");
                break;
            }
            if(cur + 4 <= N) {
                sb.append(cur + 4).append(" ");
            } else {
                break;
            }
            sb.append(cur + 3).append(" ");
            cur += 3;
            if(cur == N - 1) break;
        }
        sb.append(1);
        System.out.println(N);
        System.out.print(sb);

    }

}
