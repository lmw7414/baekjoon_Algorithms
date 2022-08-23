package programmers;

import java.util.Arrays;
import java.util.Collections;

//H-Index
public class problem42747 {

    public static void main(String[] args) {

        int arr[] = {10, 8, 5, 4, 3};

        System.out.println(solution(arr));

    }
    public static int solution(int[] citations) {

        int size = citations.length;
        Integer arr[] = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        int count = 0;
        int answer = 0;

        for(int i = size; i>= 0; i--) {
            count = 0;
            for(int j = 0; j< size; j++) {
               if(arr[j] >= i)
                   count++;
            }
            if(i <= count)
                if(answer < i)
                    answer = i;
        }
        return answer;
    }
}
