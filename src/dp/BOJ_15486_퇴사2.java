package dp;

import java.io.*;
import java.util.*;

public class BOJ_15486_퇴사2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(dp[i], dp[i - 1]);
            int endDate = i + t - 1;
            if (endDate <= n) {
                dp[endDate] = Math.max(dp[endDate], dp[i - 1] + p);
            }
        }
        System.out.println(dp[n]);
    }

}
