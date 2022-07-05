package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//2979 트럭주차
public class problem2979 {

    static int chargeA;
    static int chargeB;
    static int chargeC;
    static int chargeTable[];
    static int max = 0;
    static int totalCharge = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        ChargeTime[] ct = new ChargeTime[3];
        chargeA = Integer.parseInt(st.nextToken());
        chargeB = Integer.parseInt(st.nextToken());
        chargeC = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            ct[i] = new ChargeTime();
            ct[i].start = Integer.parseInt(st.nextToken());
            ct[i].end = Integer.parseInt(st.nextToken());
            if (ct[i].end > max)
                max = ct[i].end;
        }
        chargeTable = new int[max + 1];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < chargeTable.length; j++) {
                if (j > ct[i].start && j <= ct[i].end)
                    chargeTable[j]++;
            }
        }

        for (int i = 0; i < chargeTable.length; i++) {
            if (chargeTable[i] == 1) {
                totalCharge += chargeA;
            } else if (chargeTable[i] == 2) {
                totalCharge += 2 * chargeB;
            } else if(chargeTable[i] == 3){
                totalCharge += 3 * chargeC;
            }
        }
        System.out.println(totalCharge);
    }
}

class ChargeTime {
    public int start;
    public int end;

    public ChargeTime() {
        start = 0;
        end = 0;
    }
}
