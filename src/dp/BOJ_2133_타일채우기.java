package dp;

import java.io.*;

public class BOJ_2133_타일채우기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(0);
            return;
        }
        int[] dp = new int[n + 1];
        dp[2] = 3;
        for (int i = 3; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i - 2] * 3 + 2;
                for (int j = 4; j < i; j += 2) {
                    dp[i] += dp[i - j] * 2;
                }
            }
        }
        System.out.println(dp[n]);
    }
}
