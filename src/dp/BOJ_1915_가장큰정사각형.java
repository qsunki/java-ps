package dp;

import java.io.*;
import java.util.*;

public class BOJ_1915_가장큰정사각형 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] square = new char[n][];
        int[][] dp = new int[n][m];
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            square[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                dp[i][j] = square[i][j] - '0';
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] == 1) {
                    int tmp = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    tmp = Math.min(tmp, dp[i - 1][j - 1]);
                    dp[i][j] = tmp + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        System.out.println(maxLength * maxLength);
    }
}
