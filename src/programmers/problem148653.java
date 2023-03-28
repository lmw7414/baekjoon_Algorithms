package programmers;


public class problem148653 {
    public static void main(String[] args) {
        System.out.println(solution(95));
        System.out.println(solution(155));
        System.out.println(solution(154));
        System.out.println(solution(555));
        System.out.println(solution(16));
        System.out.println(solution(2554));
    }

    public static int solution(int storey) {
        String input = Integer.toString(storey);
        int idx = input.length() + 1;
        int[] arr = new int[idx];
        for (int i = 1; i < idx; i++) {
            arr[i] = input.charAt(i - 1) - '0';
        }

        int answer = 0;

        while (!isContainAllZero(arr)) {
            idx--;
            if (arr[idx] > 5) {
                answer += 10 - arr[idx];
                arr[idx - 1]++;
                if (arr[idx - 1] >= 10) {
                    arr[idx - 2]++;
                    arr[idx - 1] -= 10;
                }
            } else {
                if (arr[idx] == 5 && (arr[idx - 1] + 1 == 10 || arr[idx-1] == 5)) {
                    answer += 10 - arr[idx];
                    arr[idx - 1]++;
                    if (arr[idx - 1] >= 10) {
                        arr[idx - 2]++;
                        arr[idx - 1] -= 10;
                    }

                } else
                    answer += arr[idx];
            }
            arr[idx] = 0;
        }
        return answer + arr[0];
    }

    public static boolean isContainAllZero(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != 0)
                return false;
        }
        return true;
    }
}
