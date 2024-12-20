package dp;

import java.io.*;
import java.util.*;

public class BOJ_2240_자두나무 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] dp = new int[t + 1][w + 1];
        for (int i = 1; i <= t; i++) {
            int tree = Integer.parseInt(br.readLine());
            dp[i][0] = dp[i - 1][0] + tree % 2;
            for (int j = 1; j <= w; j++) {
                if (j % 2 == tree % 2) { // 위치 다른 경우
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i - 1][j - 1]);
                }
            }
        }
        System.out.println(dp[t][w]);
    }

}
