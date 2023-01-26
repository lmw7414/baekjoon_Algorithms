package baekjoon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class problem10610 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inputs = sc.nextLine().split("");
        Integer[] arr = new Integer[inputs.length];
        int answer = -1;

        for(int i = 0; i< arr.length; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }
        Arrays.sort(arr, Collections.reverseOrder());

        if(arr[arr.length-1] != 0)
            System.out.println(-1);
        else{
            int sum = Arrays.stream(arr).mapToInt(Integer::intValue).sum();
            if(sum % 3== 0)
                Arrays.stream(arr).forEach(System.out::print);
            else
                System.out.println(-1);
        }
    }
}
