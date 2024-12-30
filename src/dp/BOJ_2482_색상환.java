package dp;

import java.io.*;

public class BOJ_2482_색상환 {
    static final int MOD = 1_000_000_003;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        if (k == 1) {
            System.out.println(n);
            return;
        }
        if (k > n / 2) {
            System.out.println(0);
            return;
        }
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }
        long ans = (dp[n - 1][k] + dp[n - 3][k - 1]) % MOD;
        System.out.println(ans);
    }
}
