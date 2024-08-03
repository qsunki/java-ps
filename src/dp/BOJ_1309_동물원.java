package dp;

import java.io.*;

public class BOJ_1309_동물원 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new int[2];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 0; i < n; i++) {
            int dp0 = dp[0];
            int dp1 = dp[1];
            dp[0] = (dp0 + dp1) % 9901;
            dp[1] = (dp0 * 2 + dp1) % 9901;
        }

        System.out.println(dp[0]);
    }
}
