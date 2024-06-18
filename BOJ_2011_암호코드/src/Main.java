import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] code;
    static int n;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        char[] charArray = br.readLine().toCharArray();
        if (charArray[0] == '0') {
            System.out.println(0);
            return;
        }
        n = charArray.length;
        code = new int[n];
        for (int i = 0; i < n; i++) {
            code[i] = charArray[i] - '0';
        }
        dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            if (code[i] == 0 && code[i - 1] == 0) {
                System.out.println(0);
                return;
            }
            if (code[i] == 0) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % 1000000;
            }
            if (code[i - 1] * 10 + code[i] <= 26) {
                dp[i][1] = dp[i - 1][0] % 1000000;
            }
        }
        System.out.println((dp[n - 1][0] + dp[n - 1][1]) % 1000000);
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
