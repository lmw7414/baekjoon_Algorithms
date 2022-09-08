package programmers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 3 2 7 2
 * 4 6 5 1
 * 2
 *
 * 1 2 1 2
 * 1 10 1 2
 * 7
 *
 * 1 1
 * 1 5
 * -1
 */
//두 큐 합 같게 만들기
public class problem118667 {

    public static void main(String[] args) {

        int A1[] = {3, 2, 7, 2};
        int B1[] = {4, 6, 5, 1};

        int A2[] = {1, 2, 1, 2};
        int B2[] = {1, 10, 1, 2};

        int A3[] ={1,1};
        int B3[] ={1,5};

        System.out.println(solution(A1, B1));
        System.out.println(solution(A2, B2));
        System.out.println(solution(A3, B3));

    }
    static public int solution(int[] queue1, int[] queue2) {
        long total;
        long totalA =0;
        long totalB = 0;
        Queue<Integer> queueA = new LinkedList<>();
        Queue<Integer> queueB = new LinkedList<>();

        for(int i = 0; i< queue1.length; i++) {
            queueA.add(queue1[i]);
            totalA += queue1[i];
        }
        for(int i = 0; i< queue2.length; i++) {
            queueB.add(queue2[i]);
            totalB += queue2[i];
        }
        total = totalA + totalB;
        if(total %2 !=0)
            return -1;

        total /=2;

        int limit = queueA.size()*2;

        int data;
        int countA = 0;
        int countB = 0;
        while(countA <= limit && countB <= limit) {
            if(totalA == totalB)
                return countA + countB;

            if(totalA < total){
                data = queueB.poll();
                queueA.add(data);
                totalA += data;
                totalB -= data;
                countB++;
            }

            else{
                data = queueA.poll();
                queueB.add(data);
                totalB += data;
                totalA -= data;
                countA++;
            }
        }

        return -1;
    }
}
