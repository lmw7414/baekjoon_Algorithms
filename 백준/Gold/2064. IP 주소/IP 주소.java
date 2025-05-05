
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = new String[N];
        for (int i = 0; i < N; i++) str[i] = br.readLine();
        sort(str);
        int M = getSubnetMask(str[0], str[N - 1]);
        int[] ipAddress = makeIpAddress(str[0], M);
        printIp(ipAddress);
        int[] subnetMask = makeSubnetMask(M);
        printIp(subnetMask);
    }
    
    public static void sort(String[] str) {
        Arrays.sort(str, (a, b) -> {
            String[] sa = a.split("\\.");
            String[] sb = b.split("\\.");
            for (int i = 0; i < 4; i++) {
                int na = Integer.parseInt(sa[i]);
                int nb = Integer.parseInt(sb[i]);
                if (na != nb) return Integer.compare(na, nb);
            }
            return 0;
        });
    }

    // 서브넷 마스크 구하기(앞자리 부터 동일한 구간 찾기)
    public static int getSubnetMask(String str1, String str2) {
        int[] ip1 = getIpAddress(str1);
        int[] ip2 = getIpAddress(str2);

        int m = 0;
        for (int i = 0; i < 4; i++) {
            if (ip1[i] == ip2[i]) m += 8;
            else {
                for (int j = 7; j >= 0; j--) {
                    if ((ip1[i] & (1 << j)) == (ip2[i] & (1 << j))) m++;
                    else break;
                }
                break;
            }
        }
        return m;
    }

    public static int[] makeIpAddress(String str, int m) {
        int[] data = getIpAddress(str);
        StringBuilder sb = new StringBuilder();
        for (int i : data) {
            for (int j = 7; j >= 0; j--) {
                if ((i & (1 << j)) != 0) sb.append(1);
                else sb.append(0);
            }
        }
        StringBuilder ipAddress = new StringBuilder();
        ipAddress.append(sb, 0, m);
        for (int i = m; i < 32; i++) ipAddress.append(0);
        int[] ip = new int[4];
        for (int i = 0; i < 4; i++) ip[i] = Integer.parseInt(ipAddress.substring(i * 8, (i + 1) * 8), 2);
        return ip;
    }

    // string -> Ip
    public static int[] getIpAddress(String str) {
        String[] s = str.split("\\.");
        return new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3])};
    }

    public static int[] makeSubnetMask(int m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if (m-- > 0) sb.append(1);
            else sb.append(0);
        }
        int[] subnet = new int[4];
        for (int i = 0; i < 4; i++) subnet[i] = Integer.parseInt(sb.substring(i * 8, (i + 1) * 8), 2);
        return subnet;
    }

    public static void printIp(int[] ip) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(ip[i]);
            if (i < 3) sb.append(".");
        }
        System.out.println(sb);
    }

}
