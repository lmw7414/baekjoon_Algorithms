package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

public class problem2644 {

    static int N;
    static int[] arr;
    static boolean[] arrCheck;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        N = scan.nextInt();
        ArrayList<Integer> key1Arr = new ArrayList<>();
        ArrayList<Integer> key2Arr = new ArrayList<>();
        int key1 = scan.nextInt();
        int key2 = scan.nextInt();
        key1Arr.add(key1);
        key2Arr.add(key2);

        arr = new int[N + 1];
        arrCheck = new boolean[N + 1];

        int count = scan.nextInt();

        for (int i = 0; i < count; i++) {
            int parent = scan.nextInt();
            int child = scan.nextInt();
            arr[child] = parent;
        }

        // key 1 작업
        int idx = key1;
        while (true) {
            if (arr[idx] == 0) {
                break;
            }
            key1Arr.add(arr[idx]);
            idx = arr[idx];
        }
        // key 2 작업
        idx = key2;
        while (true) {
            if (arr[idx] == 0) {
                break;
            }
            key2Arr.add(arr[idx]);
            idx = arr[idx];
        }


        boolean isContain = false;
        int key1Parent = 0, key2Parent = 0;
        for (int i = 0; i < key1Arr.size(); i++) {
            if (key2Arr.contains(key1Arr.get(i))) {
                key1Parent = i;
                key2Parent = key2Arr.indexOf(key1Arr.get(i));
                isContain = true;
                break;
            }
        }
        if (!isContain) {
            System.out.println(-1);
        } else {
            System.out.println(key1Parent + key2Parent);
        }

    }
}
