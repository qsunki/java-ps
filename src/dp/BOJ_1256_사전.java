package dp;

import java.io.*;
import java.util.*;

public class BOJ_1256_사전 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[][] dp = new int[201][201];
    static final int MAX = 1_000_000_001;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();

        computeCombinations(n, m);

        if (dp[n + m][n] < k) {
            System.out.println(-1);
        } else {
            getString(n, m, k);
            System.out.println(sb);
        }
    }

    static void computeCombinations(int n, int m) {
        for (int i = 0; i <= n + m; i++) {
            dp[i][0] = dp[i][i] = 1;
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                if (dp[i][j] > MAX) {
                    dp[i][j] = MAX;
                }
            }
        }
    }

    static void getString(int n, int m, int k) {
        if (n == 0) {
            for (int i = 0; i < m; i++) sb.append('z');
            return;
        }
        if (m == 0) {
            for (int i = 0; i < n; i++) sb.append('a');
            return;
        }

        int num_a_start = dp[n + m - 1][n - 1];
        if (k <= num_a_start) {
            sb.append('a');
            getString(n - 1, m, k);
        } else {
            sb.append('z');
            getString(n, m - 1, k - num_a_start);
        }
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
