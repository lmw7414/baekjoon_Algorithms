import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int answer = 0;
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        String[] first = str.split("-");
        String[] p = first[0].split("\\+");
        for(String s : p )
            answer += Integer.parseInt(s);
        for(int i = 1; i< first.length; i++) {
            String[] k = first[i].split("\\+");
            for(String s : k)
                answer -= Integer.parseInt(s);
        }

        System.out.println(answer);
    }
}
