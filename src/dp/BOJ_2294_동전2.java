package dp;

import java.io.*;
import java.util.*;

public class BOJ_2294_동전2 {
    static final int MAX_VALUE = 10001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        // x원을 만드는 동전의 최소 개수
        int[] dp = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            dp[i] = MAX_VALUE;
        }
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        int ans = dp[k] == MAX_VALUE ? -1 : dp[k];
        System.out.println(ans);
    }
}
